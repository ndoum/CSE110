package com.example.rum8.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.MatchedRoommateFullProfileAdapter;
import com.example.rum8.adapters.ViewLinkListRecycleViewAdapter;
import com.example.rum8.controllers.MatchedRoommateProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.fragments.MatchedFullViewTabFourFragment;
import com.example.rum8.fragments.MatchedFullViewTabOneFragment;
import com.example.rum8.fragments.MatchedFullViewTabThreeFragment;
import com.example.rum8.fragments.MatchedFullViewTabTwoFragment;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;


public class MatchedRoommateProfileActivity extends AppCompatActivity implements MatchedRoommateProfileControllerListener {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private MatchedRoommateProfileController controller;
    private TextView firstName;
    private TextView academicYear;
    private ImageView profilePicture;
    private String matchedUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_roommate_profile_full);
        initController();
        Intent intent = getIntent();
        matchedUserId = intent.getStringExtra(ViewLinkListRecycleViewAdapter.USER_ID_STRING);

        tablayout = findViewById(R.id.full_view_tab_layout);
        appBarLayout = findViewById(R.id.full_view_app_bar);
        viewPager = findViewById(R.id.full_view_view_pager);
        controller.loadMatchUserInfo(matchedUserId);
        MatchedRoommateFullProfileAdapter adapter = new MatchedRoommateFullProfileAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MatchedFullViewTabOneFragment(), "General");
        adapter.AddFragment(new MatchedFullViewTabTwoFragment(), "Personal");
        adapter.AddFragment(new MatchedFullViewTabThreeFragment(), "Overview");
        adapter.AddFragment(new MatchedFullViewTabFourFragment(), "Contacts");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

    }

    public String getMatchedUserId() {
        return this.matchedUserId;
    }

    @Override
    public void showToast(final String message) {

    }

    @Override
    public void showMatchedInfo(final Map<String, Object> data) {
        firstName = findViewById(R.id.matched_roommate_first_name);
        firstName.setText((String) data.get(Db.Keys.FIRST_NAME));
        academicYear = findViewById(R.id.matched_roommate_year);
        academicYear.setText((String) data.get(Db.Keys.ACADEMIC_YEAR) + "Year");
    }

    @Override
    public void setMatchedUserProfileImage(final Bitmap bitmap) {
        profilePicture = findViewById(R.id.matched_user_profile_picture);
        profilePicture.setImageBitmap(bitmap);
    }

    @Override
    public void showDefaultImage() {
        profilePicture = findViewById(R.id.matched_user_profile_picture);
        profilePicture.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.images));
    }

    @Override
    public void onBackPressed(){
        if (isTaskRoot()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            super.onBackPressed();
        }
    }


    private void initController() {
        controller = new MatchedRoommateProfileController(this);
    }

}
