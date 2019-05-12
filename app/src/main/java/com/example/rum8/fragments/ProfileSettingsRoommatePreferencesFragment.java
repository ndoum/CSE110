package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

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


        radioGroupRoommateQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("prefer_same_gender_roommate_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("prefer_same_gender_roommate_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("prefer_same_gender_roommate_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("clean_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("clean_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("clean_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("reserved_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("reserved_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("reserved_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("party_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("party_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("party_valuee", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("alcohol_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("alcohol_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("alcohol_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("smoke_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("smoke_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("smoke_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("stay_up_late_on_weekends_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("stay_up_late_on_weekends_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("stay_up_late_on_weekends_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("overnight_guests_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("overnight_guests_value", indicatorSometimes);
            }
            else{
                controller.updateRoommateMap("overnight_guests_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionNine.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateRoommateMap("allow_pets_value", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateRoommateMap("allow_pets_value", indicatorSometimes);

            }
            else{
                controller.updateRoommateMap("allow_pets_value", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();

        });


        saveRoommateButton = rootView.findViewById(R.id.roommate_preferences_save_button);
        saveRoommateButton.setOnClickListener(v -> {
            controller.roommateSaveSubmit();
        });

        return rootView;
    }
    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }

}