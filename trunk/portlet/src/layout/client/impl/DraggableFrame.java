package layout.client.impl;

import layout.client.Frame;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;

public class DraggableFrame implements Frame, EventListener {

	public Element getBodyElement() {
		return bodyElement;
	}

	public DraggableFrame(DraggableLayoutManager dlm) {

		this.layoutManager = dlm;
		this.container = dlm.getElement();

		// creat element
		element = DOM.createDiv();
		DOM.appendChild(this.container, element);
		DOM.setStyleAttribute(element, "position", "absolute");
		DOM.setStyleAttribute(element, "width", getOuterWidth() + "px");
		DOM.setStyleAttribute(element, "height", getOuterHeight() + "px");
		DOM.setStyleAttribute(element, "left", "0px");
		DOM.setStyleAttribute(element, "top", "0px");
		DOM.setStyleAttribute(element, "backgroundColor", getBorderColor());
		DOM.setStyleAttribute(element, "borderColor", getBorderColor());
		DOM.setStyleAttribute(element, "borderStyle", "solid");
		DOM.setStyleAttribute(element, "borderWidth", "0px");

		// create title element
		title = DOM.createDiv();
		DOM.appendChild(element, title);
		DOM.setStyleAttribute(title, "position", "absolute");
		DOM.setStyleAttribute(title, "width", getInnerWidth() + "px");
		DOM.setStyleAttribute(title, "height", getTitleHeight() + "px");
		DOM.setStyleAttribute(title, "left", getBorderWidth() + "px");
		DOM.setStyleAttribute(title, "top", getBorderWidth() + "px");
		DOM.setStyleAttribute(title, "backgroundColor", "white");

		//
		DOM.sinkEvents(title, Event.ONMOUSEDOWN);
		DOM.setEventListener(title, this);
		DOM.sinkEvents(element, Event.ONMOUSEDOWN);
		DOM.setEventListener(element, this);

		// create inner element
		bodyElement = DOM.createDiv();
		DOM.appendChild(element, bodyElement);
		DOM.setStyleAttribute(bodyElement, "position", "absolute");
		DOM.setStyleAttribute(bodyElement, "width", getInnerWidth() + "px");
		DOM.setStyleAttribute(bodyElement, "height", getInnerHeight() + "px");
		DOM.setStyleAttribute(bodyElement, "left", getBorderWidth() + "px");
		DOM.setStyleAttribute(bodyElement, "top", (getBorderWidth()
				+ getTitleHeight() + getGap())
				+ "px");
		DOM.setStyleAttribute(bodyElement, "backgroundColor", "white");

		//
		moveTask = new MoveTask(element, this.layoutManager);
	}

	public void onBrowserEvent(Event event) {
		if (Event.ONMOUSEDOWN == DOM.eventGetType(event)) {
			final Element fromElement = DOM.eventGetTarget(event);
			if (fromElement.equals(title)) {
				moveTask.activate();
			}
			if (!isCurrentFrame) {
				setAsCurrentFrame(true);
			}
		}
	}

	private void setAsCurrentFrame(boolean asCurrent) {
		isCurrentFrame = asCurrent;
		if (asCurrent) {
			DraggableFrame currentFrame = ((DraggableFrame) layoutManager
					.getCurrentFrame());
			if (null != currentFrame)
				currentFrame.setAsCurrentFrame(false);
			DOM.setStyleAttribute(element, "borderWidth", "1px");
			DOM.setStyleAttribute(element, "zIndex", "1");
			layoutManager.setCurrentFrame(this);
		} else {
			DOM.setStyleAttribute(element, "borderWidth", "0px");
			DOM.setStyleAttribute(element, "zIndex", "0");
			layoutManager.setCurrentFrame(null);
		}
	}

	private Task moveTask;

	private Element element;

	private Element title;

	private Element bodyElement;

	private Element container;

	private DraggableLayoutManager layoutManager;

	// config

	private int getTitleHeight() {
		return titleHeight;
	}

	private int getInnerHeight() {
		return height;
	}

	private int getInnerWidth() {
		return width;
	}

	private int getOuterHeight() {
		return height + borderWidth * 2 + gap + titleHeight;
	}

	private int getOuterWidth() {
		return width + borderWidth * 2;
	}

	private String getBorderColor() {
		return borderColor;
	}

	private int getBorderWidth() {
		return borderWidth;
	}

	private int getGap() {
		return gap;
	}

	private int width = 400;

	private int height = 300;

	private int borderWidth = 2;

	private int titleHeight = 20;

	private int gap = 2;

	private String borderColor = "brown";

	private boolean isCurrentFrame = false;
}
