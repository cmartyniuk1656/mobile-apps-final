package com.chrismartyniuk.assignment_2.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chrismartyniuk.assignment_2.data.DatabaseManager;
import com.chrismartyniuk.assignment_2.data.model.Member;
import com.chrismartyniuk.assignment_2.data.model.Money;
import com.chrismartyniuk.assignment_2.data.model.Score;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class ScoreRepo {

    private final String TAG = ScoreRepo.class.getSimpleName().toString();

    private Score score;

    public ScoreRepo(){
        score = new Score();
    }

    public static String createTalbe(){
        return "CREATE TABLE " + Score.TABLE  + "("
                + Score.KEY_GameId  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Score.KEY_MemberName + " TEXT, "
                + Score.KEY_GameWin + " INTEGER, "
                + Score.KEY_GameLose  + " INTEGER )";
    }

    public void insert(Score score){

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Score.KEY_MemberName, score.getMemberName());
        values.put(Score.KEY_GameWin, score.getGameWin());
        values.put(Score.KEY_GameLose, score.getGameLose());

        // Inserting Row
        db.insert(Score.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();


    }

    public void winUpdate(Score score) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Score.KEY_GameWin, score.getGameWin());

        db.update(Score.TABLE, values, Score.KEY_MemberName + "= ?", new String[] { String.valueOf(score.getMemberName()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    public void loseUpdate(Score score) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Score.KEY_GameLose, score.getGameLose());

        db.update(Score.TABLE, values, Score.KEY_MemberName + "= ?", new String[] { String.valueOf(score.getMemberName()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Score.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public Score getScoreByName(String memberName){

        //Open the database
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        //Create the Query
        String selectQuery =  "SELECT  " +
                Score.KEY_MemberName  + "," +
                Score.KEY_GameWin  + "," +
                Score.KEY_GameLose +
                " FROM " + Score.TABLE
                + " WHERE " +
                Score.KEY_MemberName + "=?";

        int iCount =0;
        Score score = new Score();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(memberName) } );

        if (cursor.moveToFirst()) {
            do {

                score.setMemberName(cursor.getString(cursor.getColumnIndex(Score.KEY_MemberName)));
                score.setGameWin(cursor.getInt(cursor.getColumnIndex(Score.KEY_GameWin)));
                score.setGameLose(cursor.getInt(cursor.getColumnIndex(Score.KEY_GameLose)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return score;
    }

}
