package portlet.rebind;

import java.io.PrintWriter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class PortableHelperGenerator extends Generator {

	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {

		TypeOracle typeOracle = context.getTypeOracle();
		assert (typeOracle != null);

		JType requestedType = typeOracle.findType(typeName);

		if ( null == requestedType.isInterface() )
			return typeName;

		// init
		int nameIndex = typeName.lastIndexOf('.');
		packageName = typeName.substring(0, nameIndex);
		className = typeName.substring(nameIndex + 1) + TYPE_SUFFIX;
		qualifiedName = typeName + TYPE_SUFFIX;

		// get source writer
		SourceWriter sw = getSourceWriter(logger, context);
		if (null == sw) {
			return getQualifiedName(false);
		}

		// write
		sw.print("public String getString() { return \"I love u.\"; }");
		sw.commit(logger);

		return getQualifiedName(true);
	}

	private String getQualifiedName(boolean wrote) {
		System.out.println(qualifiedName + "\twrote: " + wrote);
		return qualifiedName;
	}

	private SourceWriter getSourceWriter(TreeLogger logger, GeneratorContext ctx) {

		PrintWriter printWriter = ctx.tryCreate(logger, packageName, className);
		if (printWriter == null) {
			return null;
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
				packageName, className);

		// composerFactory.addImport(SerializationStreamWriter.class.getName());
		// composerFactory.addImplementedInterface("Serializer");

		return composerFactory.createSourceWriter(ctx, printWriter);
	}

	private String packageName;

	private String className;

	private String qualifiedName;

	private static final String TYPE_SUFFIX = "_Helper";
}
