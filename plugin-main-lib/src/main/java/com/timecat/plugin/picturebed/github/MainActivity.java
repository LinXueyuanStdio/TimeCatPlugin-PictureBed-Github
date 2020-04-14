package com.timecat.plugin.picturebed.github;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.timecat.plugin.window.StandOutWindow;

import java.util.Calendar;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MainActivity", "begin");
        int a = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int b = Calendar.getInstance().get(Calendar.SECOND);
        StandOutWindow.show(this, GithubApp.class, a * 1000 + b);
        Log.e("MainActivity", "end");
        finish();
    }
}