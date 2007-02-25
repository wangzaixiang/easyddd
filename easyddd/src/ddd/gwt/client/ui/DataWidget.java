package ddd.gwt.client.ui;

import com.google.gwt.json.client.JSONValue;

import ddd.gwt.client.service.DomainGatewayAsync;

public interface DataWidget {

	boolean isEditMode();

	void setEditMode(boolean editMode);

	JSONValue getDataModel();

	void setDataModel(JSONValue dataModel);

	void setDomainGateway(DomainGatewayAsync gateway);
}
