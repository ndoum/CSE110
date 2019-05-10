package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ProfileSettingsGeneralInfoFragment extends Fragment implements ProfileSettingsControllerListener {
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;
    private Spinner academicYearSpinner;
    private Spinner collegeSpinner;
    private Button buttonNext;

    private ProfileSettingsController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);

        controller = new ProfileSettingsController(this);

        //NAME FIELDS
        firstNameField = rootView.findViewById(R.id.general_info_first_name_field);
        lastNameField = rootView.findViewById(R.id.general_info_last_name_field);

        //FILLING THE GENDER SPINNER
        genderSpinner = rootView.findViewById(R.id.general_info_gender_spinner);
        final ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_gender_items, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        //FILLING THE ACADEMIC YEAR SPINNER
        academicYearSpinner = rootView.findViewById(R.id.general_info_academic_year_spinner);
        final ArrayAdapter<CharSequence> academicYearAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_academic_year_items, android.R.layout.simple_spinner_item);
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        academicYearSpinner.setAdapter(academicYearAdapter);

        //FILLING THE COLLEGE SPINNER
        collegeSpinner = rootView.findViewById(R.id.general_info_college_spinner);
        final ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_college_items, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(collegeAdapter);

        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);
        buttonNext.setOnClickListener(v -> {
            final Map<String, Object> userInfo = ImmutableMap.of(
                    "first_name", firstNameField.getText().toString(),
                    "last_name", lastNameField.getText().toString(),
                    "gender", genderSpinner.getSelectedItem().toString(),
                    "academic_year", academicYearSpinner.getSelectedItem().toString(),
                    "college", collegeSpinner.getSelectedItem().toString()
            );

            controller.onSubmit(userInfo);
        });

        return rootView;
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }


}
