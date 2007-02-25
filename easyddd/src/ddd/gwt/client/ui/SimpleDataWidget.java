package ddd.gwt.client.ui;

import com.google.gwt.json.client.JSONValue;

import ddd.gwt.client.service.DomainGatewayAsync;

/**
 * a XML style widget to display or edit an JSON object
 * 
 * <ul>
 * map rules:
 * <li> a {name:value} object is mapped to a 2 column grid, the label and the
 * content. this object can be collapse or expand.
 * <li> a [a,b,c] array is mapped to a 2 column grid, the label is 0,1,2 etc.
 * in edit mode, a '+' after each row means add a empty row after this row.
 * </ul>
 * 
 */
public class SimpleDataWidget implements DataWidget {
	
	private	JSONValue	data;		// the data to be display
	private	JSONValue	dataModel;	// the dataModel

	public JSONValue getDataModel() {
		return null;
	}

	public boolean isEditMode() {
		return false;
	}

	public void setDataModel(JSONValue dataModel) {

	}

	public void setDomainGateway(DomainGatewayAsync gateway) {

	}

	public void setEditMode(boolean editMode) {

	}

}
