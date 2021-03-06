package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class OpenGamesPane extends HBox {
	
	private static final int SAGRADAWIDTH = 1280;
	private static final int SAGRADAHEIGHT = 689;
	private static final int COMBOBOXWITDH = 500;
	private static final int COMBOBOXHEIGHT = 50;
	private static final int OPENGAMEBUTTONWITDH = 150;
	private static final int OPENGAMEBUTTONHEIGHT= 30;
	private static final Insets PADDING = new Insets(100, 0, 0, 0);
	private Button openGame;
	private ComboBox<String> oldGames;
	
	private ComboBox<String> allGames;
	private static final int AREAWIDTH = 500;
	private static final int AREAHEIGHT = 50;
	
	public OpenGamesPane() {
		this.setMinSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setMaxSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPrefSize((SAGRADAWIDTH / 8 * 7), SAGRADAHEIGHT);
		this.setPadding(PADDING);
		showGames();
	}
	
	public void showGames() {
		VBox layout = new VBox();
		
		oldGames = new ComboBox<>();
		oldGames.setMinSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setPrefSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setMaxSize(COMBOBOXWITDH, COMBOBOXHEIGHT);
		oldGames.setPromptText("Welke oude spel zou je willen openen?");
		
		allGames = new ComboBox<>();
		allGames.setMinSize(AREAWIDTH, AREAHEIGHT);
		allGames.setPrefSize(AREAWIDTH, AREAHEIGHT);
		allGames.setMaxSize(AREAWIDTH, AREAHEIGHT);
		allGames.setPromptText("Alle games die aangemaakt zijn en de datum van creatie");
		
		openGame = new Button();
		openGame.setText("open spel");
		openGame.setMinSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setPrefSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setMaxSize(OPENGAMEBUTTONWITDH, OPENGAMEBUTTONHEIGHT);
		openGame.setVisible(true);
		
		layout.getChildren().addAll(oldGames, allGames);
		this.getChildren().addAll(layout, openGame );
	}
	
	public ComboBox<String> getOldGamesBox(){
		return oldGames;
	}
	
	public Button getOpenGame() {
		return openGame;
	}

	public ComboBox<String> getAllGames() {
		return allGames;
	}
	
	

	
	
}
