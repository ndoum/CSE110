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

import java.util.HashMap;
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
        View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);

        controller = new ProfileSettingsController(this);

        //NAME FIELDS
        firstNameField = rootView.findViewById(R.id.general_info_first_name_field);
        lastNameField = rootView.findViewById(R.id.general_info_last_name_field);

        //FILLING THE GENDER SPINNER
        genderSpinner = rootView.findViewById(R.id.general_info_gender_spinner);
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_gender_items, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        //FILLING THE ACADEMIC YEAR SPINNER
        academicYearSpinner = rootView.findViewById(R.id.general_info_academic_year_spinner);
        ArrayAdapter<CharSequence> academicYearAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_academic_year_items, android.R.layout.simple_spinner_item);
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        academicYearSpinner.setAdapter(academicYearAdapter);

        //FILLING THE COLLEGE SPINNER
        collegeSpinner = rootView.findViewById(R.id.general_info_college_spinner);
        ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_college_items, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(collegeAdapter);

        //FILLING THE BUTTON
        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);

        buttonNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Map<String, Object> userInfo = new HashMap<>();

                // get names
                final String firstName = firstNameField.getText().toString();
                final String lastName = lastNameField.getText().toString();
                // get gender
                final String gender = genderSpinner.getSelectedItem().toString();
                // get academic year
                final String year = academicYearSpinner.getSelectedItem().toString();
                // get college
                final String college = collegeSpinner.getSelectedItem().toString();

                userInfo.put("first_name",firstName);
                userInfo.put("last_name", lastName);
                userInfo.put("gender", gender);
                userInfo.put("academic_year", year);
                userInfo.put("college", college);

                controller.onSubmit(userInfo);
            }
        });

        return rootView;
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }


}
