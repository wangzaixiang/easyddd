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
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class PortableHelperGenerator extends Generator {

	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {

		final String TYPE_SUFFIX = "_Helper";

		// init
		String requestedFullName = typeName;
		Class requestedClass = null;
		try {
			requestedClass = Class.forName(requestedFullName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		String packageName = requestedFullName.substring(0, requestedFullName
				.lastIndexOf('.'));
		String requestedSimpleName = requestedFullName
				.substring(requestedFullName.lastIndexOf('.') + 1);
		String generatedSimpleName = requestedSimpleName + TYPE_SUFFIX;
		String generatedFullName = requestedFullName + TYPE_SUFFIX;

		// must be a interface
		if (!requestedClass.isInterface())
			return requestedFullName;

		// get imports
		Set imports = getImports(requestedClass);

		// get source writer
		SourceWriter sourceWriter = getSourceWriter(logger, context, imports,
				packageName, generatedSimpleName);
		if (null == sourceWriter)
			return generatedFullName;

		// do generate
		sourceWriter.println();
		generateStaticInitializer(sourceWriter, imports);
		sourceWriter.println();
		generateDoExport(sourceWriter, packageName, requestedSimpleName,
				requestedClass);
		sourceWriter.println();
		generateDoImport(sourceWriter, requestedSimpleName);
		sourceWriter.println();
		generateStub(sourceWriter, requestedSimpleName, requestedClass,
				generatedFullName);
		sourceWriter.println();
		sourceWriter.commit(logger);

		return generatedFullName;
	}

	private Set getImports(Class requestedClass) {

		Set imports = new HashSet();

		Method[] methods = requestedClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Class returnType = methods[i].getReturnType();
			if (shouldImport(returnType))
				imports.add(returnType);
			Class[] paramTypes = methods[i].getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (shouldImport(paramTypes[j]))
					imports.add(paramTypes[j]);
			}
		}
		return imports;
	}

	private SourceWriter getSourceWriter(TreeLogger logger,
			GeneratorContext ctx, Set imports, String packageName,
			String generatedSimpleName) {

		// get writer
		PrintWriter printWriter = ctx.tryCreate(logger, packageName,
				generatedSimpleName);
		if (printWriter == null) {
			return null;
		}

		// get composer factory
		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
				packageName, generatedSimpleName);

		// add imports required
		for (Iterator iter = imports.iterator(); iter.hasNext();) {
			Class typeToImport = (Class) iter.next();
			composerFactory.addImport(typeToImport.getName());
		}
		composerFactory.addImport(JavaScriptObject.class.getName());
		composerFactory.addImport(GWT.class.getName());

		// create source writer
		SourceWriter sourceWriter = composerFactory.createSourceWriter(ctx,
				printWriter);
		return sourceWriter;
	}

	private void generateStaticInitializer(SourceWriter sourceWriter,
			Set imports) {
		sourceWriter.println("static {");
		sourceWriter.indent();
		for (Iterator iter = imports.iterator(); iter.hasNext();) {
			Class importedType = (Class) iter.next();
			sourceWriter.println("GWT.create(" + importedType.getSimpleName()
					+ ".class);");
		}
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateDoExport(SourceWriter sourceWriter,
			String packageName, String requestedSimpleName, Class requestedClass) {
		sourceWriter.println("public static native JavaScriptObject doExport("
				+ requestedSimpleName + " jo) /*-{");
		sourceWriter.indent();
		sourceWriter.println("return {");
		sourceWriter.indent();
		Method[] methods = requestedClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String params = "";
			Class[] paramList = methods[i].getParameterTypes();
			for (int j = 0; j < paramList.length; j++) {
				params += "arg" + j + ((j < paramList.length - 1) ? ", " : "");
			}
			sourceWriter.println(methods[i].getName() + ": function(" + params
					+ ") {");
			sourceWriter.indent();
			String translatedParams = "";
			for (int j = 0; j < paramList.length; j++) {
				translatedParams += translateName(paramList[j].getName());
				if (isPrimitive(paramList[j]))
					continue;
				sourceWriter
						.println("arg"
								+ j
								+ " = @"
								+ paramList[j].getName()
								+ "_Helper"
								+ "::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(arg"
								+ j + ");");
			}
			sourceWriter.println("var ret = jo.@" + packageName + "."
					+ requestedSimpleName + "::" + methods[i].getName() + "("
					+ translatedParams + ")(" + params + ");");
			Class returnType = methods[i].getReturnType();
			if (!isVoid(returnType)) {
				if (isPrimitive(returnType))
					sourceWriter.println("return ret;");
				else
					sourceWriter.println("return " + "@" + returnType.getName()
							+ "_Helper" + "::doExport("
							+ translateName(returnType.getName()) + ")(ret);");
			}
			sourceWriter.outdent();
			sourceWriter.print("}" + ((i < methods.length - 1) ? "," : ""));
			sourceWriter.println();
		}
		sourceWriter.outdent();
		sourceWriter.println("};");
		sourceWriter.outdent();
		sourceWriter.println("}-*/;");
	}

	private void generateDoImport(SourceWriter sourceWriter,
			String requestedSimpleName) {
		sourceWriter.println("public static " + requestedSimpleName
				+ " doImport(JavaScriptObject jso) {");
		sourceWriter.indent();
		sourceWriter.println("return new Stub(jso);");
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateStub(SourceWriter sourceWriter,
			String requestedSimpleName, Class requestedClass,
			String generatedFullName) {
		sourceWriter.println("public static class Stub implements "
				+ requestedSimpleName + " {");
		sourceWriter.indent();
		sourceWriter.println();
		sourceWriter.println("private JavaScriptObject jso;");
		sourceWriter.println();
		generateStubConstructor(sourceWriter);
		sourceWriter.println();
		Method[] methods = requestedClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			generateStubMethod(sourceWriter, methods[i], generatedFullName);
			sourceWriter.println();
		}
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateStubConstructor(SourceWriter sourceWriter) {
		sourceWriter.println("public Stub(JavaScriptObject jso) {");
		sourceWriter.indent();
		sourceWriter.println("this.jso = jso;");
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateStubMethod(SourceWriter sourceWriter, Method method,
			String generatedFullName) {
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
		sourceWriter.println("public native " + returnType + " "
				+ method.getName() + "(" + params + ") /*-{");
		sourceWriter.indent();
		for (int j = 0; j < paramList.length; j++) {
			if (isPrimitive(paramList[j]))
				continue;
			sourceWriter.println("arg" + j + " = @" + paramList[j].getName()
					+ "_Helper" + "::doExport("
					+ translateName(paramList[j].getName()) + ")(arg" + j
					+ ");");
		}
		sourceWriter.println("var ret = this.@" + generatedFullName
				+ ".Stub::jso." + method.getName() + "(" + args + ");");
		if (!isVoid(method.getReturnType())) {
			if (isPrimitive(method.getReturnType()))
				sourceWriter.println("return ret;");
			else
				sourceWriter
						.println("return "
								+ "@"
								+ method.getReturnType().getName()
								+ "_Helper"
								+ "::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);");
		}
		sourceWriter.outdent();
		sourceWriter.println("}-*/;");
	}

	private boolean shouldImport(Class type) {
		return !isPrimitive(type);
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

	private String translateName(String typeName) {
		if (typeName.indexOf('.') > 0)
			return "L" + typeName.replace('.', '/') + ";";
		else if (typeName.equals("byte"))
			return "B";
		else if (typeName.equals("char"))
			return "C";
		else if (typeName.equals("int"))
			return "I";
		else if (typeName.equals("short"))
			return "S";
		else if (typeName.equals("long"))
			return "J";
		else if (typeName.equals("float"))
			return "F";
		else if (typeName.equals("double"))
			return "D";
		else if (typeName.equals("boolean"))
			return "Z";
		else
			throw new RuntimeException();
	}

}
