package examples.poll.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Poll {

	private Element container;
	
	//
	
	private Label lblQuestion;

	private Label lblYesCount;

	private Label lblNoCount;

	private Button btnYes;

	private Button btnNo;

	//
	
	private String pollid;

	private String question;

	private int yesCount;

	private int noCount;

	public Poll(Element container, String pollid) {
		init(container, pollid);
	}

	public Poll(String containerid, String pollid) {
		init(RootPanel.get(containerid).getElement(), pollid);
	}

	private void init(Element container, String pollid) {
		this.container = container;
		this.pollid = pollid;
		injectTemplate(container, "Poll.template");
	}
	
	// abstract
	protected void initStage2() {

		lblQuestion = new Label();
		get("_question").add(lblQuestion);

		lblYesCount = new Label();
		get("_yes_count").add(lblYesCount);

		lblNoCount = new Label();
		get("_no_count").add(lblNoCount);

		btnYes = new Button("YES");
		get("_yes_button").add(btnYes);
		btnYes.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				yesCount++;
				save();
				refresh();
			}
		});
		
		btnNo = new Button("NO");
		get("_no_button").add(btnNo);
		btnNo.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				noCount++;
				save();
				refresh();
			}			
		});

		load();

		refresh();	
	}
	
	private void refresh() {
		lblQuestion.setText(question);
		lblYesCount.setText("" + yesCount);
		lblNoCount.setText("" + noCount);
	}

	private void save() {
		Date expDate = new Date(5000000000000L);
		Cookies.setCookie(pollid, "", expDate);
		Cookies.setCookie(pollid + ".question", question, expDate);
		Cookies.setCookie(pollid + ".yescount", "" + yesCount, expDate);
		Cookies.setCookie(pollid + ".nocount", "" + noCount, expDate);
	}

	private void load() {
		String poll = Cookies.getCookie(pollid);
		if (null == poll) {
			if ( "001" == pollid ) {
				question = "Is JSF obsolete?";
				yesCount = 43;
				noCount = 9;
			} else if ( "002" == pollid ) {
				question = "Will AJAX become desktop-app development tech?";
				yesCount = 4;
				noCount = 9;
			} else {
				
			}
			save();
		}
		question = Cookies.getCookie(pollid + ".question");
		yesCount = Integer.valueOf(Cookies.getCookie(pollid + ".yescount"))
				.intValue();
		noCount = Integer.valueOf(Cookies.getCookie(pollid + ".nocount"))
				.intValue();
	}
	
	//
	// TODO: the following code should be moved to super class
	//

	protected AbsolutePanel get(String className) {
		Element elem = getByClassName(container, className);		
		class Panel extends AbsolutePanel {
			public Panel(Element elem) {
			    setElement(elem);
			    onAttach();
			}
		}
		return new Panel(elem);
	}

	protected void injectTemplate(final Element holder, String url) {
		doGet(url, new RequestCallback() {
			public void onError(Request req, Throwable ex) {
			}
			public void onResponseReceived(Request req, Response resp) {
				//initStage3();
				//Window.alert(resp.getText());
				injectTemplateStage2(holder, resp.getText());
			}
		});
	}

	private void injectTemplateStage2(Element holder, String template) {
		setInnerHTML(holder, template);
		initStage2();
	}

	private native Element getByClassName(Element elem, String className)/*-{
		var findElementByClassName = function(baseElements, className, maxDepth) {
			if ( ! ( baseElements instanceof Array ) )
				baseElements = [baseElements];			
			if ( 0 === baseElements.length )
				return null;
			var re = new RegExp("( |\t|^)" + className + "( |\t|$)");
			var nextBaseElements = [];
			for ( var i = 0; i < baseElements.length; i++ ) {
				var baseElement = baseElements[i];
				if ( !baseElement.tagName )
					continue;
				if ( re.test(baseElement.className) )
					return baseElement;
				for ( var j = 0; j < baseElement.childNodes.length; j++ )
					nextBaseElements.push(baseElement.childNodes[j]);
			}
			if ( "undefined" === typeof maxDepth )
				return findElementByClassName(nextBaseElements, className);
			else if ( maxDepth > 0 )
				return findElementByClassName(nextBaseElements, className, maxDepth-1);
			else
				return null;
		};
		var ret = findElementByClassName(elem, className);
		return ret;
	}-*/;
	
	private void doGet(String url, RequestCallback callback) {
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			Request response = builder.sendRequest(null, callback);
		} catch (RequestException e) {
		}
	}
	
	private native void setInnerHTML(Element holder, String html) /*-{
		holder.innerHTML = html;
	}-*/;
}
