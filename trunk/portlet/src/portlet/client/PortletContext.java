package portlet.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;

public class PortletContext {
	
	private static native void put(PortletContext portletContext) /*-{
		$wnd.__portlet_context = portletContext;
	}-*/;
	
	private static native PortletContext get() /*-{
		return $wnd.__portlet_context || null;
	}-*/;
	
	public static PortletContext getInstance() {

		PortletContext instance = get();
		
		if ( null == instance ) {
			instance = new PortletContext();
			put(instance);
		}
		
		return instance;
	}
	
	//
	//--
	//
	
	private List modules;
	
	private PortletContext() {
		modules = new ArrayList();
		//TODO:
		getLength();
	}
	
	public void register(PortletInfo portletInfo) {
		Window.alert("registering " + portletInfo.getName());
		modules.add(portletInfo);
	}
	
	public int getLength() {
		int length = modules.size();
		return length;
	}

}
