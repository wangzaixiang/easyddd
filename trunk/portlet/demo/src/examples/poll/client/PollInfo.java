package examples.poll.client;

import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;

public class PollInfo implements PortletInfo, EntryPoint {

	public String getName() {
		return "poll";
	}

	public void onModuleLoad() {
		PortletContext pc = PortletContextHolder.getPortletContext();
		pc.register(this);
	}
	
	public void create(String containerID, String dataID) {
		Poll poll = new Poll(dataID);
		poll.render(DOM.getElementById(containerID));
	}
}
