package com.example.rum8.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ProfileSettingsViewPagerAdapter;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;

public class ProfileSettingsActivity extends FragmentActivity
    implements ProfileSettingsControllerListener {

    private static final int PICK_IMAGE_REQUEST = 65537;
    private static int result_load_image = 1;
    private FragmentPagerAdapter adapterViewPager;
    private ProfileSettingsController controller;
    private ViewPager viewPager;
    private Uri filePath;
    private Bitmap bitmap;
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private Button buttonGeneralInfoNext;
    private Button buttonUploadProfileImage;
    private ImageView imageUserProfile;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        initViews();
        initController();
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(ProfileSettingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (isResultValid(resultCode, requestCode) && isDataValid(data)) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (final Exception e) {
                e.printStackTrace();
                showToast("Network Error");
            }
        }
    }

    private boolean isResultValid(final int resultCode, final int requestCode) {
        return (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST);
    }

    private boolean isDataValid(final Intent data) {
        return (data != null && data.getData() != null);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        viewPager = findViewById(R.id.profile_settings_view_pager);
        viewPager.setAdapter(new ProfileSettingsViewPagerAdapter(getSupportFragmentManager()));


        firstName = findViewById(R.id.general_info_first_name_field);
        lastName = findViewById(R.id.general_info_last_name_field);

        buttonUploadProfileImage = findViewById(R.id.general_info_profile_image_upload_button);

        tabLayout = findViewById(R.id.activity_profile_settings_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initController() {
        controller = new ProfileSettingsController(this);
    }

    public Uri getFilePath() {
        return filePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    @Override
    public void showUploadImageProgress() {
    }

    @Override
    public void hideUploadImageProgress() {
    }

    @Override
    public void updateUploadImagePercentage(final double percentage) {
    }

    @Override
    public void chooseImage() {
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showCurrentUserInfo(Map<String, Object> data) {

    }

    @Override
    public void showDefaultImage() {
    }

    /**
     * Setter method that set view pager to the given number
     * that represented the order of fragments page.
     *
     * @param fragmentNumber
     */
    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }


}
