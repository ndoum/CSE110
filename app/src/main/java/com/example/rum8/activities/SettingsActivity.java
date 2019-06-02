package com.example.rum8.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.SettingsViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SettingsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
    }

    private void initViews() {
        viewPager = findViewById(R.id.settings_view_pager);
        viewPager.setAdapter(new SettingsViewPagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.settings_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
