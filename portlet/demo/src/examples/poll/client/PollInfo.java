package examples.poll.client;

import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.core.client.EntryPoint;

public class PollInfo implements PortletInfo, EntryPoint {

	public String getName() {
		return "poll";
	}

	public void onModuleLoad() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		pc.register(this);
	}
	
	public void create(String containerID, String dataID) {
		new Poll(containerID, dataID);
	}
}
