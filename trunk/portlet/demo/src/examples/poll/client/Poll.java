package examples.poll.client;

import java.util.Date;

import portlet.client.Portlet;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Poll implements Portlet {

	private AbsolutePanel container;
	
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

	public Poll(String args) {
		this.pollid = args;
	}
	
	public void render(final Element container) {
		init(new AbsolutePanel() {
			{
			    setElement(container);
			    onAttach();
			}
		});
	}

	//
	//--private methods begin
	//
	
	private void init(AbsolutePanel container) {
		this.container = container;
		this.initStage2();
	}
	
	private void initStage2() {

		lblQuestion = new Label();
		this.container.add(lblQuestion);

		lblYesCount = new Label();
		this.container.add(lblYesCount);

		lblNoCount = new Label();
		this.container.add(lblNoCount);

		btnYes = new Button("YES");
		this.container.add(btnYes);
		btnYes.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				yesCount++;
				save();
				refresh();
			}
		});
		
		btnNo = new Button("NO");
		this.container.add(btnNo);
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

}
