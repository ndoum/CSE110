package com.example.rum8.controllers;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth.AuthStateListener authStateListener;
    private Map<String, Object> personalMap;
    private Map<String, Object> logisticMap;
    private Map<String, Object> roommateMap;
    private Map<String, Object> personalMatchIds;
    private int [] selfMatchIds;
    private int [] roommateMatchIds;


    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;
        this.personalMap = new HashMap<String, Object>();
        this.logisticMap = new HashMap<String, Object>();
        this.roommateMap = new HashMap<String, Object>();
        this.selfMatchIds =  new int[8];
        this.roommateMatchIds = new int[9];
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSubmit(final Map<String, Object> userInfo) {
        final String firstName = (String) userInfo.get("first_name");
        final String lastName = (String) userInfo.get("last_name");

        // check for valid name
        if ((!isPresent(firstName)) || (!isPresent(lastName))) {
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else {
            Db.updateUser(db, auth.getCurrentUser(), userInfo)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                    .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        }
    }

    public void onChooseImageCliked(){
        controllerListener.chooseImage();
    }

    public void onUploadImageClicked( final Uri filePath ) {
        if (filePath != null) {
            final FirebaseUser user = auth.getCurrentUser();
            final FirebaseStorage storage = FirebaseStorage.getInstance();
            controllerListener.showUploadImageProgress();
            Db.updateProfilePicture(storage, user, filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        controllerListener.hideUploadImageProgress();
                        final String message = "Successfully uploaded";
                        controllerListener.showToast(message, Toast.LENGTH_SHORT);
                    })
                    .addOnFailureListener(e -> {
                        controllerListener.hideUploadImageProgress();
                        final String message = "Network error";
                        controllerListener.showToast(message, Toast.LENGTH_SHORT);
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (ONE_HUNDRED_PERCENT * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        controllerListener.updateUploadImagePercentage(progress);
                    });
        }
    }

    public Task<byte[]> loadDefaluUserProfileImage(FirebaseStorage storage){
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadUserProfileImage(FirebaseStorage storage, FirebaseUser user){
        return Db.fetchUserProfilePicture(storage, user);
    }


    /**
     * Method that updates user's personal question response
     * hash map with the passed in key and value.
     * @param key, question title
     * @param value, question response
     */
    public void updatePersonalMap(String key, int value) {
        personalMap.put(key, value);
    }

    /**
     * Method that update user's personal question response
     * Array that will later help to form user's self matched ids.
     * @param index
     * @param value
     */
    public void updatePersonalKey(int index, int value){
        this.selfMatchIds[index] = value;
    }

    /**
     * Method that updates user's roommate preference question
     * response hash map with the passed in key and value.
     * @param key, question title
     * @param value, question response
     */
    public void updateRoommateMap(String key, int value) {
        roommateMap.put(key, value);
    }

    /**
     * Method that update user's roommate preferences response
     * Array that will later help to form user's self matched ids.
     * @param index
     * @param value
     */
    public void updateRoommateKey(int index, int value){
        this.roommateMatchIds[index] = value;
    }


    /**
     * Method that saves/updates user personal logistic questions response
     * in database and create a unique self matched id that is based
     * on the responses.
     */
    public void personalSaveSubmit(){
        String personalMatchString = "";
        Map<String, Object> updatePersonalMatchIds =  new HashMap<String, Object>();
        Db.updatePersonalPreferences(db, auth.getCurrentUser(),personalMap)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        for(int i = 0; i<this.selfMatchIds.length;i++){
            personalMatchString += Integer.toString(this.selfMatchIds[i]);
        }
        updatePersonalMatchIds.put("self_match_group_id", personalMatchString);
        Db.updateSelfMatchIds(db, auth.getCurrentUser(), updatePersonalMatchIds);

    }

    /**
     * Method that saves/updates roommate preferences questions response
     * in database and create a unique roommate preferences id that is based
     * on the responses.
     */
    public void roommateSaveSubmit(){
        String roommateMatchString = "";
        Map<String, Object> updateRoommateMatchIds =  new HashMap<String, Object>();
        Db.updateRoommatePreferences(db, auth.getCurrentUser(),roommateMap)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.d(TAG, "Error adding document"));
        for(int i = 0; i<this.roommateMatchIds.length;i++){
            roommateMatchString += Integer.toString(this.roommateMatchIds[i]);
        }
        updateRoommateMatchIds.put("preference_match_group_id", roommateMatchString);
        Db.updateRoommateMatchIds(db, auth.getCurrentUser(), updateRoommateMatchIds);

    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

}
