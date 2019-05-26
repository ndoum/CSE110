package com.example.rum8.listeners;

import java.util.Map;

public interface MainControllerListener {

    void goToProfileSettings();

    void goToLogin();

    void goToAdvSettings();

    void showToast(final String message);

    void showCurrentUserInfo(final Map<String, Object> data);

}
