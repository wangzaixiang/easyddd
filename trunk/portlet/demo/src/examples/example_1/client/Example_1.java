package examples.example_1.client;

import layout.client.Frame;
import layout.client.LayoutManager;
import layout.client.impl.DraggableLayoutManager;
import portlet.client.Portlet;
import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

public class Example_1 {

	public void onModuleLoad() {
		//
		LayoutManager dlm = new DraggableLayoutManager(DOM
				.getElementById("_dlm_container"));
		Frame frame_1 = dlm.getFrame("001");
		Frame frame_2 = dlm.getFrame("002");

		//
		// checkModules();

		//
		PortletInfo pollInfo = findPortletInfoByName("poll");
		if (null == pollInfo) {
			;
		} else {
			Portlet poll = pollInfo.create("001");
			poll.render(frame_1.getBodyElement());// DOM.getElementById("_poll_container_1")
			poll = pollInfo.create("002");
			poll.render(frame_2.getBodyElement());// DOM.getElementById("_poll_container_2")
		}
	}

	private PortletInfo findPortletInfoByName(String name) {
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		for (int i = 0; i < count; i++) {
			if (pc.getPortletInfo(i).getName().equals(name)) {
				return pc.getPortletInfo(i);
			}
		}
		return null;
	}

	private void checkModules() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		Window.alert("count of modules: " + count);
		for (int i = 0; i < count; i++) {
			String name = pc.getPortletInfo(i).getName();
			Window.alert("registered portlet: " + name);
		}
	}
}
