package portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public interface PortletInfo {

	public static class Stub implements PortletInfo {
		
		private JavaScriptObject jso;
		
		public Stub(JavaScriptObject jso) {
			this.jso = jso;
		}
		
		public native String getName () /*-{
			return this.@portlet.client.PortletInfo.Stub::jso.getName();
		}-*/;
	}
	
	public static class Helper implements PortableObjectHelper {
		
		public static native JavaScriptObject doExport(PortletInfo pi) /*-{
			return {
				getName: function() {
					return pi.@portlet.client.PortletInfo::getName()();
				}
			};
		}-*/;

		public static PortletInfo doImport(JavaScriptObject jso) {
			return new Stub(jso);
		}
	}

	String getName();
}
