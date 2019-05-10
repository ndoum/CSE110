package com.example.rum8.controllers;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rum8.R;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private Map<String, Integer> logisticMap;
    private Map<String, Integer> roommateMap;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){

        this.controllerListener = controllerListener;
        this.personalMap = new HashMap<String, Object>();
        this.logisticMap = new HashMap<String, Integer>();
        this.roommateMap = new HashMap<String, Integer>();
        db = FirebaseFirestore.getInstance();
        authStateListener = firebaseAuth -> {

            // Get the current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);

    }

    public void onSubmit(final Map<String, Object> userInfo) {

        final String firstName = (String) userInfo.get("first_name");
        final String lastName = (String) userInfo.get("last_name");

        // check for valid name
        if ((!isPresent(firstName)) || (!isPresent(lastName)) ){
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        }else {
            // upload valid info to firebase
            db.collection("users")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .set(userInfo, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot added"))
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding document", e);
                        controllerListener.showToast("Network error", Toast.LENGTH_SHORT);
                    });
        }
    }

    public void updateMap(String key, int value) {
        logisticMap.put(key, value);

    }
    public void uploadFrag(Map<String, Integer> map, String path) {

        // upload valid info to firebase
        db.collection(path)
            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .set(map, SetOptions.merge())
            .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot added"))
            .addOnFailureListener(e -> {
                Log.w(TAG, "Error adding document", e);
                controllerListener.showToast("Network error", Toast.LENGTH_SHORT);
            });
    }
    public void populate() {
        uploadFrag(logisticMap, "personal_preferences");
        uploadFrag(roommateMap, "roommate_preferences");
    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }


}
