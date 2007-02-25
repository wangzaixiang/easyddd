package ddd.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 
 */
public interface Method {
	
	List<Parameter> getParameters();

	Object invoke(Object... parameters) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException;
}
