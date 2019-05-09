package com.example.rum8.controllers;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rum8.listeners.LoginControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginController {

    private LoginControllerListener controllerListener;
    private FirebaseAuth auth;

    public LoginController(final LoginControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
    }

    public void onSubmit(final String email, final String password) {
        if (!isValidEmail(email)) {
            final String message = "Please use your UCSD email (i.e. abc@ucsd.edu)";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else if (!isValidPassword(password)) {
            final String message = "Your password need to be more than 6 characters";
            controllerListener.showToast(message, Toast.LENGTH_SHORT);
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(final AuthResult authResult) {
                            onLoginSuccessful();
                            Log.d("Success", "signInWithEmail:success");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(final @NonNull Exception e) {
                            final String message;
                            if (e instanceof FirebaseAuthInvalidUserException) {
                                message = "Account does not exist";
                            } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Incorrect password";
                            } else {
                                message = "Network error";
                            }
                            controllerListener.showToast(message, Toast.LENGTH_SHORT);
                            Log.d("Error", "signInWithEmail:failure", e);
                        }
                    });
        }
    }

    private static boolean isValidEmail(final String email) {
        if (email == null) {
            return false;
        }

        final int minimumEmailLength = 10;
        return email.length() >= minimumEmailLength && email.endsWith("@ucsd.edu");
    }

    private static boolean isValidPassword(final String password) {
        final int minimumPasswordLength = 6;
        return password != null && password.length() >= minimumPasswordLength;
    }

    public void onGoToRegistrationButtonClicked() {
        controllerListener.goToRegistration();
    }

    public void onGoToPasswordRecoverClicked() {
        controllerListener.goToPasswordRecover();
    }

    private void onLoginSuccessful() {
        controllerListener.goToMainPage();
    }

}

