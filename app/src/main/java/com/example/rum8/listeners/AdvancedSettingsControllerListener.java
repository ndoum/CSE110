package com.example.rum8.listeners;

import java.util.Map;

public interface AdvancedSettingsControllerListener {

    void goToLogin();

    void showToast(final String message);

    void showCurrentUserInfo(final Map<String, Object> data);

}
