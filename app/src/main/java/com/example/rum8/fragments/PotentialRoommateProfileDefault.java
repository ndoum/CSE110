package com.example.rum8.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.PotentialRoommateProfileDefaultAdapter;
import com.example.rum8.controllers.MainController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class PotentialRoommateProfileDefault extends Fragment implements MainControllerListener {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private MainController controller;
    private View rootView;

    private TextView firstName;
    private TextView academicYear;
    private ImageView profilePicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        controller = new MainController(this);

        rootView = inflater.inflate(R.layout.fragment_potential_roommate_profile_default, container, false);

        tablayout = rootView.findViewById(R.id.potential_roommate_profile_default_tablayout_id);
        appBarLayout = rootView.findViewById(R.id.potential_roommate_profile_default_appbarid);
        viewPager = rootView.findViewById(R.id.potential_roommate_profile_default_viewpager_id);

        PotentialRoommateProfileDefaultAdapter adapter = new PotentialRoommateProfileDefaultAdapter(
                getChildFragmentManager());
        adapter.AddFragment(new UserTab1Fragment(), "General");
        adapter.AddFragment(new UserTab2Fragment(), "Personal");
        adapter.AddFragment(new UserTab3Fragment(), "Overview");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        controller.loadUserInfo();

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        firstName = rootView.findViewById(R.id.potential_first_name_default);
        firstName.setText((String) data.get(Db.Keys.FIRST_NAME));
        academicYear = rootView.findViewById(R.id.potential_academic_year_default);
        academicYear.setText(data.get(Db.Keys.ACADEMIC_YEAR) + " Year");
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {
        profilePicture = rootView.findViewById(R.id.user_profile_picture);
        profilePicture.setImageBitmap(bitmap);
    }

    @Override
    public void goToProfileSettings() {
        profilePicture.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.images));
    }

    @Override
    public void goToLogin() {
    }

    @Override
    public void goToLinkList() {
    }

    @Override
    public void goToAdvancedProfileSettings() {
    }

    @Override
    public void showToast(final String message) {
    }

    @Override
    public void setFragment() {

    }

    @Override
    public void showPopup() {
    }

    @Override
    public void showDefaultImage() {

    }

    @Override
    public void setFragmentEmpty() {

    }

}
