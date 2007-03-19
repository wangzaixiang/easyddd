package portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public interface PortletContext {

	public static class Stub implements PortletContext {
		
		private JavaScriptObject jso;
		
		public Stub(JavaScriptObject jso) {
			this.jso = jso;
		}
		
		public native void register(PortletInfo pi) /*-{
			var piExported = @portlet.client.PortletInfo.Helper::doExport(Lportlet/client/PortletInfo;)(pi);
			this.@portlet.client.PortletContext.Stub::jso.register(piExported);
		}-*/;
		
		public native int getLength() /*-{
			return this.@portlet.client.PortletContext.Stub::jso.getLength();
		}-*/;
		
		public native PortletInfo getPortletInfo(int index) /*-{
			var pi = this.@portlet.client.PortletContext.Stub::jso.getPortletInfo(index);
			return @portlet.client.PortletInfo.Helper::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(pi); 
		}-*/;
	}

	public static class Helper implements PortableObjectHelper {
		public static native JavaScriptObject doExport(PortletContext pc) /*-{
			return {
				register: function(pi) {
					var piImported = @portlet.client.PortletInfo.Helper::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(pi);
					pc.@portlet.client.PortletContext::register(Lportlet/client/PortletInfo;)(piImported);
				},
				getLength: function() {
					return pc.@portlet.client.PortletContext::getLength()();
				},
				getPortletInfo: function(index) {
					var pi = pc.@portlet.client.PortletContext::getPortletInfo(I)(index);
					var piExported = @portlet.client.PortletInfo.Helper::doExport(Lportlet/client/PortletInfo;)(pi);
					return piExported;
				}
			};
		}-*/;
		
		public static PortletContext doImport(JavaScriptObject jso) {
			return new Stub(jso);
		}
	}
	
	void register(PortletInfo portletInfo);
	
	int getLength();
	
	PortletInfo getPortletInfo(int index);
}