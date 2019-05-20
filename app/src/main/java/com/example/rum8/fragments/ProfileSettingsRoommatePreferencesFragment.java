package com.example.rum8.fragments;

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
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Map;

/**
 * Class that implements profile settings rommmate preferences question sets in profile
 * settings activity.
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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_roommate_preferences, container, false);

        controller = new ProfileSettingsController(this);

        // Initialize each corresponding radio group in roommate preferences questionnaire
        radioGroupRoommateQuestionOne = rootView.findViewById(R.id.roommate_preferences_gender_preference_radio_group);
        radioGroupRoommateQuestionTwo = rootView.findViewById(R.id.roommate_preferences_cleanness_preference_radio_group);
        radioGroupRoommateQuestionThree = rootView.findViewById(R.id.roommate_preferences_reserved_preference_radio_group);
        radioGroupRoommateQuestionFour = rootView.findViewById(R.id.roommate_preferences_comfortable_party_preference_radio_group);
        radioGroupRoommateQuestionFive = rootView.findViewById(R.id.roommate_preferences_alcohol_preference_radio_group);
        radioGroupRoommateQuestionSix = rootView.findViewById(R.id.roommate_preferences_smoking_preference_radio_group);
        radioGroupRoommateQuestionSeven = rootView.findViewById(R.id.roommate_preferences_sleep_preference_radio_group);
        radioGroupRoommateQuestionEight = rootView.findViewById(R.id.roommate_preferences_guests_preference_radio_group);
        radioGroupRoommateQuestionNine = rootView.findViewById(R.id.roommate_preferences_pets_preference_radio_group);

        controller.loadUserInfo(firestore, user).addOnSuccessListener(documentSnapshot -> {
            Map<String, Object> data = documentSnapshot.getData();
            System.out.println("here");
            long gender = (long) data.get("roommate_prefer_same_gender_roommate_value");
            long clean = (long) data.get("roommate_clean_value");
            long reserve = (long) data.get("roommate_reserved_value");
            long party = (long) data.get("roommate_party_value");
            long alcohol = (long) data.get("roommate_alcohol_value");
            long smoke = (long) data.get("roommate_smoke_value");
            long stayLate = (long) data.get("roommate_stay_up_late_on_weekdays_value");
            long guests = (long) data.get("roommate_overnight_guests_value");
            long pet = (long) data.get("roommate_allow_pets_value");

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
        });

        radioGroupRoommateQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_prefer_same_gender_roommate_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_prefer_same_gender_roommate_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_prefer_same_gender_roommate_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_clean_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_clean_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_clean_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_reserved_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_reserved_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_reserved_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_party_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_party_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_party_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_alcohol_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_alcohol_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_alcohol_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_smoke_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_smoke_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_smoke_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_stay_up_late_on_weekdays_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_stay_up_late_on_weekdays_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_stay_up_late_on_weekdays_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_overnight_guests_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_overnight_guests_value", indicatorSometimes);
            } else {
                controller.updateUserMap("roommate_overnight_guests_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionNine.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("roommate_allow_pets_value", indicatorYes);
            } else if (rb.getText().equals("No pref.")) {
                controller.updateUserMap("roommate_allow_pets_value", indicatorSometimes);

            } else {
                controller.updateUserMap("roommate_allow_pets_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();

        });

        saveRoommateButton = rootView.findViewById(R.id.roommate_preferences_save_button);
        saveRoommateButton.setOnClickListener(v -> {
            controller.submitUserMap();
            this.showToast("Roommate Preferences Saved", Toast.LENGTH_SHORT);
        });

        return rootView;
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
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