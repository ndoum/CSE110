package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.SplashController;
import com.example.rum8.listeners.SplashControllerListener;

public class SplashActivity extends AppCompatActivity implements SplashControllerListener {

    private static final int SPLASH_DISPLAY_TIME_MS = 2000;
    private SplashController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initController();

        new Handler().postDelayed(() -> controller.goToNextActivity(), SPLASH_DISPLAY_TIME_MS);
    }

    /**
     * Finish this activity because it should not be the parent activity of any activity.
     */
    @Override
    public void goToMain() {
        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Finish this activity because it should not be the parent activity of any activity.
     */
    @Override
    public void goToLogin() {
        final Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initController() {
        controller = new SplashController(this);
    }
}
