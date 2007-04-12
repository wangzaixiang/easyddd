package portlet.client;

import com.google.gwt.user.client.Element;

public interface PortletFrame extends Portable {
	Element getInnerElement();
	void render(Element container);
}
