package com.example.rum8.controllers;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.RandomLinkControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class RandomLinkController {
    private RandomLinkControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private FirebaseAuth auth;

    public RandomLinkController(final RandomLinkControllerListener controllerListener){
        this.controllerListener = controllerListener;
        this.db = FirebaseFirestore.getInstance();
        this.storage = FirebaseStorage.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    //randomly choose a link from list
    public void getRandomLink() {
        Db.fetchUserInfo(db, auth.getCurrentUser()).addOnCompleteListener((Task<DocumentSnapshot> task) -> {
            if (task.isSuccessful()) {
                // fetch current user's matched links
                HashMap<String, Object> linkListUidMap = (HashMap<String, Object>)task.getResult().get(Db.Keys.MATCHED);
                int mapSize = linkListUidMap.size();
                if(mapSize>0){
                    Random rand = new Random();
                    int randomNum = rand.nextInt(mapSize-1);
                    HashMap.Entry<String, Object> randomEntry;
                    Iterator<HashMap.Entry<String, Object>> mapItr = linkListUidMap.entrySet().iterator();
                    randomEntry = mapItr.next();
                    randomNum--;
                    while(mapItr.hasNext() && randomNum >= 0){
                        randomEntry = mapItr.next();
                        randomNum--;
                    }
                    String randomUid = randomEntry.getKey();
                    controllerListener.setRandomUid(randomUid);
                }

            }
            });
    }
}
