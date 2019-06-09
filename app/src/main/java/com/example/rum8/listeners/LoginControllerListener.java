package com.example.rum8.listeners;

/**
 * Interface for login controller
 */
public interface LoginControllerListener {

    void goToPasswordRecovery();

    void goToRegistration();

    void goToMainPage();

    void goToProfileSettings();

    void showToast(final String message);

}
