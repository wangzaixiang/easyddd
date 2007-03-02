/**
 * 
 */
package demo.gwt.portlet.client;

import com.google.gwt.core.client.JavaScriptObject;

public class PortletContextStub implements PortletContext {

	private final JavaScriptObject jsObject;

	public PortletContextStub(JavaScriptObject jso) {
		this.jsObject = jso;
	}

	public void register(Portlet portlet) {
		
		JavaScriptObject exported = Utils.doExport(portlet);
		register0(exported);
		
	};

	public native void register0(JavaScriptObject jso) /*-{
	 
	 this.@PortletContext::jso.add( );
	 } -*/;

	public Portlet getPortlet(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PortletInfo getPortletInfo(String moduleName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Portlet[] getPortletOfModule(String module) {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadPortletModule(String moduleName) {
		// TODO Auto-generated method stub

	}

	public void notifyEvent(String module, String portletId, String category,
			JavaScriptObject content) {
		// TODO Auto-generated method stub

	}

	public void registerEventListener(String fromModule, String fromPortlet,
			String fromCategory, String destPortletId) {
		// TODO Auto-generated method stub

	}

	public void unregister(Portlet portlet) {
		// TODO Auto-generated method stub

	}

	public void setMaxinum(Portlet portlet) {
		// TODO Auto-generated method stub

	}

	public void registerPortletInfo(PortletInfo info) {
		// TODO Auto-generated method stub
		
	}

}