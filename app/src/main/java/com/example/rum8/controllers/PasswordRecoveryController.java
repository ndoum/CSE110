package com.example.rum8.controllers;

import android.util.Log;

import com.example.rum8.listeners.PasswordRecoveryControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class PasswordRecoveryController {

    private PasswordRecoveryControllerListener controllerListener;
    private FirebaseAuth auth;

    public PasswordRecoveryController(final PasswordRecoveryControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        auth = FirebaseAuth.getInstance();
    }

    /**
     * Send reset password email to users
     */
    public void onSubmit(final String email) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Success", "Reset Password Email sent");
                    final String message = "Password reset email sent";
                    controllerListener.showToast(message);
                    controllerListener.goToLogin();
                })
                .addOnFailureListener(e -> {
                    String message;
                    if (e instanceof FirebaseAuthInvalidUserException) {
                        message = "Account does not exist";
                    } else {
                        message = "Network error";
                    }
                    controllerListener.showToast(message);
                    Log.d("Error", "Reset Password Email: failure", e);
                });

    }
}
