package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileSettingsGeneralInfoFragment extends Fragment {
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);

        //NAME FIELDS
        firstNameField = rootView.findViewById(R.id.general_info_first_name_field);
        lastNameField = rootView.findViewById(R.id.general_info_last_name_field);

        //FILLING THE GENDER SPINNER
        genderSpinner = (Spinner) rootView.findViewById(R.id.general_info_gender_spinner);
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.profile_settings_gender_items, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        //FILLING THE ACADEMIC YEAR SPINNER

        //FILLING THE COLLEGE SPINNER

        return rootView;
    }
}
