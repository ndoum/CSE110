package com.example.rum8.controllers;

import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.PreferencesControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Class that implements the controller for preferences activity.
 * It serves as a communication between the view and the model in profile
 * settings activities.
 */
public class PreferencesController {

    private PreferencesControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String, Object> userMap;


    public PreferencesController(final PreferencesControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        userMap = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        return name != null && !name.isEmpty();
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

    /**
     * Method that updates user hash map with the passed in key and value.
     *
     * @param key,   key
     * @param value, value
     */
    public void updateUserMap(final String key, final int value) {
        userMap.put(key, value);
    }

    /**
     * Method that saves/updates user in database.
     */
    public void submitUserMap() {
       Db.updateUser(db, auth.getCurrentUser(), userMap)
            .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
           .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
    }
}
