package com.example.rum8.controllers;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.SplashControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class SplashController {

    private SplashControllerListener controllerListener;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    public SplashController(final SplashControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void goToNextActivity() {
        if (userIsLoggedIn(auth.getCurrentUser())) {
            goToMainOrPro(auth.getCurrentUser());
        } else {
            controllerListener.goToLogin();
        }
    }

    private boolean userIsLoggedIn(final FirebaseUser user) {
        return user != null;
    }

    private void goToMainOrPro(final FirebaseUser user) {
        Db.fetchUserInfo(db, auth.getCurrentUser())
            .addOnSuccessListener(documentSnapshot -> {
                final Map<String, Object> data = documentSnapshot.getData();

                // If name has not been entered, go to profile settings
                if (((String) data.get(Db.Keys.FIRST_NAME)).isEmpty()) {
                    controllerListener.goToProfileSettings();
                } else {
                    controllerListener.goToMain();
                }
            })
            .addOnFailureListener(e -> {
                final String message = "Network error";
                controllerListener.showToast(message);
            });
    }
}
