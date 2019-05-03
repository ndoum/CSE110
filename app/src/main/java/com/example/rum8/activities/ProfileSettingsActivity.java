package com.example.rum8.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.rum8.listeners.RegistrationControllerListener;

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
        /*buttonUploadProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallary, result_load_image);
            }
        });
        imageUserProfile = (ImageView) findViewById(R.id.general_info_profile_image_view);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == result_load_image && resultCode == RESULT_OK && data != null){
            Uri selectImage = data.getData();
            imageUserProfile.setImageURI(selectImage);
        }
    }

    private void initController(){
        controller = new ProfileSettingsController(this);
    }

}
