package com.example.rum8.listeners;

import java.util.Map;

/**
 * Interface for preferences controller
 */
public interface PreferencesControllerListener {
    void showToast(final String message);

    void showCurrentUserInfo(Map<String, Object> data);

    void goToMainPage();
}
