package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.fragments.ProfileSettingsPersonalityLogisticsFragment;
import com.example.rum8.fragments.ProfileSettingsRoommatePreferencesFragment;

public class ProfileSettingsViewPagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] fragments = {
            new ProfileSettingsPersonalityLogisticsFragment(),
            new ProfileSettingsRoommatePreferencesFragment()
    };
    private final String[] fragmentTitles = {
            "Personality Logistics",
            "Roommate Preferences"
    };

    public ProfileSettingsViewPagerAdapter(final FragmentManager fragmentManager) {
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