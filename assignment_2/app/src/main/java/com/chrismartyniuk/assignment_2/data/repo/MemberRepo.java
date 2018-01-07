package com.chrismartyniuk.assignment_2.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chrismartyniuk.assignment_2.data.DatabaseManager;
import com.chrismartyniuk.assignment_2.data.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class MemberRepo {

    public static boolean checkUser;
    private final String TAG = MemberRepo.class.getSimpleName().toString();
    private Member member;

    public MemberRepo() {
        member = new Member();
    }

    public static String createTalbe(){
        return "CREATE TABLE " + Member.TABLE  + "("
                + Member.KEY_MemberId  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Member.KEY_MemberName + " TEXT, "
                + Member.KEY_MemberPassword + " TEXT)";
    }

    public void insert(Member member){
       // int memberId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
//        values.put(Member.KEY_MemberId, member.getMemberId());
        values.put(Member.KEY_MemberName, member.getMemberName());
        values.put(Member.KEY_MemberPassword, member.getMemberPassword());

        // Inserting Row
        db.insert(Member.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

       // return memberId;

    }

    public boolean checkUser(String memberName) {

        // array of columns to fetch
        String[] columns = {
                Member.KEY_MemberName
        };

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        // selection criteria
        String selection = Member.KEY_MemberName + " = ?";

        // selection argument
        String[] selectionArgs = {memberName};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(Member.TABLE , //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String memberName, String password) {

        // array of columns to fetch
        String[] columns = {
                Member.KEY_MemberName
        };
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // selection criteria
        String selection = Member.KEY_MemberName + " = ?" + " AND " + Member.KEY_MemberPassword + " = ?";

        // selection arguments
        String[] selectionArgs = {memberName, password};

        Cursor cursor = db.query(Member.TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Member.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public ArrayList<HashMap<String, String>>  getMemberList() {
        //Open connection to read only

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery =  "SELECT  " +
                Member.KEY_MemberId + "," +
                Member.KEY_MemberName + "," +
                Member.KEY_MemberPassword +
                " FROM " + Member.TABLE;

        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> member = new HashMap<String, String>();
                member.put("memberId", cursor.getString(cursor.getColumnIndex(Member.KEY_MemberId)));
                member.put("memberName", cursor.getString(cursor.getColumnIndex(Member.KEY_MemberName)));
                member.put("memberPassword", cursor.getString(cursor.getColumnIndex(Member.KEY_MemberPassword)));
                studentList.add(member);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        db.close();
        return studentList;

    }

}
