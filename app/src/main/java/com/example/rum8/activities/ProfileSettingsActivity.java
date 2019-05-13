package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ProfileSettingsViewPagerAdapter;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileSettingsActivity extends AppCompatActivity
        implements ProfileSettingsControllerListener {

    private ProfileSettingsController controller;
    private ViewPager viewPager;

    private TextInputEditText firstName;
    private TextInputEditText lastName;

    private Button buttonGeneralInfoNext;
    private Button buttonUploadProfileImage;
    private ImageView imageUserProfile;
    private static int result_load_image = 1;
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        initViews();
        initController();

    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(ProfileSettingsActivity.this, message, toastLength).show();
    }

    private void initViews() {
        viewPager = findViewById(R.id.profile_settings_view_pager);
        viewPager.setAdapter(new ProfileSettingsViewPagerAdapter(getSupportFragmentManager()));

        firstName = (TextInputEditText) findViewById(R.id.general_info_first_name_field);
        lastName = (TextInputEditText) findViewById(R.id.general_info_last_name_field);

        buttonUploadProfileImage = (Button) findViewById(R.id.general_info_profile_image_upload_button);

    }

    private void initController() {
        controller = new ProfileSettingsController(this);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);

    }

}
