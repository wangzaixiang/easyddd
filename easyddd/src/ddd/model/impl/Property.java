package ddd.model.impl;

import java.beans.PropertyEditor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import ddd.model.Feature;

/**
 * 
 */
public interface Property {

	public @interface Info {

		boolean bound() default false;

		boolean isDefault() default false;

		boolean child() default false;

		boolean constrained() default false;

		Class<PropertyEditor> editor() default PropertyEditor.class;

		Class elementType() default Void.class;

		boolean indexed() default false;

		Class indexType() default Void.class;

		boolean mapped() default false;

		Class mapKeyType() default Void.class;

	}

	/**
	 * annote a method as a getter, Getter can be used to mark property, indexed
	 * property's getter method
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Getter {

		/**
		 * if true, the method get the count of an indexed property, otherwise,
		 * undefined
		 */
		boolean count() default false;

		/**
		 * true if the method return the keySet of an mapped property.
		 */
		boolean keySet() default false;

		/**
		 * general, getAge() is the getter for property <code>age</code>. but
		 * you can override it.
		 */
		String value() default "";
	}

	/**
	 * annote a method as a setter, Setter can be used to mark property, indexed
	 * property's setter method
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Setter {

		/**
		 * if true, this method does a insert operation, otherwise, undefined
		 */
		boolean insert() default false;

		/**
		 * if true, this method remove a element. false means undefined.
		 */
		boolean remove() default false;

		/**
		 * general, setAge() is the setter for propertyt <code>age</code>.
		 * but you can override it.
		 */
		String value() default "";
	}

	/**
	 * an Indexed Property
	 */
	public interface Indexed<T> extends Property {

		/**
		 * add an element at the given index and shift element right
		 * 
		 * default mapped to method matches
		 * <code>add#PropertyName#(int index, T value)</code> or
		 * <code>set#PropertyName#(T[] array)</code>
		 */
		void add(int index, T value);

		/**
		 * indexed getter.
		 * 
		 * default mapped to method matches
		 * <code>get#PropertyName#(int index)</code>, or equals as
		 * <code>get#PropertyName#()[index]</code>
		 */
		T get(int index);

		/**
		 * the element type
		 */
		Class getElementType();

		/**
		 * remove an element.
		 * 
		 * <ul>
		 * if there is a getter returns an Array object, and a setter with an
		 * Array object, remove can be implemented as
		 * <li>Array get();
		 * <li>copy from the array without copy the element at the index
		 * <li>set(Array), write it back
		 * </ul>
		 * 
		 * default mapped to method matches
		 * <code>remove#PropertyName#(int index)</code> or
		 * <code>set#PropertyName#(T[] array)</code>
		 */
		void remove(int index);

		/**
		 * indexed setter
		 * 
		 * default mapped to method matches
		 * <code>set#PropertyName#(int index, T value)</code> or
		 * <code>set#PropertyName#(T[] array)</code>
		 */
		void set(int index, T value);

		/**
		 * default mapped to <code>get#PropertyName#Count</code> or
		 * <code>get#PropertyName#().length</code>
		 */
		int size();
	}

	/**
	 * a Mapped property
	 */
	public interface Mapped<K, T> extends Property {

		T get(K key);

		Class getElementType();

		Class getIndexType();

		Set<K> keySet();

		void remove(K key);

		void set(K key, T value);
	}

	public <T extends Feature> T getFeature(Class<T> featureType);

	/**
	 * for a indexed property, the propertyType is the Array/List itself, and to
	 * access the element type, using {@link Indexed#getElementType()} or
	 * {@link Mapped#getElementType()}
	 */
	Class getPropertyType();

	/**
	 * Soja support Hierarchy concept. A child property makes the child nodes of
	 * the parent nodes.
	 * 
	 */
	boolean isChild();

	/**
	 * Updates to "bound" properties will cause a "PropertyChange" event to get
	 * fired when the property is changed.
	 */
	boolean isBound();

	/**
	 * Attempted updates to "Constrained" properties will cause a
	 * "VetoableChange" event to get fired when the property is changed.
	 */
	boolean isConstrainted();

	/**
	 * return a specical PropertyEditor class, or null for default.
	 */
	Class<PropertyEditor> getEditorClass();

	/**
	 * read the property value from the source bean. return the property or a
	 * wrap object for a primitive type.
	 */
	Object get() throws InvocationTargetException, PropertyNotReadableException;

	/**
	 * write the property.
	 */
	void set(Object value) throws InvocationTargetException,
			PropertyNotWritableException;
}
