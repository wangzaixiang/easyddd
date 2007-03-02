package demo.gwt.portlet.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;

public class PortletWidget extends SimplePanel implements PortletContainer {

	private String moduleName;

	/**
	 * this is an exported Javascript object which can be accessed from inside
	 * portlet
	 */
	private JavaScriptObject jsPortletFrameAPI;

	private Portlet peer;

	public PortletWidget(String moduleName) {

		this.moduleName = moduleName;

		loadModule();

	}

	/**
	 * load the module if needed. likes gwt.js, it will:
	 * <ul>
	 * <li> inject an iframe to load the module.nocache.html
	 * <li> active the Module EntryPoints
	 * </ul>
	 * 
	 * a Portlet module should register a JavaScript instance as <code>
	 * 	$wnd.registerPortletType(moduleName, {
	 * 		portletType: moduleName,
	 * 		createPortlet: function(args, frame){
	 * 			// implementation which actually create a new portlet instance
	 * 		}
	 * 	});
	 * </code>
	 */
	private void loadModule() {
	}

	public Portlet getPortlet() {
		return peer;
	}

	/**
	 * default operation to render the PortletFrame, called by
	 * {@see Portlet#renderFrame(Element)} if it do not custom render
	 */
	public void renderPortletFrame(Element container) {

	}

	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public PortletContext getPortletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPortletId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void replaceWith(String moduleName, String[] args) {
		// TODO Auto-generated method stub
		
	}

}
