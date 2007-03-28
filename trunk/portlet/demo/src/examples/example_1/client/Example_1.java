package examples.example_1.client;

import portlet.client.DynamicLoadTask;
import portlet.client.PortletContext;
import portlet.client.PortletContextHolder;
import portlet.client.PortletInfo;

import com.google.gwt.user.client.Window;

public class Example_1 implements DynamicLoadTask {

	public void onModuleLoad() {
		onModuleLoadStage2();
	}
	
	public native void dynLoad() /*-{
		if ( $wnd.__count ) {
			$wnd.__count++;
		} else {
			$wnd.__this = this;
			$wnd.__func = this.@examples.example_1.client.Example_1::onModuleLoad();
			$wnd.__call = function() { $wnd.__func.call($wnd.__this); };
			$wnd.__count = 1;
		}
		$doc.title = "Count: " + $wnd.__count;
		if ($wnd.__gwt_loadModules_is_running) {
			// Try again soon.
			$wnd.setTimeout($wnd.__call, $wnd.__gwt_retryWaitMillis);
			return;
		} else {
			// this.@examples.example_1.client.Example_1::onModuleLoadStage2()(); 			
			this.@examples.example_1.client.Example_1::load(Ljava/lang/String;Ljava/lang/String;Lportlet/client/DynamicLoadTask;)("../examples.portlet_2.Portlet_2", "examples.portlet_2.Portlet_2", this);
		}
	}-*/;

	public void onModuleLoadStage2() {
		PortletInfo pollInfo = null;
		PortletContext pc = PortletContextHolder.getPortletContext();
		int count = pc.getLength();
		Window.alert("count of modules: " + count);
		for ( int i = 0; i < count; i++ ) {
			String name = pc.getPortletInfo(i).getName();
			Window.alert("registered portlet: " + name);
			if ( "poll".equals(name) ) {
				pollInfo = pc.getPortletInfo(i);
			}
		}
		//
		if ( null != pollInfo ) {
			pollInfo.create("_poll_container_1", "001");
			pollInfo.create("_poll_container_2", "002");
		}
	}

	public void onLoaded() {
		onModuleLoadStage2();
	}

	public native void load(String path, String name, DynamicLoadTask dlTask) /*-{
		$wnd.__gwt_moduleControlBlocks.blocks_ = [];
		$wnd.__gwt_moduleControlBlocks.add($doc.getElementById("__portlet_page_title"), path+"="+name);
		$wnd.__gwt_loadModules_is_running = true;
		$wnd.__gwt_loadModules();
		$wnd.__portlet_onloaded_callback = function() {
			if ($wnd.__gwt_loadModules_is_running) {
				// Try again soon.
				$wnd.setTimeout($wnd.__portlet_onloaded_callback, $wnd.__gwt_retryWaitMillis);
				return;
			} else {
				dlTask.@portlet.client.DynamicLoadTask::onLoaded()();
			}
		};
		$wnd.__portlet_onloaded_callback();
	}-*/;

}

