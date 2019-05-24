package com.example.rum8.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements fragment pager adapter for potential roommate profile
 * default page.
 */
public class PotentialRoommateProfileDefaultAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();

    /**
     * Constructor that instantiates the potential roommate profile default adapter.
     *
     * @param fm
     */
    public PotentialRoommateProfileDefaultAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Method that returns specific fragment at the given position
     *
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);

    }

    /**
     * Method that returns the size of fragment.
     *
     * @return
     */
    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    /**
     * Method that returns the title of the fragment at given
     * position.
     *
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String Title) {
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);
    }

}
