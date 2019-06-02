package com.example.rum8.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rum8.R;
import com.example.rum8.controllers.MainController;
import com.example.rum8.fragments.PotentialRoommateProfileAlt;
import com.example.rum8.fragments.PotentialRoommateProfileDefault;
import com.example.rum8.listeners.MainControllerListener;

import java.util.Map;

/**
 * Class that implements the home page of application.
 */
public class MainActivity extends AppCompatActivity implements MainControllerListener {

    private MainController controller;
    Button gotit;
    ImageView closePopup;
    Dialog dia;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initController();
        dia = new Dialog(this);
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
                controller.onProfileSettingsButtonClicked();
                return true;
            case R.id.main_activity_log_out:
                controller.onLogOutButtonClicked();
                return true;
            case R.id.main_activity_go_to_view_link_list:
                controller.onGoToLinkListButtonClicked();
                return true;
            case R.id.main_activity_go_to_settings:
                controller.onSettingsButtonClicked();
                return true;
            case R.id.main_activity_go_to_preview_profile:
                controller.onPreviewProfileButtonClicked();
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

    @Override
    public void goToSettings() {
        final Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToProfilePreview() {
        final Intent intent = new Intent(MainActivity.this, PreviewProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Method that initalize the controller for main activity.
     */
    private void initController() {
        controller = new MainController(this);
    }

    @Override
    public void setFragment() {
        showFragment(new PotentialRoommateProfileDefault());
    }

    @Override
    public void setFragmentEmpty() {
        showFragment(new PotentialRoommateProfileAlt());
    }

    private void showFragment(final Fragment fragment) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.exit_right, R.anim.exit_right);
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Method that switch between potential roommate profile fragment and roommate
     * profile fragment alternate based on button clicks
     *
     * @param view
     */
    public void changeFragment(final View view) {
        if (view == findViewById(R.id.link_button)) {
            // Actions when the link button is clicked
            controller.onLikeClicked();
        } else if (view == findViewById(R.id.not_link_button)) {
            // Actions when the not link button is clicked
            controller.onDisLikeClicked();
        }
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPopup() {
        dia.setContentView(R.layout.popup_no_more_profile);
        closePopup = (ImageView) dia.findViewById(R.id.close_popup);
        gotit = (Button) dia.findViewById(R.id.close_popup_button);
        dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dia.show();

        // when user click on got it button,go to profilesetting
        gotit.setOnClickListener(v -> {
            goToProfileSettings();
        });

        closePopup.setOnClickListener(v -> {
            dia.dismiss();
        });
    }

    @Override
    public void showDefaultImage() {

    }

}
