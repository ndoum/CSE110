package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.MainController;
import com.example.rum8.listeners.MainControllerListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainControllerListener {

    private MainController controller;

    private FloatingActionButton linkButton;
    private FloatingActionButton notLinkButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initController();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(MainActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToLogin() {
        final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void linkButtonClicked() {
        Toast.makeText(getApplicationContext(), "LIKED", Toast.LENGTH_LONG).show();
    }

    @Override
    public void notLinkButtonClicked() {
        Toast.makeText(getApplicationContext(), "NOT LIKED", Toast.LENGTH_LONG).show();
    }

    private void initViews() {

        //initialize and set listener for buttons
        linkButton = findViewById(R.id.link_button);
        linkButton.setOnClickListener(v -> controller.onLinkButtonClicked());

        notLinkButton = findViewById(R.id.not_link_button);
        notLinkButton.setOnClickListener(v -> controller.onNotLinkButtonClicked());
    }

    private void initController() {
        controller = new MainController(this);
    }

}
