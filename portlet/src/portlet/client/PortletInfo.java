package portlet.client;

import com.google.gwt.user.client.Element;


public interface PortletInfo extends Portable {
	
	String getName();
	
	void create(Element contianer, String dataId);
}
