package com.example.rum8.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rum8.R;
import com.example.rum8.controllers.PreviewProfileController;
import com.example.rum8.fragments.PreviewProfileFragment;
import com.example.rum8.listeners.PreviewProfileControllerListener;

import java.util.Map;

public class PreviewProfileActivity extends AppCompatActivity implements PreviewProfileControllerListener {

    private PreviewProfileController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_profile);
        initController();
    }

    private void initController() {
        controller = new PreviewProfileController(this);
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(PreviewProfileActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCurrentUserInfo(Map<String, Object> data) {

    }

    @Override
    public void setFragment() {
        showFragment(new PreviewProfileFragment());
    }

    private void showFragment(final Fragment fragment) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}
