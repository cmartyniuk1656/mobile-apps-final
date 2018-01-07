package com.chrismartyniuk.assignment_2.data.model;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class Score {
    public static final String TAG = Score.class.getSimpleName();
    //Table Name
    public static final String TABLE = "Score";

    //Colums names
    public static final String KEY_GameId = "GameId";
    public static final String KEY_MemberName = "MemberName";
    public static final String KEY_GameWin = "GameWin";
    public static final String KEY_GameLose = "GameLose";

    private String memberName;
    public int GameWin;
    public int GameLose;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }


    public int getGameWin() {
        return GameWin;
    }

    public int getGameLose() {
        return GameLose;
    }

    public int getUpdateGameWin() {

        GameWin++;
        return GameWin;
    }

    public void setGameWin(int gameWin) {
        GameWin = gameWin;
    }




    public int getUpdateGameLose() {
        GameLose++;
        return GameLose;
    }

    public void setGameLose(int gameLose) {
        GameLose = gameLose;
    }

    public void incrementWins() {
        GameWin++;
    }

    public void incrementLosses() {
        GameLose++;
    }



}
