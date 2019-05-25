package com.example.rum8.controllers;

import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainController {

    private MainControllerListener controllerListener;

    public MainController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public void onGoToProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }

    public void onGoToAdvSettingsButtonClicked() {
        controllerListener.goToAdvSettings();
    }

    public void onLogOutButtonClicked() {
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }

    public void onGoToLinkListButtonClicked() {controllerListener.goToLinkList();}

    public void onLinkButtonClicked() {
        controllerListener.showToast("LIKED");
    }

    public void onNotLinkButtonClicked() {
        controllerListener.showToast("NOT LIKED");
    }


}
