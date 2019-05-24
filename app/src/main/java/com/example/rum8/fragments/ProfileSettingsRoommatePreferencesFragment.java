package com.example.rum8.fragments;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.LoginActivity;
import com.example.rum8.activities.MainActivity;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

import java.util.Map;

/**
 * Class that implements profile settings rommmate preferences question sets in
 * profile settings activity.
 */
public class ProfileSettingsRoommatePreferencesFragment extends Fragment implements ProfileSettingsControllerListener {
    private ProfileSettingsController controller;
    private RadioGroup radioGroupRoommateQuestionOne;
    private RadioGroup radioGroupRoommateQuestionTwo;
    private RadioGroup radioGroupRoommateQuestionThree;
    private RadioGroup radioGroupRoommateQuestionFour;
    private RadioGroup radioGroupRoommateQuestionFive;
    private RadioGroup radioGroupRoommateQuestionSix;
    private RadioGroup radioGroupRoommateQuestionSeven;
    private RadioGroup radioGroupRoommateQuestionEight;
    private RadioGroup radioGroupRoommateQuestionNine;
    private Button saveRoommateButton;

    private int indicatorYes = 1;
    private int indicatorSometimes = 0;
    private int indicatorNo = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_roommate_preferences, container,
                false);

        controller = new ProfileSettingsController(this);

        // Initialize each corresponding radio group in roommate preferences
        // questionnaire
        radioGroupRoommateQuestionOne = rootView.findViewById(R.id.roommate_preferences_gender_preference_radio_group);
        radioGroupRoommateQuestionTwo = rootView
                .findViewById(R.id.roommate_preferences_cleanness_preference_radio_group);
        radioGroupRoommateQuestionThree = rootView
                .findViewById(R.id.roommate_preferences_reserved_preference_radio_group);
        radioGroupRoommateQuestionFour = rootView
                .findViewById(R.id.roommate_preferences_comfortable_party_preference_radio_group);
        radioGroupRoommateQuestionFive = rootView
                .findViewById(R.id.roommate_preferences_alcohol_preference_radio_group);
        radioGroupRoommateQuestionSix = rootView.findViewById(R.id.roommate_preferences_smoking_preference_radio_group);
        radioGroupRoommateQuestionSeven = rootView.findViewById(R.id.roommate_preferences_sleep_preference_radio_group);
        radioGroupRoommateQuestionEight = rootView
                .findViewById(R.id.roommate_preferences_guests_preference_radio_group);
        radioGroupRoommateQuestionNine = rootView.findViewById(R.id.roommate_preferences_pets_preference_radio_group);

        controller.loadUserInfo().addOnSuccessListener(documentSnapshot -> {
            Map<String, Object> data = documentSnapshot.getData();
            long gender = (long) data.get(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE);
            long clean = (long) data.get(Db.Keys.ROOMMATE_CLEAN_VALUE);
            long reserve = (long) data.get(Db.Keys.ROOMMATE_RESERVED_VALUE);
            long party = (long) data.get(Db.Keys.ROOMMATE_PARTY_VALUE);
            long alcohol = (long) data.get(Db.Keys.ROOMMATE_ALCOHOL_VALUE);
            long smoke = (long) data.get(Db.Keys.ROOMMATE_SMOKE_VALUE);
            long stayLate = (long) data.get(Db.Keys.ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE);
            long guests = (long) data.get(Db.Keys.ROOMMATE_OVERNIGHT_GUESTS_VALUE);
            long pet = (long) data.get(Db.Keys.ROOMMATE_ALLOW_PETS_VALUE);

            if (gender == 1) {
                radioGroupRoommateQuestionOne.check(R.id.roommate_preferences_gender_preference_yes);
            } else if (gender == 0) {
                radioGroupRoommateQuestionOne.check(R.id.roommate_preferences_gender_preference_no_pref);
            } else {
                radioGroupRoommateQuestionOne.check(R.id.personal_preferences_cleanliness_preference_yes);
            }

            if (clean == 1) {
                radioGroupRoommateQuestionTwo.check(R.id.roommate_preferences_cleanness_preference_yes);
            } else if (clean == 0) {
                radioGroupRoommateQuestionTwo.check(R.id.roommate_preferences_cleanness_preference_no_pref);
            } else {
                radioGroupRoommateQuestionTwo.check(R.id.roommate_preferences_cleanness_preference_no);
            }

            if (reserve == 1) {
                radioGroupRoommateQuestionThree.check(R.id.roommate_preferences_reserved_preference_yes);
            } else if (reserve == 0) {
                radioGroupRoommateQuestionThree.check(R.id.roommate_preferences_reserved_preference_no_pref);
            } else {
                radioGroupRoommateQuestionThree.check(R.id.roommate_preferences_reserved_preference_no_);
            }

            if (party == 1) {
                radioGroupRoommateQuestionFour.check(R.id.roommate_preferences_party_preference_yes);
            } else if (party == 0) {
                radioGroupRoommateQuestionFour.check(R.id.roommate_preferences_party_preference_no_pref);
            } else {
                radioGroupRoommateQuestionFour.check(R.id.roommate_preferences_party_preference_no);
            }

            if (alcohol == 1) {
                radioGroupRoommateQuestionFive.check(R.id.roommate_preferences_alcohol_preference_yes);
            } else if (alcohol == 0) {
                radioGroupRoommateQuestionFive.check(R.id.roommate_preferences_alcohol_preference_no_pref);
            } else {
                radioGroupRoommateQuestionFive.check(R.id.roommate_preferences_alcohol_preference_no);
            }

            if (smoke == 1) {
                radioGroupRoommateQuestionSix.check(R.id.roommate_preferences_smoking_preference_yes);
            } else if (smoke == 0) {
                radioGroupRoommateQuestionSix.check(R.id.roommate_preferences_smoking_preference_no_pref);
            } else {
                radioGroupRoommateQuestionSix.check(R.id.roommate_preferences_smoking_preference_no);
            }

            if (stayLate == 1) {
                radioGroupRoommateQuestionSeven.check(R.id.roommate_preferences_sleep_preference_yes);
            } else if (alcohol == 0) {
                radioGroupRoommateQuestionSeven.check(R.id.roommate_preferences_sleep_preference_no_pref);
            } else {
                radioGroupRoommateQuestionSeven.check(R.id.roommate_preferences_sleep_preference_no);
            }

            if (guests == 1) {
                radioGroupRoommateQuestionEight.check(R.id.roommate_preferences_guests_preference_yes);
            } else if (alcohol == 0) {
                radioGroupRoommateQuestionEight.check(R.id.roommate_preferences_guests_preference_no_pref);
            } else {
                radioGroupRoommateQuestionEight.check(R.id.roommate_preferences_guests_preference_no);
            }

            if (pet == 1) {
                radioGroupRoommateQuestionNine.check(R.id.roommate_preferences_pets_preference_yes);
            } else if (alcohol == 0) {
                radioGroupRoommateQuestionNine.check(R.id.roommate_preferences_pets_preference_no_pref);
            } else {
                radioGroupRoommateQuestionNine.check(R.id.roommate_preferences_pets_preference_no);
            }
        }).addOnFailureListener(exception -> {
            final String message = "Network error";
            showToast(message);
        });

        radioGroupRoommateQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_PREFER_SAME_GENDER_ROOMMATE_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_CLEAN_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_CLEAN_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_CLEAN_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_RESERVED_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_RESERVED_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_RESERVED_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_PARTY_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_PARTY_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_PARTY_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALCOHOL_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALCOHOL_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALCOHOL_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_SMOKE_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_SMOKE_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_SMOKE_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_OVERNIGHT_GUESTS_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_OVERNIGHT_GUESTS_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_OVERNIGHT_GUESTS_VALUE, indicatorNo);
            }
        });

        radioGroupRoommateQuestionNine.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALLOW_PETS_VALUE, indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALLOW_PETS_VALUE, indicatorSometimes);

            } else {
                controller.updateUserMap(Db.Keys.ROOMMATE_ALLOW_PETS_VALUE, indicatorNo);
            }

        });

        saveRoommateButton = rootView.findViewById(R.id.roommate_preferences_save_button);
        saveRoommateButton.setOnClickListener(v -> {
            controller.submitUserMap();
            showToast("Roommate Preferences Saved");
        });

        return rootView;
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUploadImageProgress() {
    }

    @Override
    public void hideUploadImageProgress() {
    }

    @Override
    public void updateUploadImagePercentage(double percengate) {
    }

    @Override
    public void chooseImage() {
    }

}