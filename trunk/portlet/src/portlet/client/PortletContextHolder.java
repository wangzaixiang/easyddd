package portlet.client;

import portlet.client.impl.DefaultPortletContext;

import com.google.gwt.core.client.JavaScriptObject;

public class PortletContextHolder {

	private static native JavaScriptObject get() /*-{
		return $wnd.__portlet_context || null;
	}-*/;
	
	private static native void put(JavaScriptObject jsoPortletContext) /*-{
		$wnd.__portlet_context = jsoPortletContext;
	}-*/;
	
	public static PortletContext getPortletContext() {
		JavaScriptObject jso = get();
		if ( null == jso ) {
			PortletContext pc = new DefaultPortletContext();
			jso = PortletContext.Helper.doExport(pc);
			put(jso);
		}
		return PortletContext.Helper.doImport(jso);
	}
	
}
