package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

public interface MainControllerListener {

    void goToProfileSettings();

    void goToLogin();

    void goToLinkList();

    void goToAdvancedProfileSettings();

    void goToProfilePreview();

    void showToast(final String message);

    void showCurrentUserInfo(final Map<String, Object> data);

    void setFragment();

    void setFragmentEmpty();

    void setUserProfileImage(Bitmap bitmap);

    void showDefaultImage();

    void showPopup();

}
