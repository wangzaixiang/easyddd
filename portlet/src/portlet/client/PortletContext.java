package portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public interface PortletContext {

	public static class Helper implements PortableObjectHelper {
		public static JavaScriptObject doExport(PortletContext jo) {
			return null;
		}

		public static PortletContext doImport(JavaScriptObject jso) {
			return null;
		}
	}
	
	void register(PortletInfo portletInfo);
	
	int getLength();
}
