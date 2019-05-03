package com.example.rum8.listeners;

public interface LoginControllerListener {

    void goToPasswordRecover();
    void goToRegistration();
    void goToMainPage();
    void showToast(final String message, final int toastLength);
}
