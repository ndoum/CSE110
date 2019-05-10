package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

public class ProfileSettingsRoommatePreferencesFragment extends Fragment implements ProfileSettingsControllerListener {
    private ProfileSettingsController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_roommate_preferences, container, false);
        Button save = rootView.findViewById(R.id.roommate_preferences_button);

        controller = new ProfileSettingsController(this);
        save.setOnClickListener(v -> {
            controller.populate(rootView);
        });
        return rootView;
    }
    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }

}