package Controller;

import java.util.ArrayList;
import java.util.Random;

import View.InvitePane;
import View.InviteStart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.InviteModel;

public class InviteController {

	private InvitePane invitePane;
	private InviteModel inviteModel;
	private GameController gameController;
	private HomeController home;
	private GameAcceptionThreadController gameAcceptionThread;
	private InviteStart inviteStart;
	private String StringGameID;

	private static final String ACCEPTED = "accepted";
	private static final String REFUSED = "refused";

	private ArrayList<String> ColorArray = new ArrayList<>();
	private int randomColorInt;
	private boolean added = true;

//	private boolean userFound = false;

	public InviteController(GameController gameController, HomeController home) {
		this.gameController = gameController;
		this.home = home;
		inviteModel = new InviteModel();
		invitePane = new InvitePane(this);
		fillPlayerList();

		// de switchcase bepaalt aan de hand van de selected RadioButton welke methode
		// er gekozen moet worden.
//		invitePane.getSearch().setOnMouseClicked(e -> {
//			switch (invitePane.getButtons().getSelectedToggle().getUserData().toString()) {
//			case "twee":
//				searchForUsername(invitePane.getName1().getText());
//				break;
//			case "drie":
//				searchForTwoUsernames(invitePane.getName1().getText(), invitePane.getName2().getText());
//				break;
//			case "vier":
//				searchForThreeUsernames(invitePane.getName1().getText(), invitePane.getName2().getText(),
//						invitePane.getName3().getText());
//				break;
//			}
//			if(!userFound) {
//				Alert alert = new Alert(AlertType.INFORMATION, "1 of meerdere gebruikersnamen bestaan niet.", ButtonType.OK);
//				alert.showAndWait();
//				if (alert.getResult() == ButtonType.OK) {
//					alert.close();
//				}
//				userFound = false;
//			}
//		});
//		invitePane.getName1().setOnMouseClicked(e -> setSearchButton());

//		invitePane.getInviteButton().setOnMouseClicked(e -> {
//			if(inviteModel.checkInvitation(gameController.getC_login().getUsername(), invitePane.getName1().getText()) <= 0) {
//				if(inviteModel.checkInvitation(gameController.getC_login().getUsername(), invitePane.getName2().getText()) <=0) {
//					if(inviteModel.checkInvitation(gameController.getC_login().getUsername(), invitePane.getName3().getText()) <= 0) {
//						inVitePlayer();
//						acceptInvitation(home.getC_login().getUsername(), gameController.getM_game().getGameId());
//						createInviteStartPane(Integer.toString(gameController.getM_game().getGameId()));
//						this.invitePane.getInviteButton().setVisible(false);
//					}
//				}
//			} else {
//				Alert error = new Alert(AlertType.ERROR, "je hebt deze persoon al een keer uitgenodigd", ButtonType.OK);
//				error.showAndWait();
//				if(error.getResult() == ButtonType.OK) {
//					error.close();
//				}
//			}
//			this.invitePane.getInviteButton().setVisible(false);
//			this.invitePane.getSearch().setVisible(true);
//		});

		invitePane.getInviteButton().setOnMouseClicked(e -> {
			boolean firstInvite = false;
			switch (invitePane.getButtons().getSelectedToggle().getUserData().toString()) {
			case "twee":
				if (this.invitePane.getPlayers1().getValue() != null
						&& !this.inviteModel.checkForDoubleInvite(this.gameController.getC_login().getUsername(),
								this.invitePane.getPlayers1().getValue())) {
					firstInvite = true;
				}
				break;
			case "drie":
				System.out.println("drie");
				if (this.invitePane.getPlayers1().getValue() != null
						&& !this.inviteModel.checkForDoubleInvite(this.gameController.getC_login().getUsername(),
								this.invitePane.getPlayers1().getValue())) {
					if (this.invitePane.getPlayers2().getValue() != null
							&& !this.inviteModel.checkForDoubleInvite(this.gameController.getC_login().getUsername(),
									this.invitePane.getPlayers2().getValue())) {
						firstInvite = true;
					}
				}
				break;
			case "vier":
				System.out.println("vier");
				if (this.invitePane.getPlayers1().getValue() != null
						&& !this.inviteModel.checkForDoubleInvite(this.gameController.getC_login().getUsername(),
								this.invitePane.getPlayers1().getValue())) {
					if (this.invitePane.getPlayers2().getValue() != null
							&& !this.inviteModel.checkForDoubleInvite(this.gameController.getC_login().getUsername(),
									this.invitePane.getPlayers2().getValue())) {
						if (this.invitePane.getPlayers3().getValue() != null && !this.inviteModel.checkForDoubleInvite(
								this.gameController.getC_login().getUsername(),
								this.invitePane.getPlayers3().getValue())) {
							firstInvite = true;
						}
					}
				}
				break;
			}
			if (firstInvite) {
				boolean playersInvited = inVitePlayer();
				
				if (playersInvited) {
					acceptInvitation(home.getC_login().getUsername(), gameController.getM_game().getGameId());
					createInviteStartPane(Integer.toString(gameController.getM_game().getGameId()));
					firstInvite = false;
				} else {
					Alert alert = new Alert(AlertType.INFORMATION,
							"Je probeert een speler meerdere keren uit te nodigen!",
							ButtonType.OK);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.OK) {
						alert.close();
					}
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION,
						"niet alle tegenstanders zijn geslelecteerd of een/meerdere tegenstander(s) hebben al een uitnodiging van u openstaan.",
						ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
					alert.close();
				}
			}
		});

		invitePane.getAccept().setOnMouseClicked(e -> {
			if (invitePane.getInvites().getValue() != null) {
				acceptInvitation(home.getC_login().getUsername());
				createInviteStartPane(StringGameID);
			}

		});
		invitePane.getRefuse().setOnMouseClicked(e -> {
			if (invitePane.getInvites().getValue() != null) {
				refuseInvitation(home.getC_login().getUsername());
			}
		});

	}

	public void fillPlayerList() {
		String currentPlayer = this.home.getC_login().getUsername();
		System.out.println("CurrentPlayer = " + currentPlayer);
		this.invitePane.getPlayers1().getItems().clear();
		this.invitePane.getPlayers1().getItems().addAll(this.inviteModel.getUsernames(currentPlayer));
		this.invitePane.getPlayers2().getItems().clear();
		this.invitePane.getPlayers2().getItems().addAll(this.inviteModel.getUsernames(currentPlayer));
		this.invitePane.getPlayers3().getItems().clear();
		this.invitePane.getPlayers3().getItems().addAll(this.inviteModel.getUsernames(currentPlayer));
	}

	public void acceptInvitation(String username) {
		String[] parts = invitePane.getInvites().getValue().split("\\+");
		StringGameID = parts[1];

		int Gameid = Integer.parseInt(StringGameID);
		gameAcceptionThread = new GameAcceptionThreadController(this, Gameid);
		gameAcceptionThread.setDaemon(true);
		gameAcceptionThread.start();

		gameController.getM_game().setGameId(Gameid);

		inviteModel.updatePlayerStatusChallengee(username, Gameid, ACCEPTED);
		invitePane.getInvites().getItems().remove(invitePane.getInvites().getValue());

	}

	public void acceptInvitation(String username, int gameID) {
		gameAcceptionThread = new GameAcceptionThreadController(this, gameID);
		gameAcceptionThread.setDaemon(true);
		gameAcceptionThread.start();

		gameController.getM_game().setGameId(gameID);

	}

	public void refuseInvitation(String username) {
		String[] parts = invitePane.getInvites().getValue().split("\\+");
		String StringGameID = parts[1];

		int Gameid = Integer.parseInt(StringGameID);
		gameController.getM_game().setGameId(Gameid);

		inviteModel.updatePlayerStatusChallengee(username, Gameid, REFUSED);
		invitePane.getInvites().getItems().remove(invitePane.getInvites().getValue());

		Alert exception = new Alert(AlertType.ERROR, "Je hebt de uitnodiging nu geweigerd", ButtonType.OK);
		exception.showAndWait();
	}

	public Boolean inVitePlayer() {
		boolean playersInvited = false;
		switch (invitePane.getButtons().getSelectedToggle().getUserData().toString()) {
		case "twee":
			gameController.getM_game().createGameRoom();
			inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
					inviteModel.getColor(createRandomPrivatObjColor()));
			// uitgedaagde
			inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers1().getValue(),
					gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
					inviteModel.getColor(createRandomPrivatObjColor()));
			gameController.getM_game().updatePlayerTurn(1);
			playersInvited = true;
			break;
		case "drie":
			if (this.invitePane.getPlayers1().getValue().equals(this.invitePane.getPlayers2().getValue()) == false) {
				gameController.getM_game().createGameRoom();
				inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				// eerste uitgedaagde
				inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers1().getValue(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				// tweede uigedaagde
				inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers2().getValue(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				gameController.getM_game().updatePlayerTurn(1);
				playersInvited = true;
			}
			break;
		case "vier":
			String player1 = this.invitePane.getPlayers1().getValue();
			System.out.println("Player 1 " + player1);
			String player2 = this.invitePane.getPlayers2().getValue();
			System.out.println("Player 2 " + player2);
			String player3 = this.invitePane.getPlayers3().getValue();
			System.out.println("Player 3 " + player3);
			if (player1.equals(player2) == false && player2.equals(player3) == false
					&& player3.equals(player1) == false) {
				gameController.getM_game().createGameRoom();
				inviteModel.challengeeSelf(inviteModel.maxPlayerId(), home.getC_login().getUsername(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challenger"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				// eerste uitgedaagde
				inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers1().getValue(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				// tweede uitgedaagde
				inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers2().getValue(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				// derde uitgedaagde.
				inviteModel.challengeeOther(inviteModel.maxPlayerId(), invitePane.getPlayers3().getValue(),
						gameController.getM_game().getGameId(), inviteModel.getPlayerStatus("challengee"),
						inviteModel.getColor(createRandomPrivatObjColor()));
				gameController.getM_game().updatePlayerTurn(1);
				playersInvited = true;
			}
			break;
		}
		return playersInvited;
	}

//	public void searchForUsername(String username) {
//		try {
//			if (!username.equals(gameController.getC_login().getUsername())) {
//				if (inviteModel.checkInDatabase(username).equals(username)) {
//					//invitePane.getInviteButton().setVisible(true);
//					//invitePane.getSearch().setVisible(false);
//					userFound = true;
//				}
//			}
//		} catch (
//
//		Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void searchForTwoUsernames(String username1, String username2) {
//		try {
//			if (!username1.equals(username2)) {
//				if (!username1.equals(gameController.getC_login().getUsername())
//						&& !username2.equals(gameController.getC_login().getUsername())) {
//					if (inviteModel.checkInDatabase(username1).equals(username1)
//							&& inviteModel.checkInDatabase(username2).equals(username2)) {
//						invitePane.getInviteButton().setVisible(true);
//						invitePane.getSearch().setVisible(false);
//						userFound = true;
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void searchForThreeUsernames(String username1, String username2, String username3) {
//		try {
//			if (!username1.equals(username2) && !username1.equals(username3) && !username2.equals(username3)) {
//				if (!username1.equals(gameController.getC_login().getUsername())
//						&& !username2.equals(gameController.getC_login().getUsername())
//						&& !username3.equals(gameController.getC_login().getUsername())) {
//					if (inviteModel.checkInDatabase(username1).equals(username1)
//							&& inviteModel.checkInDatabase(username2).equals(username2)
//							&& inviteModel.checkInDatabase(username3).equals(username3)) {
//						invitePane.getInviteButton().setVisible(true);
//						invitePane.getSearch().setVisible(false);
//						userFound = true;
//					}
//				}
//			}
//		} catch (
//
//		Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void createInviteStartPane(String gameID) {
		inviteStart = new InviteStart(gameID);
		home.addInviteStartPane(inviteStart);
		inviteStart.getStartGame().setOnMouseClicked(e -> {
			home.removeInviteStartPane(inviteStart);
			gameController.createGamePane();
			closeGameAcceptionThreadCon();
			closeHomeThreadCon();

		});

	}

	public String createRandomPrivatObjColor() {
		Random rand = new Random();
		int max = 5;
		int min = 1;
		added = false;

		String result = "";

		while (!added) {
			randomColorInt = rand.nextInt((max - min) + 1) + min;
			switch (randomColorInt) {
			case 1:
				result = "blue";
				break;
			case 2:
				result = "green";
				break;
			case 3:
				result = "purple";
				break;
			case 4:
				result = "red";
				break;
			case 5:
				result = "yellow";
				break;
			}
			if (!ColorArray.contains(result)) {
				ColorArray.add(result);
				added = true;
			}

		}
		// System.out.println(result);
		return result;
	}

	public void closeHomeThreadCon() {
		home.getC_hometc().terminate();
		home.getC_hometc().getM_home().getHomeThreadDB().getDbCon().closeConnection();

	}

	public void closeGameAcceptionThreadCon() {
		gameAcceptionThread.terminate();
		gameAcceptionThread.getGameThreadModel().getGameAcceptionThreadDB().getConNection().closeConnection();
	}

	public void setStartButtonVisable() {
		inviteStart.getStartGame().setVisible(true);
	}

//	public void setSearchButton() {
//		invitePane.getName1().clear();
//		invitePane.getInviteButton().setVisible(false);
//		invitePane.getSearch().setVisible(true);
//	}

	public InvitePane getV_InvitePane() {
		return invitePane;
	}

	public GameController getC_game() {
		return gameController;
	}

	public HomeController getHome() {
		return home;
	}

	public InviteStart getInviteStart() {
		return inviteStart;
	}

}
