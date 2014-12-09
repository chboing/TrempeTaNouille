package trempe.ta.nouille.client;

import java.util.List;

import trempe.ta.nouille.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TrempeTaNouille implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "Ben ca n'a pas fonctionné ... Please retente ou laisse tomber";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final NouilleServiceAsync nouilleService = GWT.create(NouilleService.class);

	
	ListBox listUser;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox loginField = new TextBox();
		final PasswordTextBox passField = new PasswordTextBox();
		final TextBox emailField = new TextBox();
		final Label loginLabel = new Label("Déjà inscrit ?");
		final Label registerLabel = new Label("Inscription !");
		final Label errorLabel = new Label();
		loginField.getElement().setPropertyString("placeholder", "ton p'tit nom !");
		passField.getElement().setPropertyString("placeholder", "ton p'tit mot de passe (bidon pour le moment) !");
		emailField.getElement().setPropertyString("placeholder", "ton p'tit email !");

		listUser = new ListBox();
		refreshUserList();
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("loginLabel").add(loginLabel);
		RootPanel.get("loginlist").add(listUser);
		
		RootPanel.get("registerLabel").add(registerLabel);
		RootPanel.get("loginFieldContainer").add(loginField);
		RootPanel.get("passwordFieldContainer").add(passField);
		RootPanel.get("emailFieldContainer").add(emailField);
		
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		loginField.setFocus(true);
		loginField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Pouet");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Fermer");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Inscription en cours:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Réponse du serveur:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendSbuscribeRequestToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendSbuscribeRequestToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendSbuscribeRequestToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String login = loginField.getText();
				String pass = passField.getText();
				String email = emailField.getText();
				if (!FieldVerifier.isValidName(login)) {
					errorLabel.setText("Au moins 3 lettres");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(login);
				serverResponseLabel.setText("");
				nouilleService.subscribe(login, pass, email, new AsyncCallback<String>(){
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("caca - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Félicitations !");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
						refreshUserList();
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		loginField.addKeyUpHandler(handler);
	}

	private void refreshUserList(){
		nouilleService.getUserList(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onSuccess(List<String> result) {
				listUser.clear();
				for(String nouille: result){
					listUser.addItem(nouille);
				}
			}
			
		});
	}
}
