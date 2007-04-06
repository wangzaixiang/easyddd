package portlet.rebind;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.user.rebind.SourceWriter;

public class SourceWriterWrapper implements SourceWriter {

	private SourceWriter sw;
	
	private String prefix = "";
	
	public SourceWriterWrapper(SourceWriter sw) {
		this.sw = sw;
	}
	
	public void beginJavaDocComment() {
		sw.beginJavaDocComment();
	}

	public void commit(TreeLogger logger) {
		sw.commit(logger);
	}

	public void endJavaDocComment() {
		sw.endJavaDocComment();
	}

	public void indent() {
		prefix += "\t";
		sw.indent();
	}

	public void indentln(String s) {
		prefix += "\t";
		System.out.println(prefix + s);
		sw.indentln(s);
	}

	public void outdent() {
		if ( prefix.length() > 0 )
			prefix = prefix.substring(1);
		sw.outdent();
	}

	public void print(String s) {
		System.out.print(prefix + s);
		sw.print(s);
	}

	public void println() {
		System.out.println();
		sw.println();
	}

	public void println(String s) {
		System.out.println(prefix + s);
		sw.println(s);
	}
	
}
