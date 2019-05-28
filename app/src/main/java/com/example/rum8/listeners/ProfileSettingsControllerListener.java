package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

public interface ProfileSettingsControllerListener {
    void showToast(final String message);

    void showUploadImageProgress();

    void hideUploadImageProgress();

    void updateUploadImagePercentage(double percentage);

    void chooseImage();

    void setUserProfileImage(Bitmap bitmap);

    void showCurrentUserInfo(Map<String, Object> data);
}
