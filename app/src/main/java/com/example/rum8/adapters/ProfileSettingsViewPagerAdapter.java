package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.fragments.ProfileSettingsGeneralInfoFragment;
import com.example.rum8.fragments.ProfileSettingsPersonalityLogisticsFragment;
import com.example.rum8.fragments.ProfileSettingsRoommatePreferencesFragment;

public class ProfileSettingsViewPagerAdapter extends FragmentPagerAdapter {
    public ProfileSettingsViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProfileSettingsGeneralInfoFragment();
            case 1:
                return new ProfileSettingsPersonalityLogisticsFragment();
            case 2:
                return new ProfileSettingsRoommatePreferencesFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }
}