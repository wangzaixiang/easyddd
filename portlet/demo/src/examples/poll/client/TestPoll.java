package examples.poll.client;

import com.google.gwt.user.client.DOM;

public class TestPoll {

	public void onModuleLoad() {
		Poll poll = new Poll("002");
		poll.render(DOM.getElementById("_poll_holder"));
	}

}
