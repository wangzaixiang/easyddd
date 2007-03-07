package portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public interface PortletInfo {

	public static class Helper implements PortableObjectHelper {
		
		public static JavaScriptObject doExport(PortletContext jo) {
			return null;
		}

		public static PortletContext doImport(JavaScriptObject jso) {
			return null;
		}
	}

	String getName();
}
