package ddd.gwt.client.ui;

import java.util.Iterator;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import ddd.gwt.client.model.CBeanInfo;
import ddd.gwt.client.model.CPropertyInfo;

/**
 * render a JSON object
 * 
 * layout using a Grid like <code>
 * -------------------
 * | label | content |
 * | label | content |
 * -------------------
 * </code>
 */
public class ObjectWidget extends Widget {

	private JSONObject data;

	private JSONObject dataInfo;

	/**
	 * render a JSON object
	 * 
	 * layout using a Grid like <code>
	 * -------------------
	 * | label | content |
	 * | label | content |
	 * -------------------
	 * </code>
	 */
	public Widget render(JSONObject obj, boolean edit) {

		CBeanInfo beanInfo = null; // getBeanInfo

		Grid grid = new Grid();

		Iterator iterator = obj.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			JSONValue value = obj.get(key);

			CPropertyInfo propertyInfo = beanInfo.getPropertyInfo(key);

			grid.add(new Label(propertyInfo.getLabel()));

			Widget widget = render(value, edit, propertyInfo);
			grid.add(widget);
		}
		
		// render actions as buttons

		return null;
	}

	/**
	 * render a value
	 * 
	 * @param value
	 *            the value itself
	 * @param propertyInfo
	 *            its environment information
	 * @param edit
	 *            true if edit mode
	 */
	public Widget render(JSONValue value, boolean edit,
			CPropertyInfo propertyInfo) {
		JSONArray array = value.isArray();
		JSONBoolean bool = value.isBoolean();
		JSONNull nil = value.isNull();
		JSONNumber number = value.isNumber();
		JSONObject object = value.isObject();
		JSONString string = value.isString();

		if (array != null)
			return renderArray(array, edit, propertyInfo);
		if (bool != null)
			return renderBoolean(bool, edit, propertyInfo);
		if (nil != null)
			return renderNull(nil, edit, propertyInfo);
		if (number != null)
			return renderNumber(number, edit, propertyInfo);
		if (object != null)
			return renderObject(object, edit, propertyInfo);
		if (string != null)
			return renderString(string, edit, propertyInfo);

		return null;
	}

	/**
	 * render a JSON array
	 * 
	 * layout using a Grid like <code>
	 * -------------------
	 * | 1 | content |
	 * | 2 | content |
	 * -------------------
	 * </code>
	 */

	public Widget renderArray(JSONArray array, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

	/**
	 * render as empty
	 */
	public Widget renderNull(JSONNull nil, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

	/**
	 * render as simple checkbox
	 */
	public Widget renderBoolean(JSONBoolean bool, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

	public Widget renderNumber(JSONNumber num, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

	public Widget renderString(JSONString str, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

	public Widget renderObject(JSONObject obj, boolean edit,
			CPropertyInfo propertyInfo) {
		return null;
	}

}
