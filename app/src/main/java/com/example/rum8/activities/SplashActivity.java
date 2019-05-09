package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.SplashController;
import com.example.rum8.listeners.SplashControllerListener;

public class SplashActivity extends AppCompatActivity implements SplashControllerListener {

    private SplashController controller;

    private final int SPLASH_DISPLAY_TIME_MS = 2000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initController();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                controller.goToNextPage();
            }
        }, SPLASH_DISPLAY_TIME_MS);
    }

    @Override
    public void goToMainPage() {
        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

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
