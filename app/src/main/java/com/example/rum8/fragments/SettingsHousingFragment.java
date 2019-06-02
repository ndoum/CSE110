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

public class SettingsHousingFragment extends Fragment implements SettingsControllerListener {

    private SettingsController controller;
    private TextInputEditText accommodationsField;
    private TextInputEditText budgetField;
    private TextInputEditText roomTypeField;
    private TextInputEditText otherThingsField;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_settings_housing, container, false);
        initViews(rootView);
        initController();
        controller.loadUserInfo();
        return rootView;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        accommodationsField.setText((String) data.get(Db.Keys.LIVING_ACCOMMODATIONS));
        roomTypeField.setText((String) data.get(Db.Keys.ROOM_TYPE));
        budgetField.setText((String) data.get(Db.Keys.BUDGET));
        otherThingsField.setText((String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW));
    }

    private void initViews(final View rootView) {
        accommodationsField = rootView.findViewById(R.id.general_info_living_accommodations_field);
        budgetField = rootView.findViewById(R.id.general_info_budget_field);
        roomTypeField = rootView.findViewById(R.id.general_info_room_type_field);
        otherThingsField = rootView.findViewById(R.id.general_info_other_things_field);
        saveButton = rootView.findViewById(R.id.settings_housing_save);

        saveButton.setOnClickListener(v -> {
            final Map<String, Object> userHash = new HashMap<String, Object>() {{
                put(Db.Keys.LIVING_ACCOMMODATIONS, accommodationsField.getText().toString());
                put(Db.Keys.BUDGET, budgetField.getText().toString());
                put(Db.Keys.ROOM_TYPE, roomTypeField.getText().toString());
                put(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW, otherThingsField.getText().toString());
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

}
