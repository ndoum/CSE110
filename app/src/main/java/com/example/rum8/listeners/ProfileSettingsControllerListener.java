package com.example.rum8.listeners;

import java.util.Map;

public interface ProfileSettingsControllerListener {
    void showToast(final String message);

    void showCurrentUserInfo(Map<String, Object> data);

    void goToMainPage();
}
