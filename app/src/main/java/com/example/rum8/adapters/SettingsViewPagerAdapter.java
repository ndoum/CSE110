package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.fragments.SettingsAboutMeFragment;
import com.example.rum8.fragments.SettingsContactFragment;
import com.example.rum8.fragments.SettingsGeneralFragment;
import com.example.rum8.fragments.SettingsHousingFragment;

public class SettingsViewPagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] fragments = {
            new SettingsGeneralFragment(),
            new SettingsAboutMeFragment(),
            new SettingsHousingFragment(),
            new SettingsContactFragment()
    };
    private final String[] fragmentTitles = {
            "General",
            "About me",
            "Housing",
            "Contact"
    };

    public SettingsViewPagerAdapter(final FragmentManager fragmentManager) {
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
