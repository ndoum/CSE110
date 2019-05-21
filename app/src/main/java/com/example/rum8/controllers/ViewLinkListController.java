package com.example.rum8.controllers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
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
    private com.google.firebase.firestore.Query query;
    private Map<String, Object> linkListUidMap; //the hashmap with link uids as keys
    private Set<String> linkListUidSet;
    private FirestoreRecyclerOptions<LinkListSingleLink> options;
    private FirestoreRecyclerAdapter adapter;

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
        }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                linkListUidMap = (HashMap<String, Object>) documentSnapshot.get("matched");
                linkListUidSet = linkListUidMap.keySet();
                populateRecyclerViewContent(linkListUidSet);
            }
        });
    }

    /**
     * Fetch matched link info from db and build recycler view
     */
    public void populateRecyclerViewContent(Set<String> uids){
        //System.out.println(uids);
        for(String uid : uids){

        }


    }

    public Task<byte[]> loadDefaultUserProfileImage(final FirebaseStorage storage){
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadLinkProfileImage(final FirebaseStorage storage, final String linkUid){
        return Db.fetchLinkProfilePicture(storage, linkUid);
    }

}
