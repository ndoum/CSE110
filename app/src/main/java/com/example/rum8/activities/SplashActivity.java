package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.listeners.SplashControllerListener;

public class SplashActivity extends AppCompatActivity implements SplashControllerListener {

    private final int SPLASH_DISPLAY_TIME_MS = 2000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity( new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, msDelay);
    }

}
