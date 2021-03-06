package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TokenDB {
	private Statement st;
	private PreparedStatement ps;

	public TokenDB() {
		this.st = DBCon.getInstance().getSt();
	}

	public boolean tokensAdded(int tokenAmount, int playerID) {
		boolean isadded = false;
		try {
			String query = "select count(idplayer) as added from gamefavortoken where idplayer = " + playerID
					+ " group by idgame;";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				if (resultset.getInt("added") > 0) {
					isadded = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			isadded = false;
		}
		return isadded;
	}

	 public void insertTokenInDB(int gameID, int playerID) {
	        try {
	            String query = "insert into gamefavortoken (idfavortoken, idgame, idplayer) values (" + getMaxTokenID() + " + 1,"
	                    + gameID + "," + playerID + ");";
	            ps = DBCon.getInstance().getCon().prepareStatement(query);
	            ps.execute();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public void updateToken(int toolCardID, int roundID, int gameID, int playerID, int cost) {
		try {
			String query = "update gamefavortoken set gametoolcard = " + toolCardID + ", roundID = " + roundID
					+ " where idgame = " + gameID + " and idplayer = " + playerID + " and gametoolcard is null limit "
					+ cost + ";";
			ps = DBCon.getInstance().getCon().prepareStatement(query);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getToolCardCost(int toolCardID, int gameID) {

		int cost = 0;
		try {
			String query = "select count(gametoolcard) as cost from gamefavortoken where gametoolcard = " + toolCardID
					+ " and idgame = " + gameID + ";";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				int numberOfTokensUsed = resultset.getInt("cost");
				if(numberOfTokensUsed > 1) {
					cost = 2;
				}
				else {
					cost = 1;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			cost = 1;

		}
		return cost;
	}

	public int getToolCardID(int gameID, String cardName) {
		int toolCardID = 0;
		try {
			String query = "SELECT t.idtoolcard as id FROM gametoolcard AS gt JOIN toolcard AS t ON gt.idtoolcard = t.idtoolcard WHERE idgame = "
					+ gameID + " AND name = '" + cardName + "';";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {
				toolCardID = resultset.getInt("id");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return toolCardID;
	}

	public int getTokenAmount(int playerID) {
		int tokens = 0;
		try {
			String query = "select count(idplayer) as tokens from gamefavortoken where idplayer = " + playerID
					+ " and gametoolcard is null group by idgame;";
			ResultSet resultset = st.executeQuery(query);
			if (resultset.next()) {

				tokens = resultset.getInt("tokens");

			}

		} catch (Exception e) {
			e.printStackTrace();
			tokens = 0;
		}
		return tokens;
	}
	
	public int getUsedPerCard(int toolCardID, int gameID) {
		int usedPerCard = 0;
		try {
			String query = "select count(gametoolcard) as numberUsed from gamefavortoken where gametoolcard = "+toolCardID+" and idgame = "+gameID+";";
			ResultSet resultset = st.executeQuery(query);
			if(resultset.next()) {
				usedPerCard = resultset.getInt("numberUsed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return usedPerCard;
	}
	
	public ArrayList<Integer> getPlayerIDPerUsedToken(int toolCardID, int gameID){
		ArrayList<Integer> PlayerIDPerUsedToken = new ArrayList<Integer>();
		try {
			String query = "select idplayer from gamefavortoken where idgame = "+gameID+" and gametoolcard = "+toolCardID+" and gametoolcard is not null group by idfavortoken;";
			ResultSet resultset = st.executeQuery(query);
			while(resultset.next()) {
				int playerid = resultset.getInt("idplayer");
				PlayerIDPerUsedToken.add(playerid);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return PlayerIDPerUsedToken;
	}

	public int getMaxTokenID() {
        int maxID = 0;
        try {
            String query = "select max(t.idfavortoken) as maxID from gamefavortoken t;";
            ResultSet resultset = st.executeQuery(query);
            if (resultset.next()) {
                if (resultset.getInt("maxID") > 0) {
                    maxID = resultset.getInt("maxID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxID;
    }
}
