package com.example.rum8.controllers;


import android.util.Log;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.LoginControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class LoginController {

    private LoginControllerListener controllerListener;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    public LoginController(final LoginControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    private static boolean isValidEmail(final String email) {
        final int minimumEmailLength = 10;
        return email != null && email.length() >= minimumEmailLength && email.endsWith("@ucsd.edu");
    }

    private static boolean isValidPassword(final String password) {
        final int minimumPasswordLength = 6;
        return password != null && password.length() >= minimumPasswordLength;
    }

    public void onSubmit(final String email, final String password) {
        if (!isValidEmail(email)) {
            final String message = "Please use your UCSD email (i.e. abc@ucsd.edu)";
            controllerListener.showToast(message);
        } else if (!isValidPassword(password)) {
            final String message = "Your password need to be more than 6 characters";
            controllerListener.showToast(message);
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        if (auth.getCurrentUser().isEmailVerified()) {
                            Log.d("Success", "signInWithEmail:success");
                            onLoginSuccessful();
                        } else {
                            final String message = "Please verify your email!";
                            controllerListener.showToast(message);
                        }
                    })
                    .addOnFailureListener(e -> {
                        final String message;
                        if (e instanceof FirebaseAuthInvalidUserException) {
                            message = "Account does not exist";
                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            message = "Incorrect password";
                        } else {
                            message = "Network error";
                        }
                        Log.d("Error", "signInWithEmail:failure", e);
                        controllerListener.showToast(message);
                    });
        }
    }

    public void onGoToRegistrationButtonClicked() {
        controllerListener.goToRegistration();
    }

    public void onGoToPasswordRecoverClicked() {
        controllerListener.goToPasswordRecovery();
    }

    private void onLoginSuccessful() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
                .addOnSuccessListener(documentSnapshot -> {
                    final Map<String, Object> data = documentSnapshot.getData();

                    // If name has not been entered, go to preferences
                    if (((String) data.get(Db.Keys.FIRST_NAME)).isEmpty()) {
                        controllerListener.goToProfileSettings();
                    } else {
                        controllerListener.goToMainPage();
                    }
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });

    }


}

