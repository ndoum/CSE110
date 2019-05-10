package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;

import java.util.HashMap;

public class ProfileSettingsPersonalityLogisticsFragment extends Fragment implements ProfileSettingsControllerListener {
    ProfileSettingsController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile_settings_personality_logistics, container, false);

        RadioButton cleanY = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_yes);
        RadioButton cleanN = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_no);
        RadioButton cleanNP = rootView.findViewById(R.id.personal_preferences_cleanliness_preference_no_pref);

        controller = new ProfileSettingsController(this);
        cleanN.setOnClickListener(v -> {
                int value = -1;
                String key = "clean";
                controller.updateMap(key, value);
            });

        return rootView;
    }
    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }
}
