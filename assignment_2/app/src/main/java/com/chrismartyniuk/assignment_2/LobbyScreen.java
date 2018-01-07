package com.chrismartyniuk.assignment_2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chrismartyniuk.assignment_2.R;

public class LobbyScreen extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private String username_Lobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_screen);

        Intent intent = getIntent();
        String username_Lobby = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if (shouldAskPermissions()) {
            askPermissions();
        }

    }

    public void playGame(View view) {

        Intent intent = getIntent();
        String username_Lobby = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Intent i = new Intent(this, Game.class);
        i.putExtra(EXTRA_MESSAGE, username_Lobby);

        startActivity(i);
    }

    public void checkStats(View view) {

        Intent intent = getIntent();
        String username_Lobby = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Intent i = new Intent(this, StatsScreen.class);
        i.putExtra(EXTRA_MESSAGE, username_Lobby);

        startActivity(i);
    }

    public void goToInputStreamActivity(View view) {
        Intent intent = getIntent();
        String username_Lobby = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Intent i = new Intent(this, InputStreamActivity.class);
        i.putExtra(EXTRA_MESSAGE, username_Lobby);

        startActivity(i);
    }

    public void updateAsynchText(View view) {
        new AsyncOperation().execute("");
    }

    private class AsyncOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView asynchLbl = (TextView) findViewById(R.id.asynchLbl);
            asynchLbl.setText("Executed asynchronously after 300 milliseconds");
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    //Open SMS with Implicit Intent
    public void contactBtnIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        startActivity(intent);
    }

    public void phoneBtnIntent(View view) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1231231234"));
        startActivity(intent);

    }


    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.CALL_PHONE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }
}

