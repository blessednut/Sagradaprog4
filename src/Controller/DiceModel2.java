package Controller;

import java.util.ArrayList;

import DataBase.DiceDB;
import model.GameDiceModel;

public class DiceModel2 {
	private ArrayList<GameDiceModel> dice;
	private GameController controller;
	private DiceDB con;
	
	public DiceModel2 (GameController controller) {
		this.controller = controller;
		this.dice = new ArrayList<GameDiceModel>();
		this.con = new DiceDB();
		
		this.importGameDice(controller.getM_game().getGameId());
	}
	
	public void addGameDice (GameDiceModel gameDice) {
		this.dice.add(gameDice);
	}
	
	public ArrayList<GameDiceModel> getBag () {
		return this.con.getBag(controller.getM_game().getGameId());
	}
	
	public void addDice (GameDiceModel gameDice) {
		this.dice.add(gameDice);
		
		//insert Query
		this.con.insertGameDice(gameDice);
	}
	
	private void importGameDice (int gameID) {
		this.dice = con.importGameDice(gameID);
	}
	
	//Maken update Query
}