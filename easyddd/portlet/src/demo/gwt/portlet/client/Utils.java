package demo.gwt.portlet.client;

import com.google.gwt.core.client.JavaScriptObject;


public class Utils {

	/**
	 * 
	 */
	public static native JavaScriptObject export(PortletInfo ctx) /*-{
	 var result = {};
	 result.moduleName = ctx.@PortletContext::moduleName;
	 result.funcCreate = function(args, container){
	 	ctx.@Port
	 }
	 } -*/;

	public static native PortletInfo importPortletInfo(JavaScriptObject js) /*-{
	 
	 } -*/;

	public static JavaScriptObject doExport(Portlet portlet) {
		return null;
	}

}
