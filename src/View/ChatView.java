package View;

import Controller.ChatController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ChatView extends Pane{
	private static final int PANEWIDTH = 300;
	private static final int PANEHEIGHT = 275;
	private static final int CHATAREAWIDTH = 250;
	private static final int CHATAREAHEIGHT = 25;
	private static final int BUTTONWIDTH = 50;
	private static final int BUTTONHEIGHT = 25;
	
	private TextField chatArea;
	private Label recentchat;
	private Button sendChat;
	private ChatController ChatC;

	
	public ChatView(ChatController CC) {
		ChatC = CC;
		// het setten van de afmetingen van de chat pane
		this.setMinSize(PANEWIDTH, PANEHEIGHT);
		this.setPrefSize(PANEWIDTH, PANEHEIGHT);
		this.setMaxSize(PANEWIDTH, PANEHEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		// setten van de afmetingen van het textfield samen met een placeholder
		chatArea = new TextField();
		chatArea.setMinSize(CHATAREAWIDTH, CHATAREAHEIGHT);
		chatArea.setPrefSize(CHATAREAWIDTH, CHATAREAHEIGHT);
		chatArea.setMaxSize(CHATAREAWIDTH, CHATAREAHEIGHT);
		
		// setten van de afmetingen van de chat button en de button text
		sendChat = new Button();
		sendChat.setMinSize(BUTTONWIDTH, BUTTONHEIGHT);
		sendChat.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
		sendChat.setMaxSize(BUTTONWIDTH, BUTTONHEIGHT);
		sendChat.setText("Chat");
		//chatcontroller krijgt de chat message als er op de knop wordt gedrukt
		
		//sendChat.setOnAction(e -> recentchat.setText(ChatC.getRecentChat()));
		sendChat.setOnAction(e ->  getTextFromField());

		//plaatsen van de textarea en chat button in een Hbox
		HBox CABox = new HBox(chatArea, sendChat);
		CABox.setLayoutX(0);
		CABox.setLayoutY(250);

		// aanmaken van het recent chat label met afmetening
		recentchat = new Label();
		recentchat.setMinSize(600, 250);
		recentchat.setPrefSize(600, 250);
		recentchat.setMaxSize(600, 250);
		
		
		
		// plaatsen van recent chat in een Hbox voor makkelijke plaatsing
		HBox RCBox = new HBox(recentchat);
		RCBox.setLayoutX(0);
		RCBox.setLayoutY(0);
		
		// toevoegen van beide Hboxen aan de pane
		this.getChildren().addAll(CABox, RCBox);
	}
	
	public void setChatMessages(String chatMessages) {
		recentchat.setText(chatMessages);
	}

	//zorgt ervoor dat de text uit het textfield gehaald kan worden
	private void getTextFromField() {
		ChatC.getChatMessage(chatArea.getText());
	}	
}
