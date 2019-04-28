package com.example.rum8.controllers;


import com.example.rum8.listeners.LoginControllerListener;

public class LoginController {
    private LoginControllerListener controllerListener;

    public LoginController(final LoginControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public void onGoToRegistrationButtonClicked() {
        controllerListener.goToRegistration();
    }
    public void onGoToPasswordRecoverClicked() {
        controllerListener.goToPasswordRecover();
    }
}
