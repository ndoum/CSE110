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
    private Button saveButton;

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

        saveButton = rootView.findViewById(R.id.roommate_preferences_button);

        radioGroupRoommateQuestionOne.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("genderPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("genderPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("genderPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("cleanPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("cleanPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("cleanPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("reservedPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("reservedPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("reservedPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("partiesPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("partiesPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("partiesPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("alocholPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("alocholPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("alocholPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("smokingPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("smokingPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("smokingPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("bedPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("bedPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("bedPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("guestsPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("guestsPreference", indicatorSometimes);
            }
            else{
                controller.updateMap("guestsPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupRoommateQuestionNine.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("petsPreference", indicatorYes);
            }
            else if(rb.getText().equals("No pref.")){
                controller.updateMap("petsPreference", indicatorSometimes);

            }
            else{
                controller.updateMap("petsPreference", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();

        });

        saveButton.setOnClickListener(v -> {
            controller.populate();
        });

        return rootView;
    }
    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }

}