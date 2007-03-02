package demo.gwt.portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public interface PortletContext {

	void registerPortletInfo(PortletInfo info);

	PortletInfo getPortletInfo(String moduleName);

	void loadPortletModule(String moduleName);

	Portlet[] getPortletOfModule(String module);

	Portlet getPortlet(String id);

	void setMaxinum(Portlet portlet);

	void register(Portlet portlet);

	void unregister(Portlet portlet);

	void notifyEvent(String module, String portletId, String category,
			JavaScriptObject content);

	void registerEventListener(String fromModule, String fromPortlet,
			String fromCategory, String destPortletId);

}
