package examples.example_1.client;

import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.user.client.Window;

public class Example_1 {

	public void onModuleLoad() {
		checkModules();
		PortletInfo pollInfo = findPortletInfoByName("poll");
		if ( null == pollInfo ) {
			;
		} else {
			pollInfo.create("_poll_container_1", "001");
			pollInfo.create("_poll_container_2", "002");
		}
	}
	
	private PortletInfo findPortletInfoByName(String name) {
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		for ( int i = 0; i < count; i++ ) {
			if ( pc.getPortletInfo(i).getName().equals(name) ) {
				return pc.getPortletInfo(i);
			}
		}
		return null;
	}
	
	private void checkModules() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		Window.alert("count of modules: " + count);
		for ( int i = 0; i < count; i++ ) {
			String name = pc.getPortletInfo(i).getName();
			Window.alert("registered portlet: " + name);
		}
	}
}

