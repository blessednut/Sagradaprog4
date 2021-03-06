package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameModel;

public class InGameDB {
	private Statement st;
	private PreparedStatement ps;
	
	public InGameDB (GameModel GM) {
		this.st = DBCon.getInstance().getSt();
	}
	
	public void updateSeqNR (int playerID, int seqnr) {
		try {
			String query = "update player set seqnr = " + seqnr + " WHERE idplayer = " + playerID + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeTurnPlayerID (int idgame, int playerID) {
		try {
			String query = "update game set turn_idplayer = " + playerID + " WHERE idgame = " + idgame + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSeqNR (int idgame, int playerID) {
		int seqNR = 0;
		try {
			String query = "SELECT seqnr FROM player WHERE idgame = " + idgame + " AND idplayer = " + playerID;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				seqNR = resultset.getInt("seqnr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqNR;
	}
	
	public int getTurnPlayerID (int idgame) {
		int playerID = 0;
		try {
			String query = "SELECT turn_idplayer FROM game WHERE idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				playerID = resultset.getInt("turn_idplayer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerID;
	}
	
	public int getRoundID (int idgame) {
		int roundID = 0;
		try {
			String query = "SELECT current_roundID FROM game WHERE idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				roundID = resultset.getInt("current_roundID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundID;
	}
	
	public boolean getClockwise (int idgame) {
		int clockwise = 0;
		try {
			String query = "SELECT clockwise FROM round WHERE roundID = " + getRoundID(idgame) + ";";
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				clockwise = resultset.getInt("clockwise");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (clockwise == 1) ? true : false;
	}
	
	public int getRoundNR (int idgame) {
		int roundNR = 0;
		try {
			String query = "SELECT roundnr FROM round WHERE roundID = " + getRoundID(idgame) + ";";
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				roundNR = resultset.getInt("roundnr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundNR;
	}
	
	public int getHighestSeqnr (int idgame) {
		int highestSeqnr = 0;
		try {
			String query = "SELECT MAX(seqnr) FROM player WHERE idgame = " + idgame + ";";
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				highestSeqnr = resultset.getInt("MAX(seqnr)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return highestSeqnr;
	}
	
	public void setRoundID (int idgame) {
		int newRoundID = getRoundID(idgame) + 1;
		try {
			String query = "update game set current_roundID = " + newRoundID + " WHERE idgame = " + idgame + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getPlayerID (int idgame, int seqnr) {
		int playerID = 0;
		try {
			String query = "SELECT idplayer FROM player WHERE idgame = " + idgame + " AND seqnr = " + seqnr + ";";
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				playerID = resultset.getInt("idplayer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerID;
	}

	public ArrayList<String> getOpponentNames(int idgame, int playerID) {
		ArrayList<String> names = new ArrayList<String>();
		try {
			String query = "SELECT * FROM player WHERE idplayer != " + playerID + " AND idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			while (resultset.next()) {
				names.add(resultset.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}

	public String getPlayerID(int playerID) {
		String name = null;
		try {
			String query = "SELECT username FROM player WHERE idplayer = " + playerID;
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				name = resultset.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public int getPlayerID(String username, int idgame) {
		int playerid = 0;
		try {
			String query = "SELECT * FROM player WHERE idgame = " + idgame + " AND username = '" + username + "';";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				playerid = resultset.getInt("idplayer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerid;
	}
	
	public void updateScore (int idplayer, int score) {
		try {
			String query = "UPDATE player SET score = " + score + " WHERE idplayer = " + idplayer;
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayStatusFinished (int idplayer) {
		try {
			String query = "UPDATE player SET playstatus = 'finished' WHERE idplayer = " + idplayer;
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getWinner (int idgame) {
		String winner = null;
		try {
			String query = "SELECT username, score FROM player WHERE idgame = " + idgame + " ORDER BY score DESC LIMIT 1;";
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				winner = resultset.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return winner;
	}
	
	public boolean gameEnded (int idgame) {
		boolean gameEnded = false;
		try {
			String query = "SELECT * FROM player WHERE playstatus = 'finished' AND idgame = " + idgame;
			ResultSet resultset = (st.executeQuery(query));
			if (resultset.next()) {
				gameEnded = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameEnded;
	}

	public boolean isPatternCardChosen(int idplayer) {
		boolean chosen = false;
		try {
			String query = "SELECT idpatterncard FROM player WHERE idplayer = " + idplayer;
			ResultSet rs = (st.executeQuery(query));
			if (rs.next()) {
				System.out.println("IDPATTERNCARD == " + rs.getInt("idpatterncard"));
				System.out.println("IDPATTERNCARD == " + rs.getInt("idpatterncard"));
				System.out.println("IDPATTERNCARD == " + rs.getInt("idpatterncard"));
				System.out.println("IDPATTERNCARD == " + rs.getInt("idpatterncard"));
				System.out.println("IDPATTERNCARD == " + rs.getInt("idpatterncard"));
				if (rs.getInt("idpatterncard") != 0) {
					chosen = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chosen;
	}


}
