package examples.example_1.client;

import portlet.client.PortletContext;

import com.google.gwt.user.client.Window;

public class Example_1 {


	private void init() {

	}

	public void onModuleLoad() {
		int size = PortletContext.getInstance().getLength();
		Window.alert("the count of registered modules : " + size);
	}

}
