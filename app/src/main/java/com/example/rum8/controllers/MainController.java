package com.example.rum8.controllers;

import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainController {

    private MainControllerListener controllerListener;
    private FirebaseAuth auth;

    public MainController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
    }

    public void onAppLaunch() {
        if (!userIsLoggedIn(auth.getCurrentUser())) {
            controllerListener.goToLogin();
        }
    }

    private boolean userIsLoggedIn(final FirebaseUser user) {
        return user != null;
    }

    public void onGoToProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }

    public void onLogOutButtonClicked() {
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }

}
