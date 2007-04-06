/**
 * 
 */
package portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public class PortletInfoHelper implements Portable {
	
	public static native JavaScriptObject doExport(PortletInfo pi) /*-{
		return {
			getName: function() {
				return pi.@portlet.client.PortletInfo::getName()();
			},
			create: function(containerID, dataID) {
				return pi.@portlet.client.PortletInfo::create(Ljava/lang/String;Ljava/lang/String;)(containerID, dataID);
			}
		};
	}-*/;

	public static PortletInfo doImport(JavaScriptObject jso) {
		return new Stub(jso);
	}

	public static class Stub implements PortletInfo {
		
		private JavaScriptObject jso;
		
		public Stub(JavaScriptObject jso) {
			this.jso = jso;
		}
		
		public native String getName () /*-{
			return this.@portlet.client.PortletInfoHelper.Stub::jso.getName();
		}-*/;
		
		public native void create(String containerID, String dataID) /*-{
			this.@portlet.client.PortletInfoHelper.Stub::jso.create(containerID, dataID);
		}-*/;
	}
}