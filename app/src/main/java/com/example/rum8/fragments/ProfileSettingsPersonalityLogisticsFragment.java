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
import com.example.rum8.activities.ProfileSettingsActivity;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

/**
 * Class that implements profile settings general logistic question sets in profile
 * settings activity.
 */
public class ProfileSettingsPersonalityLogisticsFragment extends Fragment implements ProfileSettingsControllerListener {
    private ProfileSettingsController controller;
    private RadioGroup radioGroupPersonalQuestionOne;
    private RadioGroup radioGroupPersonalQuestionTwo;
    private RadioGroup radioGroupPersonalQuestionThree;
    private RadioGroup radioGroupPersonalQuestionFour;
    private RadioGroup radioGroupPersonalQuestionFive;
    private RadioGroup radioGroupPersonalQuestionSix;
    private RadioGroup radioGroupPersonalQuestionSeven;
    private RadioGroup radioGroupPersonalQuestionEight;
    private Button personalSaveButton;
    private Button personalNextButton;
    private int indicatorYes = 1;
    private int indicatorSometimes = 0;
    private int indicatorNo = -1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile_settings_personality_logistics, container, false);
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        radioGroupPersonalQuestionOne = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_radio_group);
        radioGroupPersonalQuestionTwo = rootView.findViewById(R.id.personal_preferences_reserved_preference_radio_group);
        radioGroupPersonalQuestionThree = rootView.findViewById(R.id.personal_preferences_parties_preference_radio_group);
        radioGroupPersonalQuestionFour = rootView.findViewById(R.id.personal_preferences_alcohol_preference_radio_group);
        radioGroupPersonalQuestionFive = rootView.findViewById(R.id.personal_preferences_smoke_preference_radio_group);
        radioGroupPersonalQuestionSix = rootView.findViewById(R.id.personal_preferences_sleep_preference_radio_group);
        radioGroupPersonalQuestionSeven = rootView.findViewById(R.id.personal_preferences_guests_preference_radio_group);
        radioGroupPersonalQuestionEight = rootView.findViewById(R.id.personal_preferences_pet_preference_radio_group);

        controller = new ProfileSettingsController(this);
        controller.loadUserInfo().addOnSuccessListener(documentSnapshot -> {
                    Map<String, Object> data = documentSnapshot.getData();
                    long clean = (long) data.get(Db.Keys.CLEAN_VALUE);
                    long reserve = (long) data.get(Db.Keys.RESERVED_VALUE);
                    long party = (long) data.get(Db.Keys.PARTY_VALUE);
                    long alcohol = (long) data.get(Db.Keys.ALCOHOL_VALUE);
                    long smoke = (long) data.get(Db.Keys.SMOKE_VALUE);
                    long stayLate = (long) data.get(Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE);
                    long guests = (long) data.get(Db.Keys.OVERNIGHT_GUESTS_VALUE);
                    long pet = (long) data.get(Db.Keys.ALLOW_PETS_VALUE);

                    if (clean == 1) {
                        radioGroupPersonalQuestionOne.check(R.id.personal_preferences_cleanliness_preference_yes);
                    } else if (clean == 0) {
                        radioGroupPersonalQuestionOne.check(R.id.personal_preferences_cleanliness_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionOne.check(R.id.personal_preferences_cleanliness_preference_no);
                    }

                    if (reserve == 1) {
                        radioGroupPersonalQuestionTwo.check(R.id.personal_preferences_reserved_preference_yes);
                    } else if (clean == 0) {
                        radioGroupPersonalQuestionTwo.check(R.id.personal_preferences_reserved_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionTwo.check(R.id.personal_preferences_reserved_preference_no);
                    }

                    if (party == 1) {
                        radioGroupPersonalQuestionThree.check(R.id.personal_preferences_parties_preference_yes);
                    } else if (party == 0) {
                        radioGroupPersonalQuestionThree.check(R.id.personal_preferences_parties_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionThree.check(R.id.personal_preferences_parties_preference_no_);
                    }

                    if (alcohol == 1) {
                        radioGroupPersonalQuestionFour.check(R.id.personal_preferences_alcohol_preference_yes);
                    } else if (alcohol == 0) {
                        radioGroupPersonalQuestionFour.check(R.id.personal_preferences_alcohol_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionFour.check(R.id.personal_preferences_alcohol_preference_no);
                    }

                    if (smoke == 1) {
                        radioGroupPersonalQuestionFive.check(R.id.personal_preferences_smoke_preference_yes);
                    } else if (smoke == 0) {
                        radioGroupPersonalQuestionFive.check(R.id.personal_preferences_smoke_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionFive.check(R.id.personal_preferences_smoke_preference_no);
                    }

                    if (stayLate == 1) {
                        radioGroupPersonalQuestionSix.check(R.id.personal_preferences_sleep_preference_yes);
                    } else if (alcohol == 0) {
                        radioGroupPersonalQuestionSix.check(R.id.personal_preferences_sleep_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionSix.check(R.id.personal_preferences_sleep_preference);
                    }

                    if (guests == 1) {
                        radioGroupPersonalQuestionSeven.check(R.id.personal_preferences_guests_preference_yes);
                    } else if (alcohol == 0) {
                        radioGroupPersonalQuestionSeven.check(R.id.personal_preferences_guests_preference_no_pref);
                    } else {
                        radioGroupPersonalQuestionSeven.check(R.id.personal_preferences_guests_preference_no);
                    }

                    if (pet == 1) {
                        radioGroupPersonalQuestionEight.check(R.id.personal_preferences_pet_preference_yes);
                    } else if (alcohol == 0) {
                        radioGroupPersonalQuestionEight.check(R.id.personal_preferences_pet_preference_maybe);
                    } else {
                        radioGroupPersonalQuestionEight.check(R.id.personal_preferences_pet_preference_no);
                    }
                }
        )
                .addOnFailureListener(exception -> {
                    final String message = "Network error";
                    showToast(message);
                });

        radioGroupPersonalQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {

                controller.updateUserMap(Db.Keys.CLEAN_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {

                controller.updateUserMap(Db.Keys.CLEAN_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.CLEAN_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.RESERVED_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.RESERVED_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.RESERVED_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.PARTY_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.PARTY_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.PARTY_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ALCOHOL_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.ALCOHOL_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ALCOHOL_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.SMOKE_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.SMOKE_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.SMOKE_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.OVERNIGHT_GUESTS_VALUE, indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap(Db.Keys.OVERNIGHT_GUESTS_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.OVERNIGHT_GUESTS_VALUE, indicatorNo);
            }
        });

        radioGroupPersonalQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap(Db.Keys.ALLOW_PETS_VALUE, indicatorYes);
            } else if (rb.getText().equals("Maybe")) {
                controller.updateUserMap(Db.Keys.ALLOW_PETS_VALUE, indicatorSometimes);
            } else {
                controller.updateUserMap(Db.Keys.ALLOW_PETS_VALUE, indicatorNo);
            }
        });

        personalSaveButton = rootView.findViewById(R.id.personal_references_save_button);
        personalSaveButton.setOnClickListener(v -> {
            controller.submitUserMap();
            showToast("Personal logistics Saved");
        });

        personalNextButton = rootView.findViewById(R.id.personal_references_next_button);
        personalNextButton.setOnClickListener(v -> {
            ((ProfileSettingsActivity) getActivity()).setViewPager(2);
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
