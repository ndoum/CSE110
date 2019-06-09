package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.SplashController;
import com.example.rum8.listeners.SplashControllerListener;

/**
 * Class that display render screen before the app start.
 */
public class SplashActivity extends AppCompatActivity implements SplashControllerListener {

    // Initialize class variable
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

    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(SplashActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    private void initController() {
        controller = new SplashController(this);
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(SplashActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
