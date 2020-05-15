package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.GameModel;

public class GameDB {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	private GameModel GM;
	
	public GameDB (GameModel GM) {
		this.st = DBCon.getInstance().getSt();
		this.GM = GM;
	}
	
	public int createGameRoom() {
		int GameId = 0;
		try {
			String query = "select MAX(idgame) as idgame from game;";
			rs = st.executeQuery(query);
			while (rs.next()) {
				GameId = rs.getInt("idgame");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		GameId++;
		GM.setGameId(GameId);
		
		try {
			String query = "insert into game(idgame,creationdate) values(?,now());";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.setInt(1, GameId);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return GameId;
	}
}