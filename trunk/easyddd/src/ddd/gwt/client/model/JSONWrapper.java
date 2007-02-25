package ddd.gwt.client.model;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class JSONWrapper {

	protected final JSONObject json;

	public JSONWrapper(JSONObject json) {
		this.json = json;
	}
	
	protected String getString(String key){
		JSONString string = json.get(key).isString();
		if(string != null)
			return string.stringValue();
		else
			return null;
	}
	
	protected boolean getBoolean(String key){
		JSONBoolean bool = json.get(key).isBoolean();
		if(bool != null)
			return bool.booleanValue();
		else
			return false;
	}
	
	protected double getInt(String key){
		JSONNumber num = json.get(key).isNumber();
		if(num != null)
			return num.getValue();
		else
			return 0;
	}
	
}
