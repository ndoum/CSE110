package com.example.rum8.database;

import android.net.Uri;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class Db {

    private static final String PROFILE_PIC_PATH = "profile_pictures/";
    private static final String DEFAULT_PROFILE_PIC_PATH = "profile_picture_default/default_profile_pic.png";
    private static final long ONE_MEGABYTE = 1024 * 1024;

    static class InitialValues {

        private static final String EMPTY_STRING = "";
        private static final Integer ZERO = 0;
        private static final Map<String, Object> EMPTY_MAP = new HashMap<>();

        static final Map<String, Object> USER = new HashMap<String, Object>() {{
            put("academic_year", 1);
            put("age", 18);
            put("budget", ZERO);
            put("email", EMPTY_STRING);
            put("first_name", EMPTY_STRING);
            put("gender", "Female");
            put("last_name", EMPTY_STRING);
            put("major", "Computer Science");
            put("mobile_phone", EMPTY_STRING);
            put("ucsd_college", "Muir");
            put("uid", EMPTY_STRING);

            put("potential", EMPTY_MAP);
            put("liked", EMPTY_MAP);
            put("disliked", EMPTY_MAP);
            put("matched", EMPTY_MAP);

            put("alcohol_value", ZERO);
            put("allow_pets_value", ZERO);
            put("clean_value", ZERO);
            put("overnight_guests_value", ZERO);
            put("party_value", ZERO);
            put("reserved_value", ZERO);
            put("smoke_value", ZERO);
            put("stay_up_late_on_weekdays_value", ZERO);

            put("roommate_alcohol_value", ZERO);
            put("roommate_allow_pets_value", ZERO);
            put("roommate_clean_value", ZERO);
            put("roommate_overnight_guests_value", ZERO);
            put("roommate_party_value", ZERO);
            put("roommate_prefer_same_gender_roommate_value", 0);
            put("roommate_reserved_value", ZERO);
            put("roommate_smoke_value", ZERO);
            put("roommate_stay_up_late_on_weekdays_value", ZERO);
        }};

    }

    private static String USERS_COLLECTION_NAME = "users";

    public static Task<Void> createUserAndPreferences(final FirebaseFirestore firestore,
                                                      final @Nonnull FirebaseUser user,
                                                      final Map<String, Object> userHash) {
        final String userId = user.getUid();

        // Create user document and get a reference to it
        final DocumentReference userRef = firestore.collection(USERS_COLLECTION_NAME).document(userId);

        final WriteBatch batch = firestore.batch();

        // Construct a new user hash from passed values and default values
        // Passed values from userHash overwrite existing default values
        final Map<String, Object> completeUserHash = InitialValues.USER;
        completeUserHash.putAll(userHash);

        // Initialize user document's data
        batch.set(userRef, completeUserHash);

        // Submit all batched operations
        return batch.commit();
    }

    public static Task<DocumentSnapshot> populatePotential(final FirebaseFirestore firestore,
                                                           final @Nonnull FirebaseUser user) {
        final String userId = user.getUid();

        final CollectionReference usersCollection = firestore.collection(USERS_COLLECTION_NAME);

        // Create user document and get a reference to it
        final DocumentReference userRef = usersCollection.document(userId);

        final Map<String, Object> userHash = new HashMap<>();

        return userRef.get().addOnSuccessListener(userSnap -> {
            firestore.collection(USERS_COLLECTION_NAME)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        final Map<String, Object> potential = new HashMap<>();
                        for (final QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            final String otherUserId = doc.getId();

                            if (userId.equals(otherUserId)) {
                                continue;
                            }

                            final DocumentReference otherUser = usersCollection.document(otherUserId);

                            otherUser.get().addOnSuccessListener(otherUserSnap -> {
                                if (Filter.match(userSnap.getData(), otherUserSnap.getData())) {

                                    // Add other user to this user's potential
                                    potential.put(otherUserId, InitialValues.EMPTY_STRING);
                                    userHash.put("potential", potential);
                                    userRef.update(userHash);

                                    // Add this user to other user's potential
                                    final Map<String, Object> otherUserHash = doc.getData();
                                    final Map<String, Object> otherUserPotential = (Map<String, Object>) otherUserHash.get("potential");
                                    otherUserPotential.put(userId, InitialValues.EMPTY_STRING);
                                    otherUserHash.put("potential", otherUserPotential);
                                    otherUser.update(otherUserHash);
                                }

                            });
                        }
                    });
        });
    }

    public static Task<Void> updateUser(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(userHash);
    }

    public static UploadTask updateProfilePicture(final FirebaseStorage storage,
                                                  final @Nonnull FirebaseUser user,
                                                  final Uri filePath) {
        return storage.getReference()
                .child(PROFILE_PIC_PATH + user.getUid())
                .putFile(filePath);
    }

    public static Task<byte[]> fetchUserProfilePicture(final FirebaseStorage storage,
                                                       final @Nonnull FirebaseUser user) {
        return storage.getReference().child(PROFILE_PIC_PATH + user.getUid()).getBytes(ONE_MEGABYTE);
    }

    public static Task<byte[]> fetchDefaultUserProfilePicture(final FirebaseStorage storage) {
        return storage.getReference().child(DEFAULT_PROFILE_PIC_PATH).getBytes(ONE_MEGABYTE);
    }

    public static Task<byte[]> fetchLinkProfilePicture (final FirebaseStorage storage,
                                                        final String linkUid){
        return storage.getReference().child(PROFILE_PIC_PATH + linkUid).getBytes(ONE_MEGABYTE);
    }

}
