package examples.portlet_1.client;

import portlet.client.PortletContext;
import portlet.client.PortletInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class Portlet_1 implements EntryPoint, PortletInfo{
	
	public void onModuleLoad() {
		PortletContext.getInstance().register(this);
	}

	public String getName() {
		return "portlet_1";
	}
	
}
