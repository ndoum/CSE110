package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

import java.util.HashMap;

public class ProfileSettingsPersonalityLogisticsFragment extends Fragment implements ProfileSettingsControllerListener {
    ProfileSettingsController controller;
    private RadioGroup radioGroupPersonalQuestionOne;
    private RadioGroup radioGroupPersonalQuestionTwo;
    private RadioGroup radioGroupPersonalQuestionThree;
    private RadioGroup radioGroupPersonalQuestionFour;
    private RadioGroup radioGroupPersonalQuestionFive;
    private RadioGroup radioGroupPersonalQuestionSix;
    private RadioGroup radioGroupPersonalQuestionSeven;
    private RadioGroup radioGroupPersonalQuestionEight;

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
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("clean", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("clean", indicatorSometimes);
            }
            else{
                controller.updateMap("clean", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionTwo.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("reserved", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("reserved", indicatorSometimes);
            }
            else{
                controller.updateMap("reserved", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionThree.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("parties", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("parties", indicatorSometimes);
            }
            else{
                controller.updateMap("parties", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionFour.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("alcohol", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("alcohol", indicatorSometimes);
            }
            else{
                controller.updateMap("alcohol", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionFive.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("smoke", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("smoke", indicatorSometimes);
            }
            else{
                controller.updateMap("smoke", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionSix.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("bed", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("bed", indicatorSometimes);
            }
            else{
                controller.updateMap("bed", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionSeven.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("guests", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("guests", indicatorSometimes);
            }
            else{
                controller.updateMap("guests", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        radioGroupPersonalQuestionEight.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)group.findViewById(checkedId);
            if(rb.getText().equals("Yes")){
                controller.updateMap("pet", indicatorYes);
            }
            else if(rb.getText().equals("Sometimes")){
                controller.updateMap("pet", indicatorSometimes);
            }
            else{
                controller.updateMap("pet", indicatorNo);
            }
            Toast.makeText(rootView.getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }
    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }
}
