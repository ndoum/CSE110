package com.example.rum8.controllers;

import com.example.rum8.listeners.SplashControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashController {

    private SplashControllerListener controllerListener;
    private FirebaseAuth auth;

    public SplashController(final SplashControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
    }

    public void goToNextPage() {
        if (userIsLoggedIn(auth.getCurrentUser())) {
            controllerListener.goToMainPage();
        } else {
            controllerListener.goToLogin();
        }
    }

    private boolean userIsLoggedIn(final FirebaseUser user) {
        return user != null;
    }

}
