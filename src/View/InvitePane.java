package View;

import Controller.InviteController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InvitePane extends HBox {
	
	private static final int BUTTONWHIDTH = 150;
	private static final int BUTTONHEIGHT = 30;
	private static final int TEXTWHIDTH = 300;
	private static final int TEXTHEIGHT = 100;
	private static final int COMBOBOXHEIGHT = 50;
	private static final int TEXT = 20;
	
	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;

	private VBox vbox;
	private VBox vbox2;
	private VBox vbox3;
	private VBox vbox4;

	private TextField gameId;

	private Label titel1;
	private Label titel2;
	private Button inviteButton;

	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private ToggleGroup buttons;
	
	private ComboBox<String> players1;
	private ComboBox<String> players2;
	private ComboBox<String> players3;

	private Button accept;
	private Button refuse;
	private HBox hbox2;

	private ImageView labelBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	private ImageView labelNameBackground = new ImageView(new Image("Resources/Label_uitnodigen.png"));
	
	private ComboBox<String> invites;
	private InviteController inviteController;

	public InvitePane(InviteController con) {
		this.inviteController = con;
		this.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		createPane();
		showInvite();
		rb2.setOnAction(e -> {
			players1.setVisible(true);
			players2.setVisible(false);
			players3.setVisible(false);

		});
		rb3.setOnAction(e -> {
			players1.setVisible(true);
			players2.setVisible(true);
			players3.setVisible(false);
;
		});
		rb4.setOnAction(e -> {
			players1.setVisible(true);
			players2.setVisible(true);
			players3.setVisible(true);

		});
	}

	private void createPane() {

		labelBackground.setFitWidth(TEXTWHIDTH);
		labelBackground.setFitHeight(TEXTHEIGHT);
		titel1 = new Label("vrienden uitnodigen", labelBackground);
		titel1.setFont(new Font("Arial", TEXT));
		titel1.setStyle("-fx-font-weight: bold");
		titel1.setContentDisplay(ContentDisplay.CENTER);

		buttons = new ToggleGroup();
		rb2 = new RadioButton("twee");
		rb2.setTextFill(Color.BLACK);
		rb2.setToggleGroup(buttons);
		rb2.setSelected(true);
		rb2.setUserData("twee");

		rb3 = new RadioButton("drie");
		rb3.setTextFill(Color.BLACK);
		rb3.setToggleGroup(buttons);
		rb3.setUserData("drie");

		rb4 = new RadioButton("vier");
		rb4.setTextFill(Color.BLACK);
		rb4.setToggleGroup(buttons);
		rb4.setUserData("vier");

		players1 = new ComboBox<String>();
		players1.setVisible(true);
		players1.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players1.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players1.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);
		
		players2 = new ComboBox<String>();
		players2.setVisible(false);
		players2.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players2.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players2.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);
		
		players3 = new ComboBox<String>();
		players3.setVisible(false);
		players3.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players3.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		players3.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);
		

		inviteButton = new Button();
		inviteButton.setText("uitnodigen");
		inviteButton.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		inviteButton.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		inviteButton.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);


		vbox3 = new VBox(rb2, rb3, rb4);
		vbox3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		vbox3.setAlignment(Pos.CENTER);

		
		vbox = new VBox(titel1, vbox3, players1, players2, players3, inviteButton);
		vbox2 = new VBox();
		this.getChildren().addAll(vbox, vbox2);
	}

	public void showInvite() {
		hbox2 = new HBox();
		vbox4 = new VBox();
		labelNameBackground.setFitWidth(TEXTWHIDTH);
		labelNameBackground.setFitHeight(TEXTHEIGHT);

		titel2 = new Label("openstaande uitnodigingen", labelNameBackground);
		titel2.setFont(new Font("Arial", TEXT));
		titel2.setStyle("-fx-font-weight: bold");
		titel2.setContentDisplay(ContentDisplay.CENTER);


		invites = new ComboBox<>();
		invites.setMinSize(TEXTWHIDTH, COMBOBOXHEIGHT);
		invites.setPrefSize(TEXTWHIDTH, COMBOBOXHEIGHT);
		invites.setMaxSize(TEXTWHIDTH, COMBOBOXHEIGHT);
		invites.setPromptText("Welke uitnodiging zou je willen accepteren/wijgeren");
		
		

		accept = new Button();
		accept.setText("accepteer");
		accept.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		accept.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		accept.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);

		refuse = new Button();
		refuse.setText("afwijzen");
		refuse.setMinSize(BUTTONWHIDTH, BUTTONHEIGHT);
		refuse.setPrefSize(BUTTONWHIDTH, BUTTONHEIGHT);
		refuse.setMaxSize(BUTTONWHIDTH, BUTTONHEIGHT);

		hbox2.getChildren().addAll(accept, refuse);
		vbox4.getChildren().addAll(invites);
		vbox2.getChildren().addAll(titel2, vbox4, hbox2);
	}

//	public Button getSearch() {
//		return search;
//	}

	public ComboBox<String> getInvites() {
		return invites;
	}

	public Button getInviteButton() {
		return inviteButton;
	}

	public Button getAccept() {
		return accept;
	}

	public Button getRefuse() {
		return refuse;
	}

	public void invites(ComboBox<String> invites) {
		this.invites = invites;
	}

	public ToggleGroup getButtons() {
		return buttons;
	}

	public ComboBox<String> getPlayers1() {
		return players1;
	}

	public ComboBox<String> getPlayers2() {
		return players2;
	}

	public ComboBox<String> getPlayers3() {
		return players3;
	}

	public TextField getGameId() {
		return gameId;
	}
}
