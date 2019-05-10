package com.example.rum8.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import javax.annotation.Nonnull;

public class Db {

    private static String USERS_COLLECTION_NAME = "users";
    private static String PERSONAL_PREFERENCES_COLLECTION_NAME = "personal_preferences";
    private static String ROOMMATE_PREFERENCES_COLLECTION_NAME = "roommate_preferences";
    private static String MATCH_GROUPS_COLLECTION_NAME = "match_groups";

    public static Task<Void> createUser(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .set(userHash);
    }

    public static Task<Void> updateUser(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(userHash);
    }

}
