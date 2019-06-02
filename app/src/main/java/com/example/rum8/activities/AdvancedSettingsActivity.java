package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private RadioGroup roomType;
    private TextInputEditText otherThingsField;
    private TextInputEditText aboutMeField;
    private TextInputEditText hobbiesField;
    private TextInputEditText interestsField;
    private TextInputEditText phoneNumberField;
    private TextInputEditText majorField;
    private TextInputEditText facebookField;
    private TextInputEditText snapchatField;
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
        majorField = findViewById(R.id.personal_info_major_field);
        accommodationsField = findViewById(R.id.general_info_living_accommodations_field);
        roomType = findViewById(R.id.room_type);
        budgetField = findViewById(R.id.general_info_budget_field);
        otherThingsField = findViewById(R.id.general_info_other_things_field);
        aboutMeField = findViewById(R.id.personal_info_bio_field);
        hobbiesField = findViewById(R.id.personal_info_hobbies_field);
        interestsField = findViewById(R.id.personal_info_interest_field);
        phoneNumberField = findViewById(R.id.personal_info_phone_field);
        facebookField = findViewById(R.id.personal_info_facebook_field);
        snapchatField = findViewById(R.id.personal_info_snapchat_field);
        saveButton = findViewById(R.id.button_advanced_settings_save);



        roomType.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            RadioButton rb = group.findViewById(checkedId);
            final Map<String, Object> userHash = new HashMap<String, Object>() {
                {
                    put(Db.Keys.ROOM_TYPE, rb.getText().toString());
                }
            };
            controller.onSaveButtonClicked(userHash);
        });

        phoneNumberField.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        saveButton.setOnClickListener(v -> {
            final Map<String, Object> userHash = new HashMap<String, Object>() {{
                put(Db.Keys.MAJOR, majorField.getText().toString());
                put(Db.Keys.LIVING_ACCOMMODATIONS, accommodationsField.getText().toString());
                put(Db.Keys.BUDGET, budgetField.getText().toString());
                put(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW, otherThingsField.getText().toString());
                put(Db.Keys.ABOUT_ME, aboutMeField.getText().toString());
                put(Db.Keys.HOBBIES, hobbiesField.getText().toString());
                put(Db.Keys.INTERESTS, interestsField.getText().toString());
                put(Db.Keys.PHONE_NUMBER, phoneNumberField.getText().toString());
                put(Db.Keys.FACEBOOK, facebookField.getText().toString());
                put(Db.Keys.SNAPCHAT, snapchatField.getText().toString());
            }};
            controller.onSaveButtonClicked(userHash);
        });

        controller.loadUserInfo();
    }

    public void initController() {
        controller = new AdvancedSettingsController(this);
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final String my_major = (String) data.get(Db.Keys.MAJOR);
        final String about_me = (String) data.get(Db.Keys.ABOUT_ME);
        final String room_type = (String) data.get(Db.Keys.ROOM_TYPE);
        final String budget = (String) data.get(Db.Keys.BUDGET);
        final String hobbies = (String) data.get(Db.Keys.HOBBIES);
        final String interests = (String) data.get(Db.Keys.INTERESTS);
        final String living_accommodations = (String) data.get(Db.Keys.LIVING_ACCOMMODATIONS);
        final String other_things_you_should_know = (String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW);
        final String phone_number = (String) data.get(Db.Keys.PHONE_NUMBER);
        final String facebook = (String) data.get(Db.Keys.FACEBOOK);
        final String snapchat = (String) data.get(Db.Keys.SNAPCHAT);
        majorField.setText(my_major);
        accommodationsField.setText(living_accommodations);
        //roomType.check(R.id.room_type_);
        budgetField.setText(budget);
        otherThingsField.setText(other_things_you_should_know);
        aboutMeField.setText(about_me);
        hobbiesField.setText(hobbies);
        interestsField.setText(interests);
        phoneNumberField.setText(phone_number);
        facebookField.setText(facebook);
        snapchatField.setText(snapchat);
    }

    @Override
    public void goToLogin() {
        final Intent intent = new Intent(AdvancedSettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
