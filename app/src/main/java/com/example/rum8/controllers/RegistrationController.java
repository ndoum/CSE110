package com.example.rum8.controllers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.rum8.R;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.RegistrationControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegistrationController {

    private RegistrationControllerListener controllerListener;
    private Context context;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RegistrationController(final RegistrationControllerListener controllerListener, final Context context) {
        this.controllerListener = controllerListener;
        this.context = context;
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    private static boolean isValidEmail(final String email) {
        if (email == null) {
            return false;
        }

        final int minimumEmailLength = 10;
        return email.length() >= minimumEmailLength && email.endsWith("@ucsd.edu");
    }

    // check the passwords match
    private static boolean passWordMatch(final String password, final String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    private static boolean isValidPassword(final String password) {
        final int minimumPasswordLength = 6;
        return password != null && password.length() >= minimumPasswordLength;
    }

    public void onSubmit(final String email, final String password, final String passwordConfirm) {
        if (!isValidEmail(email)) {
            final String message = "Please use your UCSD email (i.e. abc@ucsd.edu)";
            controllerListener.showToast(message);
        } else if (!isValidPassword(password)) {
            final String message = "Your password need to be more than 6 characters";
            controllerListener.showToast(message);
        } else if (!passWordMatch(password, passwordConfirm)) {
            final String message = "Passwords didn't match";
            controllerListener.showToast(message);
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                    ((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            sendVerificationEmail(email);
                            controllerListener.goToLogin();
                            // Create a new user with email when registration is complete

                            final Map<String, Object> userInfo = new HashMap<String, Object>() {{
                                put(Db.Keys.EMAIL, email);
                            }};

                            final FirebaseUser user = auth.getCurrentUser();

                            final Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.images);
                            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            final byte[] data = baos.toByteArray();

                            Db.uploadDefaultPicture(storage, user, data);

                            Db.createUser(db, user, userInfo)
                                    .addOnSuccessListener(aVoid -> Log.d("Success", "createUserWithEmail:success"))
                                    .addOnFailureListener(e -> Log.d("Error", "createUserWithEmail:failure", e));
                        } else {
                            final String message;
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                message = "An account with this email already exists";
                            } else {
                                message = "Authentication failed";
                            }
                            controllerListener.showToast(message);
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
                        controllerListener.showToast(message);
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.getException());
                        message = "Failed to send verification email to";
                        controllerListener.showToast(message);
                    }
                });
    }

    public void onGoBackToLoginButtonClicked() {
        controllerListener.goToLogin();
    }

}
