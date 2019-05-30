package com.example.rum8.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private MainControllerListener controllerListener;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    public MainController(final MainControllerListener controllerListener) {
        this.controllerListener = controllerListener;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public void onProfileSettingsButtonClicked() {
        controllerListener.goToProfileSettings();
    }

    public void onAdvancedSettingsButtonClicked() {
        controllerListener.goToAdvancedProfileSettings();
    }

    public void onGoToLinkListButtonClicked() {
        controllerListener.goToLinkList();
    }

    public void onLogOutButtonClicked() {
        FirebaseAuth.getInstance().signOut();
        controllerListener.goToLogin();
    }

    /**
     * use user's potential list to find other other show other user's info
     */
    public void loadUserInfo() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
                .addOnSuccessListener(documentSnapshot -> {
                    final Map<String, Object> data = documentSnapshot.getData();
                    final HashMap<String, Object> potential = (HashMap<String, Object>) data.get(Db.Keys.POTENTIAL);

                    // when potential is not empty, show the first user in potential
                    if (potential.keySet().size() > 0) {

                        // get other user's id
                        final String userId = (String) potential.keySet().toArray()[0];
                        Db.fetchUserInfoById(db, userId)
                                .addOnSuccessListener(documentSnapshotOther -> {

                                    // fetch other user's profile picture
                                    Db.fetchUserProfilePictureById(storage, userId)
                                            .addOnSuccessListener(bytes -> {
                                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                controllerListener.setUserProfileImage(bmp);
                                            })
                                            .addOnFailureListener(e -> {
                                                // fetch default if the user does not upload
                                                Db.fetchDefaultUserProfilePicture(storage).addOnSuccessListener(bytes -> {
                                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                    controllerListener.setUserProfileImage(bmp);
                                                });
                                                // show error message if both way fails
                                                int errorCode = ((StorageException) e).getErrorCode();
                                                if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                                                    final String message = "Network error";
                                                    controllerListener.showToast(message);
                                                }
                                            });

                                    // show other user's info
                                    final Map<String, Object> otherUserdata = documentSnapshotOther.getData();
                                    controllerListener.showCurrentUserInfo(otherUserdata);
                                })
                                .addOnFailureListener(e -> {
                                    final String message = "Network error";
                                    controllerListener.showToast(message);
                                });
                    } else {
                        controllerListener.setFragmentEmpty();
                        controllerListener.showToast("No potentials");
                    }
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
    }

    public void onLikeClicked() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
                .addOnSuccessListener(documentSnapshot -> {
                    final Map<String, Object> data = documentSnapshot.getData();
                    final HashMap<String, Object> potential = (HashMap<String, Object>) data.get(Db.Keys.POTENTIAL);

                    // when potential is not empty, show the first user in potential
                    if (potential.keySet().size() > 0) {
                        final String userId = (String) potential.keySet().toArray()[0];

                        // remove other user from potentials
                        potential.remove(userId);
                        data.put(Db.Keys.POTENTIAL, potential);

                        // add other user to liked
                        final HashMap<String, Object> liked = (HashMap<String, Object>) data.get(Db.Keys.LIKED);
                        liked.put(userId, "");
                        data.put(Db.Keys.LIKED, liked);

                        Db.updateUser(db, auth.getCurrentUser(), data);
                        Db.fetchUserInfoById(db, userId)
                                .addOnSuccessListener(documentSnapshotOther -> {
                                    final Map<String, Object> otherUserData = documentSnapshotOther.getData();
                                    final HashMap<String, Object> otherUserLiked = (HashMap<String, Object>) otherUserData.get(Db.Keys.LIKED);

                                    final String currentUserId = auth.getCurrentUser().getUid();

                                    if (otherUserLiked.containsKey(currentUserId)) {
                                        final HashMap<String, Object> otherUserMatched = (HashMap<String, Object>) otherUserData.get(Db.Keys.MATCHED);
                                        final HashMap<String, Object> userMatched = (HashMap<String, Object>) data.get(Db.Keys.MATCHED);
                                        otherUserMatched.put(currentUserId, "");
                                        userMatched.put(userId, "");
                                        Db.updateUser(db, auth.getCurrentUser(), data);
                                        Db.updateOtherUserById(db, userId, otherUserData);
                                    }

                                })
                                .addOnFailureListener(e -> {
                                    final String message = "Network error";
                                    controllerListener.showToast(message);
                                });
                        if (potential.keySet().size() > 0) {
                            controllerListener.setFragment();
                        } else {
                            controllerListener.setFragmentEmpty();
                            controllerListener.showToast("No more potential");
                        }
                    } else {
                        controllerListener.setFragmentEmpty();
                        controllerListener.showToast("No more potential");
                    }
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
    }

    public void onDisLikeClicked() {
        Db.fetchUserInfo(db, auth.getCurrentUser())
                .addOnSuccessListener(documentSnapshot -> {
                    final Map<String, Object> data = documentSnapshot.getData();
                    final HashMap<String, Object> potential = (HashMap<String, Object>) data.get(Db.Keys.POTENTIAL);

                    // when potential is not empty, show the first user in potential
                    if (potential.keySet().size() > 0) {
                        final String userId = (String) potential.keySet().toArray()[0];
                        // remove other user from potentials
                        potential.remove(userId);
                        data.put(Db.Keys.POTENTIAL, potential);
                        // add user to disliked
                        final HashMap<String, Object> dislikes = (HashMap<String, Object>) data.get(Db.Keys.DISLIKED);
                        dislikes.put(userId, "");
                        data.put(Db.Keys.DISLIKED, dislikes);
                        Db.updateUser(db, auth.getCurrentUser(), data);
                        if (potential.keySet().size() > 0) {
                            controllerListener.setFragment();
                        } else {
                            controllerListener.setFragmentEmpty();
                            controllerListener.showToast("No more potential");
                        }
                    } else {
                        controllerListener.setFragmentEmpty();
                        controllerListener.showToast("No more potential");
                    }
                })
                .addOnFailureListener(e -> {
                    final String message = "Network error";
                    controllerListener.showToast(message);
                });
    }

}
