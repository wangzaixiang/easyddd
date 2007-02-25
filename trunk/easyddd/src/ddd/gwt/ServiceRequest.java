package ddd.gwt;

import com.google.gwt.user.client.rpc.IsSerializable;

import ddd.EntityService;
import ddd.Service;

/**
 * Represents a client request
 * 
 */
public class ServiceRequest implements IsSerializable{

	/**
	 * qualified class name, it may be:
	 * <ul>
	 * <li> a class which implements {@link Service}
	 * <li> a class which implements {@link EntityService}
	 * <li> a class which reprents an Entity
	 * </ul>
	 */
	String serviceId;

	/**
	 * the action name
	 */
	String actionId;

	/**
	 * each element of the array is a JSON as the representation.
	 * 
	 * for entity service, args[0] is the entity Id.
	 */
	String args[];

}
