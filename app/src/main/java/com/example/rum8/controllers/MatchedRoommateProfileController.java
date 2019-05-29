package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.HashMap;
import java.util.Map;

public class MatchedRoommateProfileController {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private MainControllerListener controllerListener;
    private String hardCodeUserId;

    public MatchedRoommateProfileController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.storage = FirebaseStorage.getInstance();
    }


    /**
     * use user's potential list to find other other show other user's info
     */
    public void loadMatchUserInfo() {
        Db.fetchUserInfoById(this.db, hardCodeUserId).addOnSuccessListener(documentSnapshot -> {
            final Map<String, Object> userData = documentSnapshot.getData();
            final HashMap<String, Object> matchedMap = (HashMap<String, Object>) userData.get(Db.Keys.MATCHED);


        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });





    }



}
