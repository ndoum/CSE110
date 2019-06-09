package com.example.rum8.listeners;

/**
 * Interface for splash controller
 */
public interface SplashControllerListener {

    void goToMain();

    void goToLogin();

    void goToProfileSettings();

    void showToast(final String message);
}
