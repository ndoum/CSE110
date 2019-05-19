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
import com.example.rum8.listeners.ProfileSettingsControllerListener;

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

        radioGroupPersonalQuestionOne = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_radio_group);
        radioGroupPersonalQuestionTwo = rootView.findViewById(R.id.personal_preferences_reserved_preference_radio_group);
        radioGroupPersonalQuestionThree = rootView.findViewById(R.id.personal_preferences_parties_preference_radio_group);
        radioGroupPersonalQuestionFour = rootView.findViewById(R.id.personal_preferences_alcohol_preference_radio_group);
        radioGroupPersonalQuestionFive = rootView.findViewById(R.id.personal_preferences_smoke_preference_radio_group);
        radioGroupPersonalQuestionSix = rootView.findViewById(R.id.personal_preferences_sleep_preference_radio_group);
        radioGroupPersonalQuestionSeven = rootView.findViewById(R.id.personal_preferences_guests_preference_radio_group);
        radioGroupPersonalQuestionEight = rootView.findViewById(R.id.personal_preferences_pet_preference_radio_group);

        controller = new ProfileSettingsController(this);

        radioGroupPersonalQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {

                controller.updateUserMap("clean_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {

                controller.updateUserMap("clean_value", indicatorSometimes);
            } else {
                controller.updateUserMap("clean_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("reserved_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("reserved_value", indicatorSometimes);
            } else {
                controller.updateUserMap("reserved_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("party_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("party_value", indicatorSometimes);
            } else {
                controller.updateUserMap("party_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("alcohol_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("alcohol_value", indicatorSometimes);
            } else {
                controller.updateUserMap("alcohol_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("smoke_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("smoke_value", indicatorSometimes);
            } else {
                controller.updateUserMap("smoke_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("stay_up_late_on_weekends_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("stay_up_late_on_weekends_value", indicatorSometimes);
            } else {
                controller.updateUserMap("stay_up_late_on_weekends_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("overnight_guests_value", indicatorYes);
            } else if (rb.getText().equals("Sometimes")) {
                controller.updateUserMap("overnight_guests_value", indicatorSometimes);
            } else {
                controller.updateUserMap("overnight_guests_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton) group.findViewById(checkedId);
            if (rb.getText().equals("Yes")) {
                controller.updateUserMap("allow_pets_value", indicatorYes);
            } else if (rb.getText().equals("Maybe")) {
                controller.updateUserMap("allow_pets_value", indicatorSometimes);
            } else {
                controller.updateUserMap("allow_pets_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        personalSaveButton = rootView.findViewById(R.id.personal_references_save_button);
        personalSaveButton.setOnClickListener(v -> {
            controller.submitUserMap();
            this.showToast("Personal logistics Saved", Toast.LENGTH_SHORT);
        });

        personalNextButton = rootView.findViewById(R.id.personal_references_next_button);
        personalNextButton.setOnClickListener(v -> {
            ((ProfileSettingsActivity) getActivity()).setViewPager(2);
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
