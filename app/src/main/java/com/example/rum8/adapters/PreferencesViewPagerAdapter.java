package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.fragments.PreferencesRoommateFragment;
import com.example.rum8.fragments.PreferencesSelfFragment;

public class PreferencesViewPagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] fragments = {
            new PreferencesSelfFragment(),
            new PreferencesRoommateFragment()
    };
    private final String[] fragmentTitles = {
            "Personality Logistics",
            "Roommate Preferences"
    };

    public PreferencesViewPagerAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(final int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return fragmentTitles[position];
    }

}