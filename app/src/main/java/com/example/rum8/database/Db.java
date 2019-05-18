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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

public class Db {

    private static final String PROFILE_PIC_PATH = "profile_pictures/";
    private static final String DEFAULT_PROFILE_PIC_PATH= "profile_picture_default/default_profile_pic.png";
    private static final long ONE_MEGABYTE = 1024 * 1024;

    static class InitialValues {

        private static final String EMPTY_STRING = "";
        private static final Integer ZERO = 0;
        private static final Set<String> EMPTY_SET = new HashSet<>();
        private static final int NUM_FILTERS = 11;
        private static final String MATCH_GROUP_ID = new String(new char[NUM_FILTERS]).replace("\0", ",0").substring(1);

        static final Map<String, Object> USER = new HashMap<String, Object>() {{
            put("academic_year", 1);
            put("age", 18);
            put("email", EMPTY_STRING);
            put("first_name", EMPTY_STRING);
            put("gender", "Female");
            put("last_name", EMPTY_STRING);
            put("major", "Computer Science");
            put("mobile_phone", EMPTY_STRING);
            put("potential", EMPTY_SET);
            put("matched", EMPTY_SET);
            put("liked", EMPTY_SET);
            put("disliked", EMPTY_SET);
            put("preference_match_group_id", MATCH_GROUP_ID);
            put("self_match_group_id", MATCH_GROUP_ID);
            put("ucsd_college", "Muir");

            put("alcohol_value", ZERO);
            put("allow_pets_value", ZERO);
            put("budget", ZERO);
            put("clean_value", ZERO);
            put("housing_type_value", ZERO);
            put("overnight_guests_value", ZERO);
            put("party_value", ZERO);
            put("reserved_value", ZERO);
            put("smoke_value", ZERO);
            put("stay_up_late_on_weekends_value", ZERO);

            put("roommate_alcohol_value", ZERO);
            put("roommate_allow_pets_value", ZERO);
            put("roommate_budget", ZERO);
            put("roommate_clean_value", ZERO);
            put("roommate_housing_type_value", ZERO);
            put("roommate_overnight_guests_value", ZERO);
            put("roommate_party_value", ZERO);
            put("roommate_reserved_value", ZERO);
            put("roommate_smoke_value", ZERO);
            put("roommate_stay_up_late_on_weekends_value", ZERO);
        }};

        static final Map<String, Object> PERSONAL_PREFERENCES = new HashMap<String, Object>() {{
            put("alcohol_value", ZERO);
            put("allow_pets_value", ZERO);
            put("budget", ZERO);
            put("clean_value", ZERO);
            put("housing_type_value", ZERO);
            put("overnight_guests_value", ZERO);
            put("party_value", ZERO);
            put("reserved_value", ZERO);
            put("smoke_value", ZERO);
            put("stay_up_late_on_weekends_value", ZERO);
        }};

        static final Map<String, Object> ROOMMATE_PREFERENCES = new HashMap<String, Object>() {{
            putAll(PERSONAL_PREFERENCES);
            put("prefer_same_gender_roommate_value", true);
        }};

    }

    private static String USERS_COLLECTION_NAME = "users";
    private static String PERSONAL_PREFERENCES_COLLECTION_NAME = "personal_preferences";
    private static String ROOMMATE_PREFERENCES_COLLECTION_NAME = "roommate_preferences";
    private static String MATCH_GROUPS_COLLECTION_NAME = "match_groups";
    static Task<Void> returnVal;

    public static Task<Void> createUserAndPreferences(final FirebaseFirestore firestore,
                                                      final @Nonnull FirebaseUser user,
                                                      final Map<String, Object> userHash) {

        final String userId = user.getUid();

        final CollectionReference usersCollection = firestore.collection(USERS_COLLECTION_NAME);

        // Create user document and get a reference to it
        final DocumentReference userRef = usersCollection.document(userId);

        final WriteBatch batch = firestore.batch();

//        Task<Void> returnVal;

        userRef.get().addOnSuccessListener(userSnap -> {

            // Create personal preferences document and get a reference to it
            // Created personal preferences document has the same ID as user document
            final DocumentReference personalPreferencesRef = firestore.collection(PERSONAL_PREFERENCES_COLLECTION_NAME).document(userId);

            // Create roommate preferences document and get a reference to it
            // Created roommate preferences document has the same ID as user document
            final DocumentReference roommatePreferencesRef = firestore.collection(ROOMMATE_PREFERENCES_COLLECTION_NAME).document(userId);

            final Map<String, Object> personalPreferencesHash = InitialValues.PERSONAL_PREFERENCES;
            final Map<String, Object> roommatePreferencesHash = InitialValues.ROOMMATE_PREFERENCES;

            // Populate potential
            firestore.collection(USERS_COLLECTION_NAME)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        final Set<String> potential = new HashSet<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            final String otherUserId = doc.getId();
                            final DocumentReference otherUser = usersCollection.document(otherUserId);

                            otherUser.get().addOnSuccessListener(otherUserSnap -> {
                                if (filter(userSnap, otherUserSnap)) {
                                    potential.add(otherUserId);

                                    final Set<String> otherUserPotentialSet = ((Set<String>) doc.getData().get("potential"));
                                    otherUserPotentialSet.add(userId);

                                    final Map<String, Object> otherUserPotentialMap = new HashMap<String, Object>() {{
                                        put("potential", otherUserPotentialSet);
                                    }};

                                    batch.update(otherUser, otherUserPotentialMap);
                                }
                            });
                        }

                        userHash.put("potential", potential);
                    });

            // Construct a new user hash from passed values and default values
            // Passed values from userHash overwrite existing default values
            final Map<String, Object> completeUserHash = InitialValues.USER;
            completeUserHash.putAll(userHash);

            // Initialize user document's data
            batch.set(userRef, completeUserHash);
            // Initialize personal preferences' data
            batch.set(personalPreferencesRef, personalPreferencesHash);
            // Initialize roommate preferences' data
            batch.set(roommatePreferencesRef, roommatePreferencesHash);

            returnVal = batch.commit();
        });

        // Submit all batched operations
        return returnVal;
    }

    private static boolean filter(final DocumentSnapshot u1, final DocumentSnapshot u2) {
        final Map<String, Object> u1Data = u1.getData();
        final Map<String, Object> u2Data = u2.getData();

        if (genderPasses(u1Data, u2Data)) {
            return true;
        }

        return false;
    }

    private static boolean genderPasses(final Map<String, Object> u1Data, final Map<String, Object> u2Data) {
        final String u1Gender = (String) u1Data.get("gender");
        final String u2Gender = (String) u2Data.get("gender");

        final int u1GenderPref = (int) u1Data.get("prefer_same_gender_roommate_value");
        final int u2GenderPref = (int) u2Data.get("prefer_same_gender_roommate_value");

        return u1Gender.equals(u2Gender) || (u1GenderPref == 0 && u2GenderPref == 0);
    }

    public static Task<Void> updateUser(final FirebaseFirestore firestore,
                                         final @Nonnull FirebaseUser user,
                                         final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(userHash);
    }

    public static Task<Void> updateSelfMatchIds(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> selfMatchUserIds) {
        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(selfMatchUserIds);
    }

    public static Task<Void> updateRoommateMatchIds(final FirebaseFirestore firestore,
                                                final @Nonnull FirebaseUser user,
                                                final Map<String, Object> preference_match_group_id) {
        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(preference_match_group_id);
    }



    public static Task<Void> updatePersonalPreferences(final FirebaseFirestore firestore,
                                                       final @Nonnull FirebaseUser user,
                                                       final Map<String, Object> personalPreferencesHash) {

        return firestore.collection(PERSONAL_PREFERENCES_COLLECTION_NAME)
                .document(user.getUid())
                .update(personalPreferencesHash);
    }

    public static Task<Void> updateRoommatePreferences(final FirebaseFirestore firestore,
                                                       final @Nonnull FirebaseUser user,
                                                       final Map<String, Object> roommatePreferencesHash) {

        return firestore.collection(ROOMMATE_PREFERENCES_COLLECTION_NAME)
                .document(user.getUid())
                .update(roommatePreferencesHash);
    }

    public static UploadTask updateProfilePicture(final FirebaseStorage storage,
                                                  final @Nonnull FirebaseUser user,
                                                  final Uri filePath){
        return storage.getReference()
                .child(PROFILE_PIC_PATH + user.getUid())
                .putFile(filePath);
    }

    public static Task<DocumentSnapshot> fetchGeneralInfo(final FirebaseFirestore firestore,
                                                          final @Nonnull FirebaseUser user){
        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid()).get();
    }

    public static Task<DocumentSnapshot> fetchPersonalPreferences(final FirebaseFirestore firestore,
                                                                  final @Nonnull FirebaseUser user){
        return firestore.collection(PERSONAL_PREFERENCES_COLLECTION_NAME)
                .document(user.getUid()).get();
    }

    public static Task<byte[]> fetchUserProfilePicture (final FirebaseStorage storage,
                                                        final @Nonnull FirebaseUser user){
        return storage.getReference().child(PROFILE_PIC_PATH + user.getUid()).getBytes(ONE_MEGABYTE);
    }

    public static Task<byte[]> fetchDefaultUserProfilePicture (final FirebaseStorage storage){
        return storage.getReference().child(DEFAULT_PROFILE_PIC_PATH).getBytes(ONE_MEGABYTE);
    }
}
