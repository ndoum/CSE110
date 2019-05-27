package com.example.rum8.controllers;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
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

    /**
     * use user's potential list to find other other
     * show other user's info
     */
    public void loadUserInfo() {
        Db.fetchUserInfo(this.db, this.auth.getCurrentUser()).addOnSuccessListener(documentSnapshot -> {

            final Map<String, Object> data = documentSnapshot.getData();
            final HashMap<String, Object> potential = (HashMap<String, Object>) data.get(Db.Keys.POTENTIAL);

            // when potential is not empty, show the first user in potential
            if (potential.keySet().size() > 0) {

                // get other user's id
                final String userId = (String) potential.keySet().toArray()[0];
                Db.fetchUserInfoById(this.db, userId).addOnSuccessListener(documentSnapshotOther -> {
                    controllerListener.showCurrentUserInfo(data);



                    // show other user's info
                    final Map<String, Object> otherUserdata = documentSnapshotOther.getData();
                    controllerListener.showCurrentUserInfo(otherUserdata);

                }).addOnFailureListener(exception -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
            }

        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });
    }



}
