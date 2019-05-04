package com.example.rum8.activities;

import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;


import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ProfileSettingsViewPagerAdapter;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

public class ProfileSettingsActivity extends AppCompatActivity
        implements ProfileSettingsControllerListener {

    private ProfileSettingsController controller;
    private ViewPager viewPager;

    private Button buttonUploadProfileImage;
    private ImageView imageUserProfile;
    private static int result_load_image = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        initViews();
        initController();

    }

    private void initViews() {
        viewPager = findViewById(R.id.profile_settings_view_pager);
        viewPager.setAdapter(new ProfileSettingsViewPagerAdapter(getSupportFragmentManager()));

        buttonUploadProfileImage = (Button) findViewById(R.id.general_info_profile_image_upload_button);
        //TODO set on click listener and open android photo gallary
    }


    private void initController() {
        controller = new ProfileSettingsController(this);
    }

}
