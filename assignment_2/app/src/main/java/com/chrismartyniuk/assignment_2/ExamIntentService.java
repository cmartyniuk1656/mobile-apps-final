package com.chrismartyniuk.assignment_2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by cmart on 2018-01-07.
 */

public class ExamIntentService extends IntentService {

    public ExamIntentService() {
        this(ExamIntentService.class.getName());
    }

    public ExamIntentService(String name) {
        super(name);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("Starting IntentService");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        showToast("Finishing IntentService");
    }

    private void showToast(final String msg) {
        //gets the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
