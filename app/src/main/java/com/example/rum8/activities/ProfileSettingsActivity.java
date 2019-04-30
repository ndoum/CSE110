package com.example.rum8.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

public class ProfileSettingsActivity extends AppCompatActivity
                                     implements ProfileSettingsControllerListener {

    private ProfileSettingsController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        initViews();
        initController();
    }

    private void initViews() {
    }

    private void initController() {
    }

}
