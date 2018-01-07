package com.chrismartyniuk.assignment_2.app;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

import android.app.Application;
import android.content.Context;

import com.chrismartyniuk.assignment_2.data.DBHelper;
import com.chrismartyniuk.assignment_2.data.DatabaseManager;

public class App extends Application{

    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }
}
