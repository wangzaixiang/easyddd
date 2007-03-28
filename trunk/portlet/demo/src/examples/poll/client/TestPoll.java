package examples.poll.client;

import com.google.gwt.user.client.ui.RootPanel;

public class TestPoll {

	public void onModuleLoad() {
		new Poll(RootPanel.get("_poll_holder").getElement(), "002");
	}

}
