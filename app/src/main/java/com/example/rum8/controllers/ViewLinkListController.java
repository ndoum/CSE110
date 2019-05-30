package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class ViewLinkListController {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private Map<String, Object> linkListUidMap;
    private ViewLinkListControllerListener controllerListener;

    public ViewLinkListController(final ViewLinkListControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    /**
     * Fetch matched link uids from user's "matched" field
     */
    public void prepareLinks() {
        linkListUidMap = new HashMap<>();
        // fetch current user's documentation
        Db.fetchUserInfo(db, auth.getCurrentUser()).addOnCompleteListener((Task<DocumentSnapshot> task) -> {
            if (task.isSuccessful()) {
                // fetch current user's matched links
                linkListUidMap = (HashMap<String, Object>) task.getResult().get(Db.Keys.MATCHED);
                for (String uid : linkListUidMap.keySet()) {
                    // fetch link's profile image
                    Db.fetchUserInfoById(db, uid).addOnCompleteListener((Task<DocumentSnapshot> task1) -> {
                        if (task1.isSuccessful()) {
                            Db.fetchUserProfilePictureById(storage, uid).addOnSuccessListener((byte[] bytes) -> {
                                displayLink(uid, task1, bytes);
                            }).addOnFailureListener(
                                    e -> Db.fetchDefaultUserProfilePicture(storage).addOnSuccessListener(bytes -> {
                                        displayLink(uid, task1, bytes);
                                    }).addOnFailureListener(e1 -> controllerListener.showToast("Network Error")));
                        }
                    });
                }
            }
        });

    }

    // create LinkListSingleLink for link and display
    public void displayLink(String linkUid, Task<DocumentSnapshot> task, byte[] bytes) {
        HashMap<String, Object> linkInfoData = (HashMap<String, Object>) task.getResult().getData();
        String link_first_name = (String) linkInfoData.get(Db.Keys.FIRST_NAME);
        String link_last_name = (String) linkInfoData.get(Db.Keys.LAST_NAME);
        String link_major = (String) linkInfoData.get(Db.Keys.MAJOR);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        LinkListSingleLink newLink = new LinkListSingleLink(link_first_name, link_last_name, linkUid,link_major,bitmap);
        controllerListener.addNewLink(newLink);
        controllerListener.displayLinks(controllerListener.getLinks());

    }
}
