package com.example.rum8.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.SettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.SettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Class that contains the fragment that display the about me tab
 * for setting activity.
 */
public class SettingsAboutMeFragment extends Fragment implements SettingsControllerListener {

    // Initialize class variable
    private SettingsController controller;
    private TextInputEditText majorField;
    private TextInputEditText aboutMeField;
    private TextInputEditText hobbiesField;
    private TextInputEditText interestsField;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_settings_about_me, container, false);
        initViews(rootView);
        initController();
        controller.loadUserInfo();
        return rootView;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        majorField.setText((String) data.get(Db.Keys.MAJOR));
        aboutMeField.setText((String) data.get(Db.Keys.ABOUT_ME));
        hobbiesField.setText((String) data.get(Db.Keys.HOBBIES));
        interestsField.setText((String) data.get(Db.Keys.INTERESTS));
    }

    private void initViews(final View rootView) {
        majorField = rootView.findViewById(R.id.personal_info_major_field);
        aboutMeField = rootView.findViewById(R.id.personal_info_bio_field);
        hobbiesField = rootView.findViewById(R.id.personal_info_hobbies_field);
        interestsField = rootView.findViewById(R.id.personal_info_interest_field);
        saveButton = rootView.findViewById(R.id.settings_about_me_save);

        saveButton.setOnClickListener(v -> {
            final Map<String, Object> userHash = new HashMap<String, Object>() {{
                put(Db.Keys.MAJOR, majorField.getText().toString());
                put(Db.Keys.ABOUT_ME, aboutMeField.getText().toString());
                put(Db.Keys.HOBBIES, hobbiesField.getText().toString());
                put(Db.Keys.INTERESTS, interestsField.getText().toString());
            }};
            controller.onSaveButtonClicked(userHash);
        });
    }

    private void initController() {
        controller = new SettingsController(this);
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
    public void updateUploadImagePercentage(double percentage) {
    }

    @Override
    public void chooseImage() {
    }

    @Override
    public void showDefaultImage() {
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {
    }

    @Override
    public void goToMain() {
    }
}
