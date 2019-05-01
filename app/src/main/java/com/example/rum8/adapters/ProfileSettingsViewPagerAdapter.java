package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rum8.fragments.Profile_settings_general_info_fragment;

public class ProfileSettingsViewPagerAdapter extends FragmentPagerAdapter {
    public ProfileSettingsViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Profile_settings_general_info_fragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 1;
    }
}
