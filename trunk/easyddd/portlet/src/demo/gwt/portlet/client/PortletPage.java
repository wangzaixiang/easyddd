package demo.gwt.portlet.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * An PortletPage can host one or more portlets
 * 
 * When an PortletPage startup, it will setup PortletContext and then parse the
 * host page, looking for all div withing portlet attribute which has the
 * following style: <code>
 * portlet="com.foo.portlet.moduleName;arg1,arg2,arg3"
 * </code>
 * also it looks for a div with id="PortletPageMainArea" which will acts as the
 * maximum area. when a portlet is in maximum mode, all contents inside will be
 * hidded and the content will be used to display the maximum portlet
 */
public class PortletPage implements PortletContext, EntryPoint {
	
	private	Element portletPageMainArea;
	private	List portletInfos = new ArrayList();
	private List portlets = new ArrayList();

	public void onModuleLoad() {

	}

	public Portlet getPortlet(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PortletInfo getPortletInfo(String moduleName) {
		return null;
	}

	public Portlet[] getPortletOfModule(String module) {
		return null;
	}

	public void loadPortletModule(String moduleName) {

	}

	public void notifyEvent(String module, String portletId, String category,
			JavaScriptObject content) {

	}

	public void register(Portlet portlet) {

	}

	public void registerEventListener(String fromModule, String fromPortlet,
			String fromCategory, String destPortletId) {

	}

	public void registerPortletInfo(PortletInfo info) {

	}

	public void setMaxinum(Portlet portlet) {

	}

	public void unregister(Portlet portlet) {

	}

}
