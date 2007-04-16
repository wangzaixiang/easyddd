package portlet.client.impl;

import portlet.client.PortletFrame;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class DraggablePortletFrame implements PortletFrame {

	public Element getBodyElement() {		
		return bodyElement;
	}

	public DraggablePortletFrame(Element container) {

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
		bodyElement = DOM.createDiv();
		DOM.appendChild(element, bodyElement);
		DOM.setStyleAttribute(bodyElement, "position", "absolute");
		DOM.setStyleAttribute(bodyElement, "width", getInnerWidth() + "px");
		DOM.setStyleAttribute(bodyElement, "height", getInnerHeight() + "px");
		DOM.setStyleAttribute(bodyElement, "left", getBorderWidth() + "px");
		DOM.setStyleAttribute(bodyElement, "top", ( getBorderWidth() + getTitleHeight() + getGap() ) + "px");
		DOM.setStyleAttribute(bodyElement, "backgroundColor", "white");
	}

	private Element element;

	private Element title;

	private Element bodyElement;

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
