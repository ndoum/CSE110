package com.example.rum8.listeners;

import java.util.Map;

public interface SettingsControllerListener {

    void showCurrentUserInfo(final Map<String, Object> data);

    void showToast(final String message);
    
}
