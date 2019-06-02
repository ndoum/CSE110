package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

public interface PreviewProfileControllerListener {

    void showToast(final String message);

    void showCurrentUserInfo(final Map<String, Object> data);

    void setFragment();

    void setUserProfileImage(Bitmap bitmap);

    void showDefaultImage();

}
