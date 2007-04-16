package layout.client.impl;

import layout.client.Frame;
import layout.client.LayoutManager;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;

public class DraggableLayoutManager implements LayoutManager, TaskAgent {

	public DraggableLayoutManager(Element container) {
		this.container = container;

		// creat element
		element = DOM.createDiv();
		DOM.appendChild(this.container, element);
		DOM.setStyleAttribute(element, "position", "absolute");
		DOM.setStyleAttribute(element, "width", "100%");
		DOM.setStyleAttribute(element, "height", "100%");
		DOM.setStyleAttribute(element, "borderStyle", "solid");
		DOM.setStyleAttribute(element, "borderWidth", "2px");
		DOM.setStyleAttribute(element, "borderColor", "yellow");

		DOM.sinkEvents(element, Event.ONMOUSEDOWN | Event.ONMOUSEUP
				| Event.ONMOUSEMOVE);

		DOM.setEventListener(element, new EventListener() {

			public void onBrowserEvent(Event event) {
				if (null == currentTask)
					return;
				switch (DOM.eventGetType(event)) {
				case Event.ONMOUSEDOWN:
					currentTask.onMouseDown(event);
					break;
				case Event.ONMOUSEUP:
					currentTask.onMouseUp(event);
					break;
				case Event.ONMOUSEOVER:
					currentTask.onMouseOver(event);
					break;
				case Event.ONMOUSEOUT:
					currentTask.onMouseOut(event);
					break;
				case Event.ONMOUSEMOVE:
					currentTask.onMouseMove(event);
					break;
				}
			}

		});
	}

	public void setCurrentTask(Task task) {
		currentTask = task;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public Frame getFrame(String name) {
		Frame frame = new DraggableFrame(this);
		return null;
	}

	Element getElement() {
		return element;
	}

	private Element container;

	private Element element;

	private Task currentTask;

}
