package ddd.model.impl;

import java.beans.Customizer;
import java.util.List;

import ddd.model.Feature;

public interface Bean {

	public @interface Info {

		Class beanClass() default Void.class;

		Class customizerClass() default Void.class;

	}

	<T extends Feature> T getFeature(Class<T> featureType);

	Class getBeanClass();

	/**
	 * if the bean has a customizer, return it.
	 */
	Class<Customizer> getCustomizerClass();

	/**
	 * if the bean is hierachy aware, it should have a property linked to its
	 * parent.
	 */
	Property getParentProperty();

	/**
	 * return the Property collection, except for Child property
	 */
	List<Property> getProperties();

	Property getDefaultProperty();

	/**
	 * return the child collection, exception for normal property
	 */
	List<Property> getChildren();

	List<Method> getMethods();

	List<EventSet> getEvents();

}
