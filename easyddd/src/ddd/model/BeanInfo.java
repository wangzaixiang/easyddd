package ddd.model;

import java.util.List;

public class BeanInfo extends GeneralFeature {

	private static final long serialVersionUID = 2033602152924869073L;

	private	List<Feature>	beanFeatures;

	private	List<PropertyInfo>	properties;
	
	private	List<MethodInfo>	methods;

	public List<Feature> getBeanFeatures() {
		return beanFeatures;
	}

	public void setBeanFeatures(List<Feature> beanFeatures) {
		this.beanFeatures = beanFeatures;
	}

	public List<MethodInfo> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodInfo> methods) {
		this.methods = methods;
	}

	public List<PropertyInfo> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyInfo> properties) {
		this.properties = properties;
	}
	
}
