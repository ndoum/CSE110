package com.example.rum8.controllers;

import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.SettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SettingsController {

    private SettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public SettingsController(final SettingsControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSaveButtonClicked(final Map<String, Object> userHash) {
        Db.updateUser(db, auth.getCurrentUser(), userHash)
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "DocumentSnapshot successfully written");
                    controllerListener.showToast("Saved");
                })
                .addOnFailureListener(e -> controllerListener.showToast("Network error"));
    }

    public void loadUserInfo() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
                .addOnSuccessListener(documentSnapshot -> {
                    final Map<String, Object> data = documentSnapshot.getData();
                    controllerListener.showCurrentUserInfo(data);
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
    }

}
