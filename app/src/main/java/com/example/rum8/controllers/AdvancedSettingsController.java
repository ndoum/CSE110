package com.example.rum8.controllers;

import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class AdvancedSettingsController {

    private AdvancedSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public AdvancedSettingsController(final AdvancedSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSaveButtonClicked(final Map<String, Object> userHash) {
        Db.updateUser(db,auth.getCurrentUser(),userHash)
                .addOnSuccessListener(aVoid -> {Log.d(TAG, "DocumentSnapshot successfully written");
                    controllerListener.showToast("Saved"); })
                .addOnFailureListener(e -> controllerListener.showToast("Network error"));
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

    public Task<DocumentSnapshot> loadUserInfo(){
        return Db.fetchUserInfo(this.db, this.auth.getCurrentUser());
    }


}
