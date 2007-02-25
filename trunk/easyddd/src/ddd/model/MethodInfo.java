package ddd.model;

import java.util.ArrayList;
import java.util.List;

public class MethodInfo extends GeneralFeature {

	private static final long serialVersionUID = 1604551964095641944L;

	private	List<Feature> methodFeatures = new ArrayList<Feature>();
	
	private List<PropertyInfo> parameters;

	public List<PropertyInfo> getParameters() {
		return parameters;
	}

	public void setParameters(List<PropertyInfo> parameters) {
		this.parameters = parameters;
	}

	public List<Feature> getMethodFeatures() {
		return methodFeatures;
	}

	public void setMethodFeatures(List<Feature> methodFeatures) {
		this.methodFeatures = methodFeatures;
	}
}
