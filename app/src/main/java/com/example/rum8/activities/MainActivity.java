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
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ViewPagerAdapter;
import com.example.rum8.controllers.MainController;
import com.example.rum8.fragments.UserTab1Fragment;
import com.example.rum8.fragments.UserTab2Fragment;
import com.example.rum8.fragments.UserTab3Fragment;
import com.example.rum8.fragments.potential_roommate_profile;
import com.example.rum8.fragments.potential_roommate_profile_alt;
import com.example.rum8.listeners.MainControllerListener;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements MainControllerListener {

    private MainController controller;
    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private ExtendedFloatingActionButton linkButton;
    private ExtendedFloatingActionButton notLinkButton;

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(MainActivity.this, message, toastLength).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initViews();
        initController();
//        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
//        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
//        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.AddFragment(new UserTab1Fragment(), "Tab1");
//        adapter.AddFragment(new UserTab2Fragment(), "Tab2");
//        adapter.AddFragment(new UserTab3Fragment(), "Tab3");
//        viewPager.setAdapter(adapter);
//        tablayout.setupWithViewPager(viewPager);
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


    private void initController() {
        controller = new MainController(this);
    }

    public void ChangeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.link_button)){
            fragment = new potential_roommate_profile();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.not_link_button)){
            fragment = new potential_roommate_profile_alt();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);

            ft.commit();

        }
    }

}
