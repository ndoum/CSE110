package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;



import java.util.Map;

public class MatchedRoommateProfileController {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private MatchedRoommateProfileControllerListener controllerListener;
    private String hardCodeUserId = "n1x4sBfUHIXD6xzDw33yszFI5yQ2";

    public MatchedRoommateProfileController(final MatchedRoommateProfileControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.storage = FirebaseStorage.getInstance();
    }


    /**
     * use user's potential list to find other other show other user's info
     */
    public void loadMatchUserInfo(String userId) {
        Db.fetchUserInfoById(this.db, "g7v3yHWDvVbLFKdqPuxAclvM0N82").addOnSuccessListener(documentSnapshot -> {

            System.out.println("********************************");
            final Map<String, Object> matchedUserData = documentSnapshot.getData();

            System.out.println(matchedUserData.get("living_accommodations"));
            System.out.println("xxxxxxxxxxxxxxx");
            controllerListener.showMatchedInfo(matchedUserData);


        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });





    }



}
