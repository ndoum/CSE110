package com.example.rum8.listeners;

public interface ProfileSettingsControllerListener {
    void showToast(final String message);
    void showUploadImageProgress();
    void hideUploadImageProgress();
    void updateUploadImagePercentage(double percentage );
    void chooseImage();
}
