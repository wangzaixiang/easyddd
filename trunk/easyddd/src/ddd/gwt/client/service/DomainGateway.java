package ddd.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;

import ddd.gwt.client.model.CBeanInfo;

public interface DomainGateway extends RemoteService {

	/**
	 * 
	 * @param serviceId
	 *            the qualified classname of the service
	 * @param actionId
	 *            the action name
	 * @param args
	 *            the action arguments, in json format
	 * @return the result in json format
	 */
	String invokeDomainService(String serviceId, String actionId, String[] args);

	/**
	 * return the JSON representation of BeanInfo
	 */
	CBeanInfo queryBeanInfo(String serviceId, String objectId);
	
}
