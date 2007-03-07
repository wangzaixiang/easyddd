package examples.portlet_1.client;

import portlet.client.PortletInfo;
import portlet.client.impl.DefaultPortletContext;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class Portlet_1 implements EntryPoint, PortletInfo{
	
	public void onModuleLoad() {
		Window.alert("portlet_1.onModuleLoad");
	}

	public String getName() {
		return "portlet_1";
	}
	
}
