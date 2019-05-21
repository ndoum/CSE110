package com.example.rum8.controllers;

import android.widget.Toast;

import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class AdvancedSettingsController {

    private AdvancedSettingsControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public AdvancedSettingsController(final AdvancedSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onSaveButtonClicked(final Map<String, Object> userHash) {
        Db.updateUser(db,auth.getCurrentUser(),userHash)
                .addOnSuccessListener(aVoid -> {Log.d(TAG, "DocumentSnapshot successfully written");
                    controllerListener.showToast("Saved", Toast.LENGTH_LONG); })
                .addOnFailureListener(e -> controllerListener.showToast("Network error", Toast.LENGTH_LONG));
    }

    public void onGoToProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }

    public void onLogOutButtonClicked() {
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }

    public void onAdvSettingsButtonClicked() {
        controllerListener.goToAdvSettings();
    }

    public void onGoToViewLinkListButtonClicked(){
        controllerListener.goToViewLinkList();
    }

}
