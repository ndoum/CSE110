package com.example.rum8.listeners;

import android.graphics.Bitmap;

public interface ProfileSettingsControllerListener {
    void showToast(final String message);

    void showUploadImageProgress();

    void hideUploadImageProgress();

    void updateUploadImagePercentage(double percentage);

    void chooseImage();

    void setUserProfileImage(Bitmap bitmap);
}
