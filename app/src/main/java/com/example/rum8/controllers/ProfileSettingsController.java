package com.example.rum8.controllers;

import android.net.Uri;
import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

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
    private Map<String, Object> userMap;


    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        userMap = new HashMap<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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

    public Task<DocumentSnapshot> loadUserInfo(final FirebaseFirestore firestore, final FirebaseUser user){
        return Db.fetchUserInfo(firestore, user);
    }

    public Task<byte[]> loadDefaluUserProfileImage(final FirebaseStorage storage) {
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadUserProfileImage(final FirebaseStorage storage, final FirebaseUser user) {
        return Db.fetchUserProfilePicture(storage, user);
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

        /**
         * TODO: update here user's sets here
         */
    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

}
