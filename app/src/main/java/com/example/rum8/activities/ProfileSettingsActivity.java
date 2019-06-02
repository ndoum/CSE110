package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.ProfileSettingsViewPagerAdapter;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class ProfileSettingsActivity extends FragmentActivity
    implements ProfileSettingsControllerListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        initViews();
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(ProfileSettingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        viewPager = findViewById(R.id.profile_settings_view_pager);
        viewPager.setAdapter(new ProfileSettingsViewPagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.activity_profile_settings_tab_layout);
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
