package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.Map;

/**
 * Class that contains the controller that serves as a communication
 * between Matched Roommate Profile Activity and the database model.
 */
public class MatchedRoommateProfileController {
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private MatchedRoommateProfileControllerListener controllerListener;

    public MatchedRoommateProfileController(final MatchedRoommateProfileControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        this.db = FirebaseFirestore.getInstance();
        this.storage = FirebaseStorage.getInstance();
    }

    /**
     * use user's potential list to find other other show other user's info
     */
    public void loadMatchUserInfo(String userId) {

        Db.fetchUserProfilePictureById(this.storage, userId).addOnSuccessListener(bytes -> {

            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            controllerListener.setMatchedUserProfileImage(bmp);

        }).addOnFailureListener(exception -> {
            // fetch default if the user does not upload
            controllerListener.showDefaultImage();
            // show error message if both way fails
            int errorCode = ((StorageException) exception).getErrorCode();
            if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                final String message = "Network error";
                controllerListener.showToast(message);
            }
        });


        Db.fetchUserInfoById(this.db, userId).addOnSuccessListener(documentSnapshot -> {


            final Map<String, Object> matchedUserData = documentSnapshot.getData();

            controllerListener.showMatchedInfo(matchedUserData);

        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });

    }

    public void loadMatchUserContactInfo(String userId){
        Db.fetchUserInfoById(this.db, userId).addOnSuccessListener(documentSnapshot -> {
            final Map<String, Object> matchedUserData = documentSnapshot.getData();
            controllerListener.showMatchedInfo(matchedUserData);
        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });


    }

}
