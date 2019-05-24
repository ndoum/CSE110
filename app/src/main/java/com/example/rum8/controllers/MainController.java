package com.example.rum8.controllers;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class MainController {

    private MainControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public MainController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
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

    public void onLinkButtonClicked() {
        controllerListener.showToast("LIKED");
    }

    public void onNotLinkButtonClicked() {
        controllerListener.showToast("NOT LIKED");
    }

    public void loadUserInfo() {
        Db.fetchUserInfo(this.db, this.auth.getCurrentUser()).addOnSuccessListener(documentSnapshot -> {
            final Map<String, Object> data = documentSnapshot.getData();
            controllerListener.showCurrentUserInfo(data);
        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });
    }

}
