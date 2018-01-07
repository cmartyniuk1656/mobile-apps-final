package com.chrismartyniuk.assignment_2.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chrismartyniuk.assignment_2.data.DatabaseManager;
import com.chrismartyniuk.assignment_2.data.model.Member;
import com.chrismartyniuk.assignment_2.data.model.Money;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class MoneyRepo {

    private Money money;

    public MoneyRepo() {
        money = new Money();
    }

    public static String createTalbe(){
        return "CREATE TABLE " + Money.TABLE  + "("
                + Money.KEY_MemberName  + " TEXT PRIMARY KEY ,"
                + Money.KEY_MemberMoney + " INTEGER)";
    }

    public void insert(Money money){


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Money.KEY_MemberName, money.getMemberName());
        values.put(Money.KEY_MemberMoney, money.getMemberMoney());

        // Inserting Row
        db.insert(Money.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();



    }

    public void Update(Money money) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Money.KEY_MemberMoney, money.getMemberWinMoney());

        db.update(Money.TABLE, values, Money.KEY_MemberName + "= ?", new String[] { String.valueOf(money.getMemberName()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Money.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public Money getMoneyByName(String memberName){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  "SELECT  " +
                Money.KEY_MemberName  + "," +
                Money.KEY_MemberMoney +
                " FROM " + Money.TABLE
                + " WHERE " +
                Money.KEY_MemberName + "=?";

        int iCount =0;
        Money money = new Money();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(memberName) } );

        if (cursor.moveToFirst()) {
            do {

                money.setMemberName(cursor.getString(cursor.getColumnIndex(Money.KEY_MemberName)));
                money.setMemberMoney(cursor.getInt(cursor.getColumnIndex(Money.KEY_MemberMoney)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return money;
    }
}
