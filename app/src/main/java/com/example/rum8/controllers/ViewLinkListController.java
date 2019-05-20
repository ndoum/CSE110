package com.example.rum8.controllers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class ViewLinkListController {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String, Object> linkListUidMap;
    private ViewLinkListControllerListener controllerListener;

    public ViewLinkListController(final ViewLinkListControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void onGoToProfileSettingsButtonClicked(){ controllerListener.goToProfileSettings();};
    public void onLogoutButtonClicked(){
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }
    public void onGoToViewLinkListButtonClicked(){ controllerListener.goToViewLinkList();}
    public void onGoToAdvSettingsButtonClicked(){ controllerListener.goToAdvSettings();}

    public void fetchLinkList(){
        Db.fetchUserInfo(db, auth.getCurrentUser()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error loading user info");
            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                linkListUidMap = (Map<String, Object>) documentSnapshot.get("matched");
                System.out.println("Getting matched links...");
                System.out.println(linkListUidMap.keySet());
            }
        });
    }
    public Task<byte[]> loadDefaultUserProfileImage(final FirebaseStorage storage){
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadLinkProfileImage(final FirebaseStorage storage, final String linkUid){
        return Db.fetchLinkProfilePicture(storage, linkUid);
    }

}
