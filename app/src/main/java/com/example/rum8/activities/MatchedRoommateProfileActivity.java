package com.example.rum8.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.rum8.R;
import com.example.rum8.adapters.MatchedRoommateFullProfileAdapter;

import com.example.rum8.controllers.MatchedRoommateProfileController;
import com.example.rum8.fragments.UserTab1Fragment;
import com.example.rum8.fragments.UserTab2Fragment;
import com.example.rum8.fragments.UserTab3Fragment;
import com.example.rum8.fragments.UserTab4Fragment;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class MatchedRoommateProfileActivity extends AppCompatActivity implements MatchedRoommateProfileControllerListener {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private MatchedRoommateProfileController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_roommate_profile_full);

        tablayout = (TabLayout) findViewById(R.id.full_view_tab_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.full_view_app_bar);
        viewPager = (ViewPager) findViewById(R.id.full_view_view_pager);
        MatchedRoommateFullProfileAdapter adapter = new  MatchedRoommateFullProfileAdapter(getSupportFragmentManager());
        adapter.AddFragment(new UserTab1Fragment(), "General");
        adapter.AddFragment(new UserTab2Fragment(), "Personal");
        adapter.AddFragment(new UserTab3Fragment(), "Overview");
        adapter.AddFragment(new UserTab4Fragment(), "Contacts");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        controller.loadMatchUserInfo();
    }


    @Override
    public void showToast(String message) {

    }


    /**
     * Method that initalize the controller for main activity.
     */
    private void initController() {
        controller = new MatchedRoommateProfileController(this, this);
    }



}
