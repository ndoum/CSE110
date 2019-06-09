package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

/**
 * Interface for main controller
 */
public interface MainControllerListener {

    void goToProfileSettings();

    void goToLogin();

    void goToLinkList();

    void goToSettings();

    void goToProfilePreview();

    void showToast(final String message);

    void showCurrentUserInfo(final Map<String, Object> data);

    void setFragment();

    void setFragmentEmpty();

    void setUserProfileImage(Bitmap bitmap);

    void showPopup();

    void showDefaultImage();

}
