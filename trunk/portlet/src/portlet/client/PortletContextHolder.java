package portlet.client;

import portlet.client.impl.DefaultPortletContext;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;

public class PortletContextHolder implements EntryPoint {

	private static native JavaScriptObject get() /*-{
		return $wnd.__portlet_context || null;
	}-*/;
	
	private static native void put(JavaScriptObject jsoPortletContext) /*-{
		$wnd.__portlet_context = jsoPortletContext;
	}-*/;
	
	private static native JavaScriptObject doExport(PortletContext pc) /*-{
		return @portlet.client.PortletContextHelper::doExport(Lportlet/client/PortletContext;)(pc);
	}-*/;
	
	private static native PortletContext doImport(JavaScriptObject jso) /*-{
		return @portlet.client.PortletContextHelper::doImport(Lcom/google/gwt/core/client/JavaScriptObject;)(jso);
	}-*/;
	
	public void onModuleLoad() {
		JavaScriptObject jso = get();
		if ( null == jso ) {
			PortletContext pc = new DefaultPortletContext();
			jso = doExport(pc);
			put(jso);
		}
	}
	
	public static PortletContext getPortletContext() {
		JavaScriptObject jso = get();
		return doImport(jso);
	}
	
}