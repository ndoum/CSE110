package com.example.rum8.controllers;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private Map personalMap;
    private Map logisticMap;
    private Map roommateMap;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){

        this.controllerListener = controllerListener;
        this.personalMap = new HashMap();
        this.logisticMap = new HashMap();
        this.roommateMap = new HashMap();
        db = FirebaseFirestore.getInstance();
        authStateListener = firebaseAuth -> {

            // Get the current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        };

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);

    }

    public void onSubmit(final Map<String, Object> userInfo) {

        final String firstName = (String) userInfo.get("first_name");
        final String lastName = (String) userInfo.get("last_name");

        // check for valid name
        if ((!isPresent(firstName)) || (!isPresent(lastName)) ){
            final String message = "Please enter your first and last name";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        }else {
            // upload valid info to firebase
            db.collection("users")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .set(userInfo, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot added"))
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding document", e);
                        controllerListener.showToast("Network error", Toast.LENGTH_SHORT);
                    });
        }
    }
    public void populate(View rootView) {
        final Map<String, Object> logistic = new HashMap<>();
        RadioButton cleanY = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_yes);
        RadioButton cleanN = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_no);
        RadioButton cleanNP = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_no_pref);
        decideWhich(cleanY, cleanN, cleanNP, logistic);
        onSubmit(logistic);
    }

    public void decideWhich (RadioButton yes, RadioButton no, RadioButton np, Map<String, Object> logistic){
        if(yes.isChecked()) {
            logistic.put("cleanliness", "y");
        } else if (no.isChecked()) {
            logistic.put("cleanliness", "n");
        } else if (np.isChecked()) {
            logistic.put("cleanliness", "np");
        }
    }
    public void generateString (String s) {

    }



    // helper method to check if user input is present
    private static boolean isPresent(final String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }


}
