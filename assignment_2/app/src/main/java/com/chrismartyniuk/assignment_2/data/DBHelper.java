package com.chrismartyniuk.assignment_2.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chrismartyniuk.assignment_2.app.App;
import com.chrismartyniuk.assignment_2.data.model.Member;
import com.chrismartyniuk.assignment_2.data.model.Money;
import com.chrismartyniuk.assignment_2.data.model.Score;
import com.chrismartyniuk.assignment_2.data.repo.MemberRepo;
import com.chrismartyniuk.assignment_2.data.repo.MoneyRepo;
import com.chrismartyniuk.assignment_2.data.repo.ScoreRepo;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "blackJack.db";
    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MemberRepo.createTalbe());
        db.execSQL(MoneyRepo.createTalbe());
        db.execSQL(ScoreRepo.createTalbe());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("DROP TABLE IF EXISTS " + Member.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Score.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Money.TABLE);

        onCreate(db);

    }
}
