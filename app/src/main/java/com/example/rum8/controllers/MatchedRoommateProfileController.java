package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;


import java.util.Map;

public class MatchedRoommateProfileController {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private MatchedRoommateProfileControllerListener controllerListener;
    private String hardCodeUserId = "jTO5PTvDRQfo2PF7DbBF4ac9Tay2";

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

        Db.fetchUserProfilePictureById(this.storage, "jTO5PTvDRQfo2PF7DbBF4ac9Tay2").addOnSuccessListener(bytes -> {

            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            controllerListener.setMatchedUserProfileImage(bmp);

        }).addOnFailureListener(exception -> {
            // fetch default if the user does not upload
            Db.fetchDefaultUserProfilePicture(this.storage).addOnSuccessListener(bytes -> {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                controllerListener.setMatchedUserProfileImage(bmp);
            });
            // show error message if both way fails
            int errorCode = ((StorageException) exception).getErrorCode();
            if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                final String message = "Network error";
                controllerListener.showToast(message);
            }
        });


        Db.fetchUserInfoById(this.db, "g7v3yHWDvVbLFKdqPuxAclvM0N82").addOnSuccessListener(documentSnapshot -> {


            final Map<String, Object> matchedUserData = documentSnapshot.getData();


            // fetch other user's profile picture







            controllerListener.showMatchedInfo(matchedUserData);


        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });





    }



}
