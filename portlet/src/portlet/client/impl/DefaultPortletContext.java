package portlet.client.impl;

import java.util.ArrayList;
import java.util.List;

import portlet.client.PortletInfo;

import com.google.gwt.user.client.Window;

public class DefaultPortletContext {
	
	private static native void put(DefaultPortletContext portletContext) /*-{
		$wnd.__portlet_context = portletContext;
	}-*/;
	
	private static native DefaultPortletContext get() /*-{
		return $wnd.__portlet_context || null;
	}-*/;
	
	public static DefaultPortletContext getInstance() {

		DefaultPortletContext instance = get();
		
		if ( null == instance ) {
			instance = new DefaultPortletContext();
			put(instance);
		}
		
		return instance;
	}
	
	//
	//--
	//
	
	private List modules;
	
	private DefaultPortletContext() {
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
