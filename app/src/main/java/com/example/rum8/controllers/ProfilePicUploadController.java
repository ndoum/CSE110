package com.example.rum8.controllers;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rum8.listeners.ProfilePicUploadControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;


import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfilePicUploadController {

    private ProfilePicUploadControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseStorage storage;

    public ProfilePicUploadController(final ProfilePicUploadControllerListener controllerListener){

        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(final @NonNull FirebaseAuth firebaseAuth) {
                // Get the current user
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);

        storage = FirebaseStorage.getInstance();
    }

    public void onSubmit(){

    }

}
