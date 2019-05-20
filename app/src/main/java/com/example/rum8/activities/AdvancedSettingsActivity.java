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
import com.example.rum8.listeners.AdvancedSettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

public class AdvancedSettingsActivity extends AppCompatActivity
        implements AdvancedSettingsControllerListener {

    private AdvancedSettingsController controller;
    private TextInputEditText accommodationsField;
    private TextInputEditText otherThingsField;
    private TextInputEditText aboutMeField;
    private TextInputEditText hobbiesField;
    private TextInputEditText interestsField;
    private TextInputEditText phoneNumberField;
    private Button saveButton;

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(AdvancedSettingsActivity.this, message, toastLength).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_profile_settings);
        initViews();
        initController();
    }

    public void initViews() {
        accommodationsField = (TextInputEditText) findViewById(R.id.general_info_living_accommodations_field);
        otherThingsField = (TextInputEditText) findViewById(R.id.general_info_other_things_field);
        aboutMeField = (TextInputEditText) findViewById(R.id.personal_info_bio_field);
        hobbiesField = (TextInputEditText) findViewById(R.id.personal_info_hobbies_field);
        interestsField = (TextInputEditText) findViewById(R.id.personal_info_interest_field);
        phoneNumberField = (TextInputEditText) findViewById(R.id.personal_info_phone_field);

        saveButton = findViewById(R.id.button_advanced_settings_save);
        saveButton.setOnClickListener(v -> controller.onSaveButtonClicked());
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
            case R.id.main_activity_go_to_view_link_list:
                controller.onGoToViewLinkListButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void goToViewLinkList(){
        final Intent intent = new Intent(AdvancedSettingsActivity.this, ViewLinkListActivity.class);
        startActivity(intent);
        finish();
    }
}
