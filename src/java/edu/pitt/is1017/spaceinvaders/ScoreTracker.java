package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JOptionPane;

public class ScoreTracker {

    public String winMessage = null;
    private User user;
    private int currentScore, highestScore, highestScoreOther;
    private String gameID, firstNameOther, lastNameOther;

    ;

	public ScoreTracker(User user, String gameID) {
        this.user = user;
        this.currentScore = 0;
        this.gameID = gameID;

        DbUtilities db = new DbUtilities();

        //Initialize row in db for current game
        String initScore = "INSERT INTO alieninvasion.runningscores(`gameID`,`scoreType`,`scoreValue`,`fk_userID`,`dateTimeEntered`) ";
        initScore += "VALUES (\"" + this.gameID + "\"," + 1 + ",\"" + this.currentScore + "\",\"" + this.user.getUserID() + "\",NOW());";

        db.executeQuery(initScore);

        //Select highest score for user
        String highScore = "SELECT MAX(scoreValue) FROM alieninvasion.finalscores ";
        highScore += "WHERE `fk_userID` = \"" + user.getUserID() + "\"";

        ResultSet rs = db.getResultSet(highScore);

        try {
            while (rs.next()) {
                highestScore = rs.getInt("MAX(scoreValue)");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        db.closeConnection();
    }

    public void recordScore(int points) {
        DbUtilities db = new DbUtilities();

        currentScore = points;

        //updates the score for the current game 
        String updateScore = "UPDATE alieninvasion.runningscores SET `scoreValue`=" + getCurrentScore() + " ";
        updateScore += "WHERE `gameID`=\"" + this.gameID + "\" AND `fk_userID`=" + this.user.getUserID() + ";";

        db.executeQuery(updateScore);
        db.closeConnection();
    }

    public void recordFinalScore() {
        DbUtilities db = new DbUtilities();

        //Insert final score of current game
        String sql = "INSERT INTO alieninvasion.finalscores(`gameID`,`scoreValue`,`fk_userID`,`dateTimeEntered`) ";
        sql += "VALUES (\"" + this.gameID + "\",\"" + this.currentScore + "\",\"" + this.user.getUserID() + "\",NOW());";

        db.executeQuery(sql);
        
    }

    public void getGlobalHighScore() {
        DbUtilities db = new DbUtilities();
        //Retrieve highest score of user
        String highScore = "SELECT MAX(scoreValue) FROM alieninvasion.finalscores ";
        highScore += "WHERE `fk_userID` = \"" + user.getUserID() + "\"";

        ResultSet rs = db.getResultSet(highScore);

        try {
            while (rs.next()) {
                highestScore = rs.getInt("MAX(scoreValue)");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //Retrieve highest score on table, and display it
        String highScoreOther = "SELECT lastName, firstName, MAX(scoreValue) ";
        highScoreOther += "FROM finalscores JOIN users ON fk_userID = userID GROUP BY lastName, firstName ORDER BY `MAX(scoreValue)` DESC";

        ResultSet rs2 = db.getResultSet(highScoreOther);

        try {
            if (rs2.next()) {
                highestScoreOther = rs2.getInt("MAX(scoreValue)");
                firstNameOther = rs2.getString("firstName");
                lastNameOther = rs2.getString("lastName");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.closeConnection();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public int getHighestScoreOther() {
        return highestScoreOther;
    }

    public String getFirstNameOther() {
        return firstNameOther;
    }

    public String getLastNameOther() {
        return lastNameOther;
    }

}
