package portlet.client.impl;

import portlet.client.PortletFrame;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class DraggablePortletFrame implements PortletFrame {

	public Element getInnerElement() {
		if (null == innerElement) {
			throw new RuntimeException("do render before getInnerElemnet");
		}
		return innerElement;
	}

	public void render(Element container) {
		this.container = container;
		if (null == element)
			init(this.container);
		else
			throw new RuntimeException("can not render the second time");
	}

	private void init(Element container) {

		// creat element
		element = DOM.createDiv();
		DOM.appendChild(container, element);
		DOM.setStyleAttribute(element, "position", "absolute");
		DOM.setStyleAttribute(element, "width", getOuterWidth() + "px");
		DOM.setStyleAttribute(element, "height", getOuterHeight() + "px");
		DOM.setStyleAttribute(element, "backgroundColor", getBorderColor());

		// create title element
		title = DOM.createDiv();
		DOM.appendChild(element, title);
		DOM.setStyleAttribute(title, "position", "absolute");
		DOM.setStyleAttribute(title, "width", getInnerWidth() + "px");
		DOM.setStyleAttribute(title, "height", getTitleHeight() + "px");
		DOM.setStyleAttribute(title, "left", getBorderWidth() + "px");
		DOM.setStyleAttribute(title, "top", getBorderWidth() + "px");
		DOM.setStyleAttribute(title, "backgroundColor", "white");
		
		// create inner element
		innerElement = DOM.createDiv();
		DOM.appendChild(element, innerElement);
		DOM.setStyleAttribute(innerElement, "position", "absolute");
		DOM.setStyleAttribute(innerElement, "width", getInnerWidth() + "px");
		DOM.setStyleAttribute(innerElement, "height", getInnerHeight() + "px");
		DOM.setStyleAttribute(innerElement, "left", getBorderWidth() + "px");
		DOM.setStyleAttribute(innerElement, "top", ( getBorderWidth() + getTitleHeight() + getGap() ) + "px");
		DOM.setStyleAttribute(innerElement, "backgroundColor", "white");
	}

	private Element element;

	private Element title;

	private Element innerElement;

	private Element container;

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
}
