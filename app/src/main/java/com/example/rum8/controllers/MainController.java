package com.example.rum8.controllers;

import com.example.rum8.listeners.MainControllerListener;

public class MainController {

    private MainControllerListener controllerListener;

    public MainController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public void onGoToProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }
    public void onGoToLoginButtonClicked() {
        controllerListener.goToLogin();
    }

}
