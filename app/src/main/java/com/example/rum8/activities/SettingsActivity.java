package com.example.rum8.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.SettingsViewPagerAdapter;
import com.example.rum8.listeners.SettingsControllerListener;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

/**
 * Class that handles basic user settings.
 */
public class SettingsActivity extends AppCompatActivity implements SettingsControllerListener {

    // Initialize class variable
    private static final int PICK_IMAGE_REQUEST = 65537;
    private Uri filePath;
    private Bitmap bitmap;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
    }

    private void initViews() {
        viewPager = findViewById(R.id.settings_view_pager);
        viewPager.setAdapter(new SettingsViewPagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.settings_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
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

    public Uri getFilePath() {
        return filePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void goToMain() {
        final Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
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
    public void showDefaultImage() {
    }

    @Override
    public void setUserProfileImage(final Bitmap bitmap) {
    }

    @Override
    public void showToast(final String message) {

    }
}
