package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.R;
import com.example.rum8.fragments.ProfileSettingsGeneralInfoFragment;
import com.example.rum8.fragments.ProfileSettingsPersonalityLogisticsFragment;
import com.example.rum8.fragments.ProfileSettingsRoommatePreferencesFragment;

public class ProfileSettingsViewPagerAdapter extends FragmentPagerAdapter {
    public static int FRAGMENT_COUNT = 3;
    public static final int GENERAL_INFO_POSITION = 0;
    public static final int PERSONALITY_LOGISTICS_POSITION = 1;
    public static final int ROOMMATE_PREFERENCES_POSTIION = 2;

    public ProfileSettingsViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case GENERAL_INFO_POSITION:
                return new ProfileSettingsGeneralInfoFragment();
            case PERSONALITY_LOGISTICS_POSITION:
                return new ProfileSettingsPersonalityLogisticsFragment();
            case ROOMMATE_PREFERENCES_POSTIION:
                return new ProfileSettingsRoommatePreferencesFragment();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title;
        switch(position){
            case GENERAL_INFO_POSITION:
                title = "General Info";
                break;
            case PERSONALITY_LOGISTICS_POSITION:
                title = "Personality Logistics";
                break;
            case ROOMMATE_PREFERENCES_POSTIION:
                title = "Roommate Preferences";
                break;
            default:
                title = "";
                break;
        }
        return title;
    }
}