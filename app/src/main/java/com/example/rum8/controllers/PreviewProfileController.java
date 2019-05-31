package com.example.rum8.controllers;

import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class PreviewProfileController {
    private MainControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    public PreviewProfileController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }
}
