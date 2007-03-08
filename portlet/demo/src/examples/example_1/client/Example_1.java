package examples.example_1.client;

import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.impl.DefaultPortletContext;

import com.google.gwt.user.client.Window;

public class Example_1 {

	public void onModuleLoad() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		Window.alert("count of modules: " + count);
		for ( int i = 0; i < count; i++ ) {
			Window.alert("registered portlet: " + pc.getPortletInfo(i).getName());
		}
	}

}
