package layout.client.impl;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class Task {

	public Task(Element element, TaskAgent agent) {
		this.element = element;
		this.agent = agent;
	}
	
	public void activate() {
		agent.setCurrentTask(this);
	}

	protected void passivate() {
		agent.setCurrentTask(null);
	}
		
	public void onMouseDown(Event event) {
		Window.alert("in move task");
	}

	public void onMouseMove(Event event) {
		// TODO Auto-generated method stub
		
	}

	public void onMouseOut(Event event) {
		// TODO Auto-generated method stub
		
	}

	public void onMouseOver(Event event) {
		// TODO Auto-generated method stub
		
	}

	public void onMouseUp(Event event) {
		// TODO Auto-generated method stub
		
	}

	protected Element element;
	protected TaskAgent agent;
}
