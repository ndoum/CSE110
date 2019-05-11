package com.example.rum8.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

public class Db {

    static class InitialValues {

        private static final String EMPTY_STRING = "";
        private static final Integer ZERO = 0;
        private static final List<Object> EMPTY_LIST = new ArrayList<>();
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
            put("mutual_matches", EMPTY_LIST);
            put("pending_matches", EMPTY_LIST);
            put("preference_match_group_id", MATCH_GROUP_ID);
            put("self_match_group_id", MATCH_GROUP_ID);
            put("ucsd_college", "Muir");
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

    public static Task<Void> createUserAndPreferences(final FirebaseFirestore firestore,
                                                      final @Nonnull FirebaseUser user,
                                                      final Map<String, Object> userHash) {

        final String userId = user.getUid();

        // Create user document and get a reference to it
        final DocumentReference userRef = firestore.collection(USERS_COLLECTION_NAME).document(userId);

        // Create personal preferences document and get a reference to it
        // Created personal preferences document has the same ID as user document
        final DocumentReference personalPreferencesRef = firestore.collection(PERSONAL_PREFERENCES_COLLECTION_NAME).document(userId);

        // Create roommate preferences document and get a reference to it
        // Created roommate preferences document has the same ID as user document
        final DocumentReference roommatePreferencesRef = firestore.collection(ROOMMATE_PREFERENCES_COLLECTION_NAME).document(userId);

        final Map<String, Object> personalPreferencesHash = InitialValues.PERSONAL_PREFERENCES;
        final Map<String, Object> roommatePreferencesHash = InitialValues.ROOMMATE_PREFERENCES;

        final WriteBatch batch = firestore.batch();

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

        // Submit all batched operations
        return batch.commit();
    }

    public static Task<Void> updateUser(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(userHash);
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

}
