package com.example.rum8.database;

import android.net.Uri;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class Db {

    private static final String USERS_COLLECTION_NAME = "users";
    private static final String PROFILE_PIC_PATH = "profile_pictures/";
    private static final String DEFAULT_PROFILE_PIC_PATH = "profile_picture_default/default_profile_pic.png";
    private static final long HUNDRED_MEGABYTE = 10240 * 10240;

    public static Task<Void> createUser(final FirebaseFirestore firestore,
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

    public static Task<Void> updateUser(final FirebaseFirestore firestore,
                                        final @Nonnull FirebaseUser user,
                                        final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid())
                .update(userHash);
    }

    public static Task<Void> updateOtherUserById(final FirebaseFirestore firestore,
                                                 final String userId,
                                                 final Map<String, Object> userHash) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(userId)
                .update(userHash);
    }

    public static UploadTask updateProfilePicture(final FirebaseStorage storage,
                                                  final @Nonnull FirebaseUser user,
                                                  final Uri filePath) {

        return storage.getReference()
                .child(PROFILE_PIC_PATH + user.getUid())
                .putFile(filePath);
    }

    public static UploadTask uploadDefaultPicture(final FirebaseStorage storage,
                                                  final @Nonnull FirebaseUser user,
                                                  final byte[] data) {
        return storage.getReference()
                .child(PROFILE_PIC_PATH + user.getUid())
                .putBytes(data);
    }

    public static Task<byte[]> fetchUserProfilePicture(final FirebaseStorage storage,
                                                       final @Nonnull FirebaseUser user) {
        return storage.getReference().child(PROFILE_PIC_PATH + user.getUid()).getBytes(HUNDRED_MEGABYTE);
    }

    public static Task<byte[]> fetchUserProfilePictureById(final FirebaseStorage storage,
                                                           final String userId) {
        return storage.getReference().child(PROFILE_PIC_PATH + userId).getBytes(HUNDRED_MEGABYTE);
    }


    public static Task<byte[]> fetchDefaultUserProfilePicture(final FirebaseStorage storage) {
        return storage.getReference().child(DEFAULT_PROFILE_PIC_PATH).getBytes(HUNDRED_MEGABYTE);
    }


    public static Task<DocumentSnapshot> fetchUserInfo(final FirebaseFirestore firestore,
                                                       final @Nonnull FirebaseUser user) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(user.getUid()).get();
    }

    public static Task<DocumentSnapshot> fetchUserInfoById(final FirebaseFirestore firestore,
                                                           final String userId) {

        return firestore.collection(USERS_COLLECTION_NAME)
                .document(userId).get();
    }

    public static class Keys {

        public static final String ACADEMIC_YEAR = "academic_year";
        public static final String AGE = "age";
        public static final String BUDGET_MIN = "budget_min";
        public static final String BUDGET_MAX = "budget_max";
        public static final String COLLEGE = "college";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "first_name";
        public static final String GENDER = "gender";
        public static final String INSTANCE_ID_TOKEN = "instance_id_token";
        public static final String LAST_NAME = "last_name";
        public static final String MAJOR = "major";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String FACEBOOK = "facebook";
        public static final String SNAPCHAT = "snapchat";

        public static final String ABOUT_ME = "about_me";
        public static final String ROOM_TYPE = "room_type";
        public static final String HOBBIES = "hobbies";
        public static final String INTERESTS = "interests";
        public static final String LIVING_ACCOMMODATIONS = "living_accommodations";
        public static final String OTHER_THINGS_YOU_SHOULD_KNOW = "other_things_you_should_know";

        public static final String POTENTIAL = "potential";
        public static final String LIKED = "liked";
        public static final String DISLIKED = "disliked";
        public static final String MATCHED = "matched";

        public static final String ALCOHOL_VALUE = "alcohol_value";
        public static final String ALLOW_PETS_VALUE = "allow_pets_value";
        public static final String CLEAN_VALUE = "clean_value";
        public static final String OVERNIGHT_GUESTS_VALUE = "overnight_guests_value";
        public static final String PARTY_VALUE = "party_value";
        public static final String RESERVED_VALUE = "reserved_value";
        public static final String SMOKE_VALUE = "smoke_value";
        public static final String STAY_UP_LATE_ON_WEEKDAYS_VALUE = "stay_up_late_on_weekdays_value";

        public static final String ROOMMATE_ALCOHOL_VALUE = String.format("roommate_%s", ALCOHOL_VALUE);
        public static final String ROOMMATE_ALLOW_PETS_VALUE = String.format("roommate_%s", ALLOW_PETS_VALUE);
        public static final String ROOMMATE_CLEAN_VALUE = String.format("roommate_%s", CLEAN_VALUE);
        public static final String ROOMMATE_OVERNIGHT_GUESTS_VALUE = String.format("roommate_%s", OVERNIGHT_GUESTS_VALUE);
        public static final String ROOMMATE_PARTY_VALUE = String.format("roommate_%s", PARTY_VALUE);
        public static final String ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE = "roommate_prefer_same_gender_roommate_value";
        public static final String ROOMMATE_RESERVED_VALUE = String.format("roommate_%s", RESERVED_VALUE);
        public static final String ROOMMATE_SMOKE_VALUE = String.format("roommate_%s", SMOKE_VALUE);
        public static final String ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE = String.format("roommate_%s", STAY_UP_LATE_ON_WEEKDAYS_VALUE);
    }

    private static class InitialValues {

        private static final String EMPTY_STRING = "";
        private static final Integer ZERO = 0;
        private static final Integer BUDGET_MAX_VAL = 3000;
        private static final Map<String, Object> EMPTY_MAP = new HashMap<>();

        private static final Map<String, Object> USER = new HashMap<String, Object>() {{
            put(Keys.ACADEMIC_YEAR, "First");
            put(Keys.AGE, 18);
            put(Keys.BUDGET_MIN, ZERO);
            put(Keys.BUDGET_MAX, BUDGET_MAX_VAL);
            put(Keys.COLLEGE, "Muir");
            put(Keys.EMAIL, EMPTY_STRING);
            put(Keys.FIRST_NAME, EMPTY_STRING);
            put(Keys.GENDER, "Female");
            put(Keys.INSTANCE_ID_TOKEN, EMPTY_STRING);  //Empty string will be updated every time you log in
            put(Keys.LAST_NAME, EMPTY_STRING);
            put(Keys.MAJOR, "Computer Science");
            put(Keys.PHONE_NUMBER, EMPTY_STRING);
            put(Keys.FACEBOOK, EMPTY_STRING);
            put(Keys.SNAPCHAT, EMPTY_STRING);
            put(Keys.ROOM_TYPE, EMPTY_STRING);

            put(Keys.ABOUT_ME, EMPTY_STRING);
            put(Keys.HOBBIES, EMPTY_STRING);
            put(Keys.INTERESTS, EMPTY_STRING);
            put(Keys.LIVING_ACCOMMODATIONS, EMPTY_STRING);
            put(Keys.OTHER_THINGS_YOU_SHOULD_KNOW, EMPTY_STRING);

            put(Keys.POTENTIAL, EMPTY_MAP);
            put(Keys.LIKED, EMPTY_MAP);
            put(Keys.DISLIKED, EMPTY_MAP);
            put(Keys.MATCHED, EMPTY_MAP);

            put(Keys.ALCOHOL_VALUE, ZERO);
            put(Keys.ALLOW_PETS_VALUE, ZERO);
            put(Keys.CLEAN_VALUE, ZERO);
            put(Keys.OVERNIGHT_GUESTS_VALUE, ZERO);
            put(Keys.PARTY_VALUE, ZERO);
            put(Keys.RESERVED_VALUE, ZERO);
            put(Keys.SMOKE_VALUE, ZERO);
            put(Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE, ZERO);

            put(Keys.ROOMMATE_ALCOHOL_VALUE, ZERO);
            put(Keys.ROOMMATE_ALLOW_PETS_VALUE, ZERO);
            put(Keys.ROOMMATE_CLEAN_VALUE, ZERO);
            put(Keys.ROOMMATE_OVERNIGHT_GUESTS_VALUE, ZERO);
            put(Keys.ROOMMATE_PARTY_VALUE, ZERO);
            put(Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE, 1);
            put(Keys.ROOMMATE_RESERVED_VALUE, ZERO);
            put(Keys.ROOMMATE_SMOKE_VALUE, ZERO);
            put(Keys.ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE, ZERO);
        }};

    }

}
