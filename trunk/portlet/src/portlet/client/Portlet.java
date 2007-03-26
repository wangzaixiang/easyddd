package portlet.client;

import com.google.gwt.core.client.EntryPoint;

public class Portlet implements EntryPoint {

	public void onModuleLoad() {
		PortletContextHolder.init();
	}
	
}
