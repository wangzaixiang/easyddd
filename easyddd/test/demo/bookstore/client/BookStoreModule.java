package demo.bookstore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ddd.gwt.client.model.CBeanInfo;
import ddd.gwt.client.service.DomainGatewayAsync;
import ddd.gwt.client.ui.SimpleDataWidget;

public class BookStoreModule implements EntryPoint {

	public void onModuleLoad() {

		/**
		 * get a GWT Remote Service
		 */
		DomainGatewayAsync gateway = null;
	
		gateway.queryBeanInfo("demo.bookstore.BookStore", new AsyncCallback() {
		
			public void onSuccess(Object result) {
				String json = (String)result;
				
				CBeanInfo cbi = null;	// convert from json
				
				SimpleDataWidget dataWidget = new SimpleDataWidget();
				dataWidget.setDataModel((JSONValue) result);
				
				// display the dataWidget;
			}
		
			public void onFailure(Throwable caught) {
		
			}
		
		});
		
	}

}
