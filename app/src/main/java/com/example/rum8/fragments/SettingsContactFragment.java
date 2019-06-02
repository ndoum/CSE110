package com.example.rum8.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.MainActivity;
import com.example.rum8.controllers.SettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.SettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class SettingsContactFragment extends Fragment implements SettingsControllerListener {

    private SettingsController controller;
    private TextInputEditText phoneNumberField;
    private TextInputEditText facebookField;
    private TextInputEditText snapchatField;
    private Button saveButton;
    private Button goToMainButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_settings_contact, container, false);
        initViews(rootView);
        initController();
        controller.loadUserInfo();
        return rootView;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        phoneNumberField.setText((String) data.get(Db.Keys.PHONE_NUMBER));
        facebookField.setText((String) data.get(Db.Keys.FACEBOOK));
        snapchatField.setText((String) data.get(Db.Keys.SNAPCHAT));
    }

    private void initViews(final View rootView) {
        phoneNumberField = rootView.findViewById(R.id.personal_info_phone_field);
        facebookField = rootView.findViewById(R.id.personal_info_facebook_field);
        snapchatField = rootView.findViewById(R.id.personal_info_snapchat_field);
        saveButton = rootView.findViewById(R.id.settings_contact_save);
        goToMainButton = rootView.findViewById(R.id.settings_go_to_main);

        saveButton.setOnClickListener(v -> {
            final Map<String, Object> userHash = new HashMap<String, Object>() {{
                put(Db.Keys.PHONE_NUMBER, phoneNumberField.getText().toString());
                put(Db.Keys.FACEBOOK, facebookField.getText().toString());
                put(Db.Keys.SNAPCHAT, snapchatField.getText().toString());
            }};
            controller.onSaveButtonClicked(userHash);
        });

        goToMainButton.setOnClickListener(v -> {
            controller.onGoToMainClicked();
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
        final Intent intent;
        intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
