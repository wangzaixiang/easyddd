package demo.gwt.portlet.client;

/**
 * a draft design for GWT Portlet
 * 
 * <ul>
 * core classes:
 * <li> Portlet. each portlet should implements Portlet and render itself.
 * <li> PortletContainer. implemented by the host page and decorate the portlet
 * border. this will ensure a common look and feel
 * <li> PortletContext. implemented by the host page which management all
 * portlets in the page and provide a Information-Exchange-Bus for portlets
 * </ul>
 * 
 * <ul>
 * To smallize the download size of portlet, we can split a full-function into 2
 * or more portlet, each in a standalone module.
 * <li> the entry portlet, have a small download size, and provide the first UI
 * entry
 * <li> on some operation, it can jump to another portlet
 * </ul>
 */
