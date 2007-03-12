package examples.portlet_2.client;

import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class Portlet_2 implements EntryPoint, PortletInfo {
	
	public void onModuleLoad() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		pc.register(this);
	}

	public String getName() {
		return "portlet_2";
	}

	
}

