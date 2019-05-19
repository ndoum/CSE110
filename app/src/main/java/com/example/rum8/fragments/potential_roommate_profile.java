package com.example.rum8.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rum8.adapters.PotentialRoommateProfileInitAdapter;

import com.example.rum8.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * Class that implements potential roommate profile
 */
public class potential_roommate_profile extends Fragment {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }

        View rootView = inflater.inflate(R.layout.fragment_potential_roommate_profile, container, false);

        tablayout = rootView.findViewById(R.id.potential_roommate_profile_tablayout_id);
        appBarLayout = rootView.findViewById(R.id.potential_roommate_profile_appbarid);
        viewPager = rootView.findViewById(R.id.potential_roommate_profile_viewpager_id);

        PotentialRoommateProfileInitAdapter adapter = new PotentialRoommateProfileInitAdapter(getChildFragmentManager());
        adapter.AddFragment(new UserTab1Fragment(), "General");
        adapter.AddFragment(new UserTab2Fragment(), "Personal");
        adapter.AddFragment(new UserTab3Fragment(), "Overview");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);



        return rootView;
    }

}
