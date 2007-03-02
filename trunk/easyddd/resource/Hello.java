/*
 * Copyright 2006 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.hello.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * HelloWorld application.
 */
public class Hello implements EntryPoint {
	
	public interface IFace1 {
		IFace2 createIFace2(String name);
	}
	
	public interface IFace2 {
		String getName();
	}
	
	static class Face2 implements IFace2 {

		public String getName() {
			return "wangzx";
		}
		
	}
	
	static class Face1 implements IFace1 {

		public IFace2 createIFace2(String name) {
			return new Face2();
		}
		
	}
	
	static class Face2Stub implements IFace2 {
		
		private JavaScriptObject jso;

		public Face2Stub(JavaScriptObject jso){
			this.jso = jso;
		}
		
		public native String getName() /*-{
			return this.@com.google.gwt.sample.hello.client.Hello$Face2Stub::jso.getName();
		}-*/;
		
	}
	
	static class Util {
		
		public static native JavaScriptObject export(IFace2 f2) /*-{
			var o = {};
			o.getName = function(){
				return f2.@com.google.gwt.sample.hello.client.Hello$IFace2::getName()();
			}
			return o;
		}-*/;
		
		public static IFace2 importIFace2(JavaScriptObject jso){
			return new Face2Stub(jso);
		} ;
	}

  public void onModuleLoad() {
    Button b = new Button("Click me", new ClickListener() {
      public void onClick(Widget sender) {
    	  Face2 face2 = new Face2();
    	  JavaScriptObject export = Util.export(face2);
    	  
    	  dump(export);
    	  
    	  IFace2 f2 = Util.importIFace2(export);
    	  Window.alert("name = " + f2.getName());
      }
    });

    RootPanel.get().add(b);
  }
  
  private native JavaScriptObject createSimple() /*-{
  	return {
  		name: "wangzx",
  		age: 33
  	};
  }-*/;
  
  private native void dump(Object jso) /*-{
	  for(i in jso){
	  	alert(i + "=" + jso[i]);
	  }
  }-*/;
  
}
