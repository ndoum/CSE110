package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.AdvancedSettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class AdvancedSettingsActivity extends AppCompatActivity
        implements AdvancedSettingsControllerListener {

    private AdvancedSettingsController controller;
    private TextInputEditText accommodationsField;
    private TextInputEditText budgetField;
    private TextInputEditText roomTypeField;
    private TextInputEditText otherThingsField;
    private TextInputEditText aboutMeField;
    private TextInputEditText hobbiesField;
    private TextInputEditText interestsField;
    private TextInputEditText phoneNumberField;
    private Button saveButton;

    @Override
    public void showToast(final String message) {
        Toast.makeText(AdvancedSettingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_profile_settings);
        initController();
        initViews();
    }

    public void initViews() {

        accommodationsField = findViewById(R.id.general_info_living_accommodations_field);
        roomTypeField= findViewById(R.id.general_info_room_type_field);
        budgetField  = findViewById(R.id.general_info_budget_field);
        otherThingsField = findViewById(R.id.general_info_other_things_field);
        aboutMeField = findViewById(R.id.personal_info_bio_field);
        hobbiesField = findViewById(R.id.personal_info_hobbies_field);
        interestsField = findViewById(R.id.personal_info_interest_field);
        phoneNumberField = findViewById(R.id.personal_info_phone_field);

        controller.loadUserInfo();

        saveButton = findViewById(R.id.button_advanced_settings_save);
        saveButton.setOnClickListener(v -> {
            final Map<String, Object> userHash = new HashMap<String, Object>() {{
                put(Db.Keys.LIVING_ACCOMMODATIONS, accommodationsField.getText().toString());
                put(Db.Keys.BUDGET,budgetField.getText().toString());
                put(Db.Keys.ROOM_TYPE, roomTypeField.getText().toString());
                put(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW, otherThingsField.getText().toString());
                put(Db.Keys.ABOUT_ME, aboutMeField.getText().toString());
                put(Db.Keys.HOBBIES, hobbiesField.getText().toString());
                put(Db.Keys.INTERESTS, interestsField.getText().toString());
                put(Db.Keys.PHONE_NUMBER, phoneNumberField.getText().toString());
            }};
            controller.onSaveButtonClicked(userHash);
        });
    }

    public void initController() {
        controller = new AdvancedSettingsController(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //Creates the menu inside of the toolbar
        getMenuInflater().inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_go_to_profile_settings:
                controller.onGoToProfileSettingsButtonClicked();
                return true;
            case R.id.main_activity_log_out:
                controller.onLogOutButtonClicked();
                return true;
            case R.id.main_activity_go_to_adv_settings:
                controller.onAdvSettingsButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final String about_me = (String) data.get(Db.Keys.ABOUT_ME);
        final String room_type = (String) data.get(Db.Keys.ROOM_TYPE);
        final String budget = (String) data.get(Db.Keys.BUDGET);
        final String hobbies = (String) data.get(Db.Keys.HOBBIES);
        final String interests = (String) data.get(Db.Keys.INTERESTS);
        final String living_accommodations = (String) data.get(Db.Keys.LIVING_ACCOMMODATIONS);
        final String other_things_you_should_know = (String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW);
        final String phone_number = (String) data.get(Db.Keys.PHONE_NUMBER);
        accommodationsField.setText(living_accommodations);
        roomTypeField.setText(room_type);
        budgetField.setText(budget);
        otherThingsField.setText(other_things_you_should_know);
        aboutMeField.setText(about_me);
        hobbiesField.setText(hobbies);
        interestsField.setText(interests);
        phoneNumberField.setText(phone_number);
    }

    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(AdvancedSettingsActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToLogin() {
        final Intent intent = new Intent(AdvancedSettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToAdvSettings() {
    }

}
