package portlet.client;

public interface PortletInfo extends Portable {

	String getName();

	Portlet create(String args);
}
