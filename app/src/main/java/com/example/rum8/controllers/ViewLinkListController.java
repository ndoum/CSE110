package com.example.rum8.controllers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class ViewLinkListController {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String, Object> linkListUidMap; //the hashmap with link uids as keys
    private Set<String> linkListUidSet;

    private ViewLinkListControllerListener controllerListener;

    /**
     * Constructor
     * */
    public ViewLinkListController(final ViewLinkListControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    /**
     * menu buttons
     * */
    public void onGoToProfileSettingsButtonClicked(){ controllerListener.goToProfileSettings();};
    public void onLogoutButtonClicked(){
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }
    public void onGoToViewLinkListButtonClicked(){ controllerListener.goToViewLinkList();}
    public void onGoToAdvSettingsButtonClicked(){ controllerListener.goToAdvSettings();}

    /**
     * Fetch matched link uids from user's "matched" field
     */
    public void fetchLinkListUidsFromDB(){
        linkListUidMap = new HashMap<>();
        linkListUidSet = new HashSet<>();

        Db.fetchUserInfo(db, auth.getCurrentUser()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Fetch user info failed");
            }
        }).addOnSuccessListener(documentSnapshot -> {
            linkListUidMap = (HashMap<String, Object>) documentSnapshot.get("matched");
            linkListUidSet = linkListUidMap.keySet();
            //System.out.println("FETCHED UID SET");
            controllerListener.populateRecylcerViewContent(linkListUidSet);
            System.out.println("POPULATED!!!!!");
            controllerListener.displayLinks();
        });
    }

    /**
     * Fetch matched link info from db and build recycler view
     */
    /*public void populateRecyclerViewContent(Set<String> uids){
        for(String uid:uids){
            Db.fetchLinkInfo(db, uid).addOnFailureListener(e ->
                    Log.d(TAG, "Fetch matched list failed"))
                    .addOnSuccessListener(documentSnapshot -> {
                        System.out.println("FUNCTION CALL TO ADDNEWLINK");
                        controllerListener.addNewLink((HashMap<String, Object>) documentSnapshot.getData(), uid);
                    });
        }
    }*/

    public Task<byte[]> loadDefaultUserProfileImage(final FirebaseStorage storage){
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadLinkProfileImage(final FirebaseStorage storage, final String linkUid){
        return Db.fetchLinkProfilePicture(storage, linkUid);
    }

}
