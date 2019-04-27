package com.example.rum8.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rum8.R;
import com.example.rum8.controllers.MainController;
import com.example.rum8.listeners.MainControllerListener;

public class MainActivity extends AppCompatActivity
                          implements MainControllerListener {

    private MainController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initController();
    }

    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(MainActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        final Button button_goToProfileSettings = findViewById(R.id.button_go_to_profile_settings);
        button_goToProfileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                controller.onGoToProfileSettingsButtonClicked();
            }
        });
    }

    private void initController() {
        controller = new MainController(this);
    }

}
