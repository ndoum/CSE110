package com.example.rum8.listeners;

import com.google.firebase.firestore.DocumentSnapshot;

public interface AdvancedSettingsControllerListener {

    void goToProfileSettings();

    void goToLogin();

    void goToAdvSettings();

    void showToast(final String message);

    void showCurrentUserInfo(final DocumentSnapshot documentSnapshot);
}
