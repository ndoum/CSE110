package com.example.rum8.controllers;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.database.Db;
import com.example.rum8.fragments.ProfileSettingsGeneralInfoFragment;
import com.example.rum8.fragments.ProfileSettingsRoommatePreferencesFragment;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileSettingsController {

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


    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener) {
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

    public void updateMap(String key, int value) {
        logisticMap.put(key, value);
    }

    public void updatePersonalMap(String key, int value) {
        personalMap.put(key, value);
    }

    public void updatePersonalKey(int index, int value){
        this.selfMatchIds[index] = value;
    }

    public void updateRoommateMap(String key, int value) {
        roommateMap.put(key, value);
    }

    public void updateRoommateKey(int index, int value){
        this.roommateMatchIds[index] = value;
    }



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
        Db.updateSelfMatchIds(db, auth.getCurrentUser(), updateRoommateMatchIds);

    }






    public void uploadFrag(Map<String, Integer> map) {

        // upload valid info to firebase
        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(map, SetOptions.merge()).addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot added"))
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding document", e);
                    controllerListener.showToast("Network error", Toast.LENGTH_SHORT);
                });
    }

    public void populate(View rootView) {
    }

    public void generateString(String s) {

    }

    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

}
