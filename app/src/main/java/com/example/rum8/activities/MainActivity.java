package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rum8.R;
import com.example.rum8.controllers.MainController;
import com.example.rum8.fragments.PotentialRoommateProfileInit;
import com.example.rum8.fragments.PotentialRoommateProfileAlt;
import com.example.rum8.listeners.MainControllerListener;

/**
 * Class that implements the home page of application.
 *
 * <p>
 * Bugs: (a list of bugs and other problems)
 *
 * @author
 */
public class MainActivity extends AppCompatActivity implements MainControllerListener {

    private MainController controller;

    @Override
    public void showToast(final String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initController();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Creates the menu inside of the toolbar
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
            case R.id.main_activity_go_to_view_link_list:
                controller.onGoToLinkListButtonClicked();
                return true;
            case R.id.main_activity_go_to_adv_settings:
                controller.onGoToAdvSettingsButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Method navigates to the profile setting class.
     */
    @Override
    public void goToProfileSettings() {
        final Intent intent = new Intent(MainActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Finish this activity because it should not be the parent activity of
     * {@link LoginActivity}.
     */
    @Override
    public void goToLogin() {
        final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToLinkList() {
        final Intent intent = new Intent(MainActivity.this, ViewLinkListActivity.class);
        startActivity(intent);
    }
    public void goToAdvSettings() {
        final Intent intent = new Intent(MainActivity.this, AdvancedSettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Method that initalize the controller for main activity.
     */
    private void initController() {
        controller = new MainController(this);
    }

    /**
     * Method that switch between potential roommate profile fragment and roommate
     * profile fragment alternate based on button clicks
     *
     * @param view
     */
    public void ChangeFragment(View view) {
        Fragment fragment;

        // Actions when the link button is clicked
        if (view == findViewById(R.id.link_button)) {

            fragment = new PotentialRoommateProfileInit();

            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            ft.setCustomAnimations(R.anim.exit_right, R.anim.exit_right);
            ft.replace(R.id.fragment_place, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        // Actions when the not link button is clicked
        if (view == findViewById(R.id.not_link_button)) {

            fragment = new PotentialRoommateProfileAlt();

            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            ft.setCustomAnimations(R.anim.exit_right, R.anim.exit_right);
            ft.replace(R.id.fragment_place, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }
    }

}
