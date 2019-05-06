package com.example.rum8.controllers;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileSettingsController {

    private ProfileSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){

        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        // Listener to check the status of registration
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(final @NonNull FirebaseAuth firebaseAuth) {

                // Get the current user
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);

    }

    public void onSubmit(final String firstName, final String lastName) {
        if ((!isValidName(firstName)) || (!isValidName(lastName)) ){
            final String message = "Please use your first and last name";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("first_name",firstName);
        userInfo.put("last_name", lastName);
        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private static boolean isValidName(final String name) {
        if (name == null) {
            return false;
        }
        return true;
    }


}
