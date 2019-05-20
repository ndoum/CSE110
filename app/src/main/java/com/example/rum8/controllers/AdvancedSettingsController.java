package com.example.rum8.controllers;

import android.widget.Toast;

import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdvancedSettingsController {

    private AdvancedSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public AdvancedSettingsController(final AdvancedSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSaveButtonClicked() {
        controllerListener.showToast("SAVED", Toast.LENGTH_LONG);
    }

    public void onGoToProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }

    public void onLogOutButtonClicked() {
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }

    public void onAdvSettingsButtonClicked() {
        controllerListener.goToAdvSettings();
    }

    public void onGoToViewLinkListButtonClicked(){
        controllerListener.goToViewLinkList();
    }

}
