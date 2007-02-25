package ddd.gwt.client.model;

import com.google.gwt.json.client.JSONObject;

public class CGeneralFeature extends JSONWrapper {

	public CGeneralFeature(JSONObject json) {
		super(json);
	}

	public String getCategory() {
		return getString("category");
	}

	public String getName() {
		return getString("name");
	}

	public String getDisplayName() {
		return getString("displayName");
	}

	public boolean isExpert() {
		return getBoolean("isExpert");
	}

	public boolean isHidden() {
		return getBoolean("isHidden");
	}

	public boolean isPreferred() {
		return getBoolean("isPreferred");
	}

	public String getShortDescription() {
		return getString("shortDescription");
	}

	public String getLongDescription() {
		return getString("longDescription");
	}

	public String getHelpId() {
		return getString("helpId");
	}

	public String getSmallIcon() {
		return getString("smallIcon");
	}

	public String getLargeIcon() {
		return getString("largeIcon");
	}

}
