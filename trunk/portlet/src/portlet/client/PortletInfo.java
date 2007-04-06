package portlet.client;


public interface PortletInfo extends Portable {
	
	String getName();
	
	void create(String containerId, String dataId);
}
