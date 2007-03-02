package demo.gwt.portlet.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;

public class PollModule implements EntryPoint {

	static class PollPortletInfo implements PortletInfo {

		public Portlet create(String[] args, PortletContainer container) {
			return new PollPortlet(args, container);
		}

		public String getIconURL() {
			return null;
		}

		public String getModuleName() {
			return "demo.gwt.portlet.client.PollPortlet";
		}

		public String getPortletDescription() {
			return "Online Poll Example";
		}

		public String getPortletName() {
			return "Poll";
		}

	}

	static class PollPortlet implements Portlet {

		private final String[] args;

		private final PortletContainer container;

		private String mode;

		public PollPortlet(String[] args, PortletContainer container) {
			this.args = args;
			this.container = container;
		}

		public PortletContainer getContainer() {
			return container;
		}

		public String getMode() {
			return mode;
		}

		public String getTitle() {
			return "Poll";
		}

		public void renderBody(Element container) {
			if(Portlet.MODE_NORMAL.equals(mode)){
				renderNormal(container);
			}
			else if(Portlet.MODE_MAXIMUM.equals(mode)){
				renderMaximum(container);
			}
		}

		private void renderMaximum(Element container2) {
			
		}

		private void renderNormal(Element container2) {
			// show a poll form which list all options
			// button: "Vote", "See Results"
			// When Vote complete, automate switch to maximum mode and
			// display current result;
			getContainer().getPortletContext().setMaxinum(this);
		}

		public void renderFrame(Element container) {
			this.container.renderPortletFrame(container);

		}

		public void setMode(String mode) {
			this.mode = mode;
		}

	}

	public void onModuleLoad() {

		PortletContext pCtx = null;
		// pCtx = Utils.doImport( $wnd.__gwt_portlet_context__ );

		PollPortletInfo info = new PollPortletInfo();
		pCtx.registerPortletInfo(info);
	}

}
