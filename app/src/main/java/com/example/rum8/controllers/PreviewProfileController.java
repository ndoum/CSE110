package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.Map;

public class PreviewProfileController {
    private MainControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    public PreviewProfileController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public void loadUserInfo() {

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
                                // fetch default if the user does not upload
                                Db.fetchDefaultUserProfilePicture(storage).addOnSuccessListener(bytes -> {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    controllerListener.setUserProfileImage(bmp);
                                });
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
}
