package demo.gwt.portlet.client;

import com.google.gwt.user.client.Element;

/**
 * A portlet container provide a UI context for a given portlet
 */
public interface PortletContainer {
	
	/**
	 * 
	 */
	PortletContext	getPortletContext();

	/**
	 * the portlet module name
	 */
	String getModuleName();

	String getPortletId();

	Portlet getPortlet();

	void renderPortletFrame(Element container);

}
