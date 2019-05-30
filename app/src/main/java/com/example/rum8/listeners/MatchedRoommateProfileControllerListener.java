package com.example.rum8.listeners;

import android.graphics.Bitmap;

import java.util.Map;

public interface MatchedRoommateProfileControllerListener {

    void showToast(final String message);

    void showMatchedInfo(final Map<String, Object> data);

    void setMatchedUserProfileImage(Bitmap bitmap);
}
