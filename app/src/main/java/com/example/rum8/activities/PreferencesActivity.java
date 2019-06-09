package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.PreferencesViewPagerAdapter;
import com.example.rum8.listeners.PreferencesControllerListener;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

/**
 * Class that contains preferences questionnaire for potential roommate
 * algorithm.
 */
public class PreferencesActivity extends FragmentActivity implements PreferencesControllerListener {

    // Initialize class variable
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        initViews();
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(PreferencesActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        viewPager = findViewById(R.id.preferences_view_pager);
        viewPager.setAdapter(new PreferencesViewPagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.preferences_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showCurrentUserInfo(Map<String, Object> data) {

    }

    /**
     * Setter method that set view pager to the given number
     * that represented the order of fragments page.
     *
     * @param fragmentNumber
     */
    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void goToMainPage() {
    }
}
