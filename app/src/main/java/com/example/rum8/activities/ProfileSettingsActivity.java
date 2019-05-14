package com.example.rum8.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ProfileSettingsViewPagerAdapter;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

public class ProfileSettingsActivity extends FragmentActivity
        implements ProfileSettingsControllerListener {

    private static final int PICK_IMAGE_REQUEST = 65537;
    private ProfileSettingsController controller;
    private ViewPager viewPager;
    private Uri filePath;
    private Bitmap bitmap;

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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if( isResultValid(resultCode,requestCode) && isDataValid(data) ) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            }
            catch (final Exception e)
            {
                e.printStackTrace();
                showToast("Network Error", Toast.LENGTH_SHORT);
            }
        }
    }

    private boolean isResultValid(final int resultCode, final int requestCode){
        return (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST);
    }

    private boolean isDataValid(final Intent data){
        return (data != null && data.getData() != null);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        viewPager = findViewById(R.id.profile_settings_view_pager);
        viewPager.setAdapter(new ProfileSettingsViewPagerAdapter(getSupportFragmentManager()));
    }

    private void initController() {
        controller = new ProfileSettingsController(this);
    }

    public Uri getFilePath (){
        return filePath;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }


    @Override
    public void showUploadImageProgress(){}

    @Override
    public void hideUploadImageProgress(){}

    @Override
    public void updateUploadImagePercentage(double percentage){}

    @Override
    public void chooseImage(){}

}
