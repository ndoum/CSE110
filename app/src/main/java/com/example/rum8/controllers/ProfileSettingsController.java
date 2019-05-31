package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Class that implements the controller for profile settings activity.
 * It serves as a communication between the view and the model in profile
 * settings activities.
 */
public class ProfileSettingsController {

    private static final double ONE_HUNDRED_PERCENT = 100.0;
    private ProfileSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private Map<String, Object> userMap;
    public String usernameEntered;


    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        userMap = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        return name != null && !name.isEmpty();
    }

    public void onSubmit(final Map<String, Object> userInfo) {
        final String firstName = (String) userInfo.get(Db.Keys.FIRST_NAME);
        final String lastName = (String) userInfo.get(Db.Keys.LAST_NAME);

        // check for valid name
        if ((!isPresent(firstName)) || (!isPresent(lastName))) {
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message);
        } else {
            Db.updateUser(db, auth.getCurrentUser(), userInfo)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        }
    }

    public void onChooseImageCliked() {
        controllerListener.chooseImage();
    }

    public void onUploadImageClicked(final Uri filePath) {
        if (filePath != null) {
            final FirebaseUser user = auth.getCurrentUser();
            final FirebaseStorage storage = FirebaseStorage.getInstance();
            controllerListener.showUploadImageProgress();
            Db.updateProfilePicture(storage, user, filePath)
                .addOnSuccessListener(taskSnapshot -> {
                    controllerListener.hideUploadImageProgress();
                    final String message = "Successfully uploaded";
                    controllerListener.showToast(message);
                })
                .addOnFailureListener(e -> {
                    controllerListener.hideUploadImageProgress();
                    final String message = "Network error";
                    controllerListener.showToast(message);
                })
                .addOnProgressListener(taskSnapshot -> {
                    double progress = (ONE_HUNDRED_PERCENT * taskSnapshot.getBytesTransferred() / taskSnapshot
                        .getTotalByteCount());
                    controllerListener.updateUploadImagePercentage(progress);
                });
        }
    }

    public void loadUserInfo() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
            .addOnSuccessListener(documentSnapshot -> {
                final Map<String, Object> data = documentSnapshot.getData();
                controllerListener.showCurrentUserInfo(data);
            })
            .addOnFailureListener(e -> {
                final String message = "Network error";
                controllerListener.showToast(message);
            });
    }

    public void loadUserProfileImage() {
        Db.fetchUserProfilePicture(storage, auth.getCurrentUser())
            .addOnSuccessListener(bytes -> {
                final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                controllerListener.setUserProfileImage(bmp);
            })
            .addOnFailureListener(e -> {
                // fetch default if the user does not upload
                Db.fetchDefaultUserProfilePicture(storage)
                    .addOnSuccessListener(bytes -> {
                        final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        controllerListener.setUserProfileImage(bmp);
                    });
                // show error message if both way fails
                int errorCode = ((StorageException) e).getErrorCode();
                if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                }
            });
    }

    /**
     * Method that updates user hash map with the passed in key and value.
     *
     * @param key,   key
     * @param value, value
     */
    public void updateUserMap(final String key, final int value) {
        userMap.put(key, value);
    }

    /**
     * Method that saves/updates user in database.
     */
    public void submitUserMap() {
        Db.updateUser(db, auth.getCurrentUser(), userMap)
            .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
            .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        final String firstName = (String) userMap.get(Db.Keys.FIRST_NAME);
        final String lastName = (String) userMap.get(Db.Keys.LAST_NAME);

        // check for valid name
        if (!isPresent(usernameEntered)) {
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message);
            return;
        }

        controllerListener.goToMainPage();
    }
}
