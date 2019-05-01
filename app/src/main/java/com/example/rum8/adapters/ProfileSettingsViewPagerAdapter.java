package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProfileSettingsViewPagerAdapter extends FragmentPagerAdapter {
    public ProfileSettingsViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

        }
    }


    @Override
    public int getCount() {
        return 0;
    }
}
