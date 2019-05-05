package com.example.rum8.controllers;

import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileSettingsController {
    private ProfileSettingsControllerListener controllerListener;

    // Access a Cloud Firestore instance from Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;

        // get current user
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();



    }
}
