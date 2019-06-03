package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.PreviewProfileControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.Map;

public class PreviewProfileController {
    private PreviewProfileControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    public PreviewProfileController(final PreviewProfileControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public void loadUserInfo() {

        controllerListener.setFragment();

        final String userId = auth.getUid();
        Db.fetchUserInfoById(db, userId)
                .addOnSuccessListener(documentSnapshotOther -> {

                    // fetch user's profile picture
                    Db.fetchUserProfilePictureById(storage, userId)
                            .addOnSuccessListener(bytes -> {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                controllerListener.setUserProfileImage(bmp);
                            })
                            .addOnFailureListener(e -> {
                                // show default if the user does not upload
                                controllerListener.showDefaultImage();
                                // show error message if both way fails
                                int errorCode = ((StorageException) e).getErrorCode();
                                if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                                    final String message = "Network error";
                                    controllerListener.showToast(message);
                                }
                            });

                    // show user's info
                    final Map<String, Object> userdata = documentSnapshotOther.getData();
                    controllerListener.showCurrentUserInfo(userdata);
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
    }

    public void loadMatchUserContactInfo() {
        Db.fetchUserInfoById(this.db, this.auth.getUid()).addOnSuccessListener(documentSnapshot -> {
            final Map<String, Object> matchedUserData = documentSnapshot.getData();
            controllerListener.showCurrentUserInfo(matchedUserData);
        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            controllerListener.showToast(message);
        });
    }
}
