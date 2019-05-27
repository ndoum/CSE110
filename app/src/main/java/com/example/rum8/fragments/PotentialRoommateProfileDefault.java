package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.PotentialRoommateProfileDefaultAdapter;
import com.example.rum8.controllers.MainController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class PotentialRoommateProfileDefault extends Fragment implements MainControllerListener {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private MainController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        View rootView = inflater.inflate(R.layout.fragment_potential_roommate_profile_default, container, false);


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



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new MainController(this);
        controller.loadUserInfo();
        rootView.findViewById();



    }


    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {



        onResume();
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToProfileSettings() {
    }

    @Override
    public void goToLogin() {
    }

    @Override
    public void goToAdvSettings() {
    }






}
