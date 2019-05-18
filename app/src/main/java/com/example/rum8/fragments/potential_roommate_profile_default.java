package com.example.rum8.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rum8.R;
import com.example.rum8.adapters.ViewPagerAdapter;
import com.example.rum8.adapters.potentialRoommateProfileDefaultAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class potential_roommate_profile_default extends Fragment {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private ExtendedFloatingActionButton linkButton;
    private ExtendedFloatingActionButton notLinkButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (container != null) {
            container.removeAllViews();
        }

        View rootView = inflater.inflate(R.layout.fragment_potential_roommate_profile_default, container, false);

        tablayout = rootView.findViewById(R.id.potential_roommate_profile_default_tablayout_id);
        appBarLayout = rootView.findViewById(R.id.potential_roommate_profile_default_appbarid);
        viewPager = rootView.findViewById(R.id.potential_roommate_profile_default_viewpager_id);

        potentialRoommateProfileDefaultAdapter adapter = new potentialRoommateProfileDefaultAdapter(getChildFragmentManager());
        adapter.AddFragment(new UserTab1Fragment(), "Tab1");
        adapter.AddFragment(new UserTab2Fragment(), "Tab2");
        adapter.AddFragment(new UserTab3Fragment(), "Tab3");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


        // Inflate the layout for this fragment
        return rootView;
    }


}
