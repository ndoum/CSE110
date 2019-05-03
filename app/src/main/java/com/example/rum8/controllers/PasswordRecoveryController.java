package com.example.rum8.controllers;

import com.example.rum8.listeners.PasswordRecoveryControllerListener;

public class PasswordRecoveryController {
    private PasswordRecoveryControllerListener controllerListener;

    public PasswordRecoveryController(final PasswordRecoveryControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public void onGoBackToLoginButtonClicked(){ this.controllerListener.goBackToLogin();}
}
