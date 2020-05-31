package Controller;

import View.OpenGamesPane;
import model.OpenGamesModel;

public class OpenGamesController {
	private OpenGamesPane OGP;
	private OpenGamesModel OGM;

	public OpenGamesController(LogInController logInController) {
		OGM = new OpenGamesModel();
		OGP = new OpenGamesPane();
		OGP.getOpenGame().setOnAction(e -> openGame());

	}

	public OpenGamesPane getOGP() {
		return OGP;
	}

	public OpenGamesModel getOGM() {
		return OGM;
	}

	public void fillGames() {
		OGP.getOldGamesBox().getItems().clear();
		for (int i = 0; i < OGM.getOldGamesArray().size(); i++) {
			if (!OGP.getOldGamesBox().getItems().contains(OGM.getOldGamesArray().get(i))) {
				OGP.getOldGamesBox().getItems().add("Spel ID: " + OGM.getOldGamesArray().get(i)
						+ ",		Datum van spelcreatie: " + OGM.getOldGamesDateArray().get(i));
			}
		}

	}
	public int getSelectedGameID() {
		int gameId = 0;
		String[] parts = OGP.getOldGamesBox().getValue().split(",");
		String[] idParts = parts[0].split(": ");
		String StringGameID = idParts[1];
		gameId = Integer.parseInt(StringGameID);
		return gameId;
	}

	public void openGame() {
		if(getSelectedGameID() != 0) {
//			TODO: roept methode aan die alle zooi van t spel opent
//			geef getSelectedGameID(); mee aan said methode
		}

			
	}

}
