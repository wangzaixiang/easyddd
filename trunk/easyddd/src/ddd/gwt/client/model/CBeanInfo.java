package ddd.gwt.client.model;

import com.google.gwt.json.client.JSONObject;

public class CBeanInfo extends JSONWrapper {

	public CBeanInfo(JSONObject json) {
		super(json);
	}

	public CPropertyInfo getPropertyInfo(String key) {
		return null;
	}
}
