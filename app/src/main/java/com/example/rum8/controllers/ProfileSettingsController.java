package com.example.rum8.controllers;

import android.util.Log;
import android.widget.Toast;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileSettingsController {

    private ProfileSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;
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

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

}
