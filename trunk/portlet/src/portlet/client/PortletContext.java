package portlet.client;


public interface PortletContext extends Portable {

	void register(PortletInfo portletInfo);
	
	int getLength();
	
	PortletInfo getPortletInfo(int index);
}
