package com.example.rum8.controllers;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rum8.R;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileSettingsController {

    private ProfileSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Map<String, Object> personalMap;
    private Map<String, Object> logisticMap;
    private Map<String, Integer> roommateMap;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        this.personalMap = new HashMap<String, Object>();
        this.logisticMap = new HashMap<String, Object>();
        this.roommateMap = new HashMap<String, Integer>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSubmit(final Map<String, Object> userInfo) {
        final String firstName = (String) userInfo.get("first_name");
        final String lastName = (String) userInfo.get("last_name");

        // check for valid name
        if ((!isPresent(firstName)) || (!isPresent(lastName))) {
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else {
            Db.updateUser(db, auth.getCurrentUser(), userInfo)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                    .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        }
    }

    public void updateMap(String key, int value) {
        logisticMap.put(key, value);
    }

    public void updatePersonalMap(String key, int value) {
        logisticMap.put(key, value);
    }

    public void personalSaveSubmit(){
        Db.updatePersonalPreferences(db, auth.getCurrentUser(),logisticMap)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
    }




    public void uploadFrag(Map<String, Integer> map) {

        // upload valid info to firebase
        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(map, SetOptions.merge()).addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot added"))
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding document", e);
                    controllerListener.showToast("Network error", Toast.LENGTH_SHORT);
                });
    }

    public void populate(View rootView) {
    }

    public void generateString(String s) {

    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

}
