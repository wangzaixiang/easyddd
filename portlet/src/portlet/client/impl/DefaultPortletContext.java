package portlet.client.impl;

import java.util.ArrayList;
import java.util.List;

import portlet.client.PortletContext;
import portlet.client.PortletInfo;

import com.google.gwt.user.client.Window;

public class DefaultPortletContext implements PortletContext {

	private List modules;

	public DefaultPortletContext() {
		modules = new ArrayList();
	}

	public void register(PortletInfo portletInfo) {
		Window.alert("registering " + portletInfo.getName());
		modules.add(portletInfo);
	}

	public int getLength() {
		int length = modules.size();
		return length;
	}

	public PortletInfo getPortletInfo(int index) {
		return (PortletInfo)modules.get(index);
	}
}
