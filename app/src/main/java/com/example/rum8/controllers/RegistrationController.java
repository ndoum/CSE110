package com.example.rum8.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.RegistrationControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegistrationController {

    private RegistrationControllerListener controllerListener;
    private Context context;
    private FirebaseAuth auth;

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RegistrationController(final RegistrationControllerListener controllerListener, final Context context) {
        this.controllerListener = controllerListener;
        this.context = context;
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
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                    ((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            sendVerificationEmail(email);
                            controllerListener.onUserRegistered();
                            Log.d("Success", "createUserWithEmail:success");
                            // Create a new user with email when registration is complete

                            final Map<String, Object> userInfo = new HashMap<String, Object>() {{
                                put("email", email);
                            }};

                            Db.createUser(db, auth.getCurrentUser(), userInfo)
                                    .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                                    .addOnFailureListener(e -> Log.d(TAG, "Error adding document", e));
                        } else {
                            final String message;
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                message = "An account with this email already exists";
                            } else {
                                message = "Authentication failed";
                            }
                            controllerListener.showToast(message, Toast.LENGTH_SHORT);
                            Log.e("Error:", "createUserWithEmail:failure", task.getException());
                        }
                    });
        }
    }

    /*
     * Helper function
     */
    private void sendVerificationEmail(final String email) {
        auth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(task -> {
                    final String message;
                    if (task.isSuccessful()) {
                        message = "Verification email sent to " + email;
                        controllerListener.showToast(message, Toast.LENGTH_SHORT);
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.getException());
                        message = "Failed to send verification email to";
                        controllerListener.showToast(message, Toast.LENGTH_SHORT);
                    }
                });
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

    public void onGoBackToLoginButtonClicked() {
        controllerListener.goBackToLogin();
    }

}
