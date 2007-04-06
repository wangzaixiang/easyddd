package portlet.rebind;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class PortableHelperGenerator extends Generator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.ext.Generator#generate(com.google.gwt.core.ext.TreeLogger,
	 *      com.google.gwt.core.ext.GeneratorContext, java.lang.String)
	 */
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {

		TypeOracle typeOracle = context.getTypeOracle();
		assert (typeOracle != null);

		JType requestedType = typeOracle.findType(typeName);

		if (null == requestedType.isInterface())
			return typeName;

		// init
		int nameIndex = typeName.lastIndexOf('.');
		packageName = typeName.substring(0, nameIndex);
		originalClassName = typeName.substring(nameIndex + 1);
		className = originalClassName + TYPE_SUFFIX;
		qualifiedName = typeName + TYPE_SUFFIX;

		// get source writer
		importSet.clear();
		SourceWriter sw = getSourceWriter(logger, context, typeName);
		if (null == sw) {
			return getQualifiedName();
		}

		// write
		generate(sw);
		sw.commit(logger);

		return getQualifiedName();
	}

	private void generate(SourceWriter sw) {
		generateStaticInitializer(sw);
		generateDoExport(sw);
		generateDoImport(sw);
		generateStub(sw);
	}

	private void generateStaticInitializer(SourceWriter sw) {
		sw.println("static {");
		sw.indent();
		for (Iterator iter = importSet.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			name = name.substring(name.lastIndexOf('.') + 1);
			// System.out.println("============" + name);
			sw.println("GWT.create(" + name + ".class);");
		}
		sw.outdent();
		sw.println("}");
	}

	private void generateDoExport(SourceWriter sw) {
		sw.println("public static native JavaScriptObject doExport("
				+ originalClassName + " jo) /*-{");
		sw.indent();
		sw.println("return {");
		sw.indent();
		try {
			Class cls = Class.forName(packageName + "." + originalClassName);
			Method[] methods = cls.getMethods();
			for (int i = 0; i < methods.length; i++) {
				String params = "";
				Class[] paramList = methods[i].getParameterTypes();
				for (int j = 0; j < paramList.length; j++) {
					params += "arg" + j
							+ ((j < paramList.length - 1) ? ", " : "");
				}
				sw.println(methods[i].getName() + ": function(" + params
						+ ") {");
				sw.indent();
				String translatedParams = "";
				for (int j = 0; j < paramList.length; j++) {
					translatedParams += translateName(paramList[j].getName());
					if (isPrimitive(paramList[j]))
						continue;
					// var piImported =
					// @portlet.client.PortletInfoHelper::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(pi);
					sw
							.println("arg"
									+ j
									+ " = @"
									+ paramList[j].getName()
									+ "_Helper"
									+ "::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(arg"
									+ j + ");");
				}
				// pc.@portlet.client.PortletContext::register(Lportlet/client/PortletInfo;)(piImported);
				sw.println("var ret = jo.@" + packageName + "."
						+ originalClassName + "::" + methods[i].getName() + "("
						+ translatedParams + ")(" + params + ");");
				if (!isVoid(methods[i].getReturnType())) {
					if (isPrimitive(methods[i].getReturnType()))
						sw.println("return ret;");
					else
						sw.println("return "
								+ "@"
								+ methods[i].getReturnType().getName()
								+ "_Helper"
								+ "::doExport(L"
								+ methods[i].getReturnType().getName().replace(
										'.', '/') + ";)(ret);");
				}
				sw.outdent();
				sw.print("}" + ((i < methods.length - 1) ? "," : ""));
				sw.println();
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		sw.outdent();
		sw.println("};");
		sw.outdent();
		sw.println("}-*/;");
	}

	private String translateName(String name) {
		if (name.indexOf('.') > 0)
			return "L" + name.replace('.', '/') + ";";
		else if (name.equals("byte"))
			return "B";
		else if (name.equals("char"))
			return "C";
		else if (name.equals("int"))
			return "I";
		else if (name.equals("short"))
			return "S";
		else if (name.equals("long"))
			return "J";
		else if (name.equals("float"))
			return "F";
		else if (name.equals("double"))
			return "D";
		else if (name.equals("boolean"))
			return "Z";
		else
			throw new RuntimeException();
	}

	private void generateDoImport(SourceWriter sw) {
		sw.println("public static " + originalClassName
				+ " doImport(JavaScriptObject jso) {");
		sw.indent();
		sw.println("return new Stub(jso);");
		sw.outdent();
		sw.println("}");
	}

	private void generateStub(SourceWriter sw) {
		sw.println("public static class Stub implements " + originalClassName
				+ " {");
		sw.indent();
		sw.println("private JavaScriptObject jso;");
		generateStubConstructor(sw);
		try {
			Class cls = Class.forName(packageName + "." + originalClassName);
			Method[] methods = cls.getMethods();
			for (int i = 0; i < methods.length; i++) {
				generateStubMethod(sw, methods[i]);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		sw.outdent();
		sw.println("}");
	}

	private void generateStubConstructor(SourceWriter sw) {
		sw.println("public Stub(JavaScriptObject jso) {");
		sw.indent();
		sw.println("this.jso = jso;");
		sw.outdent();
		sw.println("}");
	}

	private void generateStubMethod(SourceWriter sw, Method method) {
		String returnType = method.getReturnType().getName();
		if (returnType.indexOf('.') > 0)
			returnType = returnType.substring(returnType.lastIndexOf('.') + 1);
		String params = "", args = "";
		Class[] paramList = method.getParameterTypes();
		for (int i = 0; i < paramList.length; i++) {
			String paramType = paramList[i].getName();
			if (paramType.indexOf('.') > 0)
				paramType = paramType.substring(paramType.lastIndexOf('.') + 1);
			params += paramType + " arg" + i
					+ ((i < paramList.length - 1) ? "," : "");
			args += "arg" + i + ((i < paramList.length - 1) ? "," : "");
		}
		sw.println("public native " + returnType + " " + method.getName() + "("
				+ params + ") /*-{");
		sw.indent();
		for (int j = 0; j < paramList.length; j++) {
			if (isPrimitive(paramList[j]))
				continue;
			// var piExported =
			// @portlet.client.PortletInfoHelper::doExport(Lportlet/client/PortletInfo;)(pi);
			sw.println("arg" + j + " = @" + paramList[j].getName() + "_Helper"
					+ "::doExport(L" + paramList[j].getName().replace('.', '/')
					+ ";)(arg" + j + ");");
		}
		// this.@portlet.client.PortletContextHelper.Stub::jso.register(piExported);
		sw.println("var ret = this.@" + qualifiedName + ".Stub::jso."
				+ method.getName() + "(" + args + ");");
		if (!isVoid(method.getReturnType())) {
			if (isPrimitive(method.getReturnType()))
				sw.println("return ret;");
			else
				// return
				//
				// @portlet.client.PortletInfoHelper::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(pi);
				sw
						.println("return "
								+ "@"
								+ method.getReturnType().getName()
								+ "_Helper"
								+ "::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);");
		}
		sw.outdent();
		sw.println("}-*/;");
	}

	private String getQualifiedName() {
		return qualifiedName;
	}

	private SourceWriter getSourceWriter(TreeLogger logger,
			GeneratorContext ctx, String typeName) {

		PrintWriter printWriter = ctx.tryCreate(logger, packageName, className);
		if (printWriter == null) {
			return null;
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
				packageName, className);

		try {
			Class cls = Class.forName(typeName);
			Method[] methods = cls.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Class typeReturn = methods[i].getReturnType();
				addImport(composerFactory, typeReturn);
				Class[] typeParams = methods[i].getParameterTypes();
				for (int j = 0; j < typeParams.length; j++) {
					addImport(composerFactory, typeParams[j]);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		composerFactory.addImport(JavaScriptObject.class.getName());
		composerFactory.addImport(GWT.class.getName());
		composerFactory.addImport(typeName);
		// composerFactory.addImplementedInterface("Serializer");

		SourceWriter sw = composerFactory.createSourceWriter(ctx, printWriter);
		// return new SourceWriterWrapper(sw);
		return sw;
	}

	private void addImport(ClassSourceFileComposerFactory composerFactory,
			Class type) {
		if (isPrimitive(type))
			return;
		composerFactory.addImport(type.getName());
		// composerFactory.addImport(type.getName() + "_Helper");
		importSet.add(type.getName());
	}

	private boolean isVoid(Class type) {
		return void.class.equals(type);
	}

	private boolean isPrimitive(Class type) {
		if (void.class.equals(type)) {
			;
		} else if (boolean.class.equals(type)) {
			;
		} else if (short.class.equals(type)) {
			;
		} else if (int.class.equals(type)) {
			;
		} else if (long.class.equals(type)) {
			;
		} else if (float.class.equals(type)) {
			;
		} else if (double.class.equals(type)) {
			;
		} else if (char.class.equals(type)) {
			;
		} else if (byte.class.equals(type)) {
			;
		} else if (String.class.equals(type)) {
			;
		} else if (Boolean.class.equals(type)) {
			;
		} else if (Byte.class.equals(type)) {
			;
		} else if (Character.class.equals(type)) {
			;
		} else if (Short.class.equals(type)) {
			;
		} else if (Integer.class.equals(type)) {
			;
		} else if (Long.class.equals(type)) {
			;
		} else if (Double.class.equals(type)) {
			;
		} else if (Float.class.equals(type)) {
			;
		} else {
			return false;
		}
		return true;
	}

	private String packageName;

	private String className;

	private String originalClassName;

	private String qualifiedName;

	private static final String TYPE_SUFFIX = "_Helper";

	private Set importSet = new HashSet();
}
