package ddd.model.impl;

import java.util.EventListener;

import ddd.model.GeneralFeature;

/**
 * 
 */
public interface EventSet {

	boolean isUnicast();

	Class<?> getSourceClass();

	Class<? extends EventListener> getListenerType();

	void addListener(Object sourceBean, EventListener listener);

	void removeListener(Object sourceBean, EventListener listener);

	EventListener[] getListeners(Object sourceBean);
}
