package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

public interface SettingsControllerListener {

    void showCurrentUserInfo(final Map<String, Object> data);

    void showUploadImageProgress();

    void hideUploadImageProgress();

    void updateUploadImagePercentage(double percentage);

    void chooseImage();

    void showDefaultImage();

    void setUserProfileImage(Bitmap bitmap);

    void showToast(final String message);
    
}
