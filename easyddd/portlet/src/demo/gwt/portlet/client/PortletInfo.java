package demo.gwt.portlet.client;

/**
 * exported by portlet module
 */
public interface PortletInfo {

	String getModuleName();

	String getPortletName();

	String getPortletDescription();

	String getIconURL();

	Portlet create(String[] args, PortletContainer container);
}