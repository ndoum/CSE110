package com.example.rum8.listeners;

public interface AdvancedSettingsControllerListener {

    void goToProfileSettings();

    void goToLogin();

    void goToAdvSettings();

    void showToast(final String message, final int toastLength);

    void goToViewLinkList();
}
