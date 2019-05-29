package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.firebase.auth.FirebaseAuth;
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

    //Fetch links' info and images and display
    public void prepareLinks() {
        linkListUidMap = new HashMap<>();

        //fetch current user's documentation
        Db.fetchUserInfo(db, auth.getCurrentUser()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //fetch current user's matched links
                linkListUidMap = (HashMap<String, Object>) task.getResult().get(Db.Keys.MATCHED);
                for (String uid : linkListUidMap.keySet()) {
                    //fetch link's profile image
                    Db.fetchUserInfoById(db, uid).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Db.fetchUserProfilePictureById(storage, uid)
                                    .addOnSuccessListener(bytes -> {
                                        //create LinkListSingleLink object for link and display
                                        HashMap<String, Object> uidMap = (HashMap<String, Object>) task1.getResult().getData();
                                        String first_name = (String) uidMap.get(Db.Keys.FIRST_NAME);
                                        String last_name = (String) uidMap.get(Db.Keys.LAST_NAME);

                                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                                        LinkListSingleLink newLink = new LinkListSingleLink(first_name, last_name, uid, bmp);
                                        controllerListener.addNewLink(newLink);
                                        controllerListener.displayLinks(controllerListener.getLinks());
                                    })
                                    .addOnFailureListener(e -> Db.fetchDefaultUserProfilePicture(storage)
                                            .addOnSuccessListener(bytes -> {
                                                //fetch default user profile image and create LinkListSingleLink and display
                                                HashMap<String, Object> uidMap = (HashMap<String, Object>) task1.getResult().getData();
                                                String first_name = (String) uidMap.get(Db.Keys.FIRST_NAME);
                                                String last_name = (String) uidMap.get(Db.Keys.LAST_NAME);

                                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                LinkListSingleLink newLink = new LinkListSingleLink(first_name, last_name, uid, bmp);
                                                controllerListener.addNewLink(newLink);
                                                controllerListener.displayLinks(controllerListener.getLinks());
                                            })
                                            .addOnFailureListener(e1 -> controllerListener.showToast("Network Error")));
                        }
                    });
                }
            }
        });
    }
}
