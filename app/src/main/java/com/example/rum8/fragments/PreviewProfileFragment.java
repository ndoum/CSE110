package com.example.rum8.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rum8.R;
import com.example.rum8.adapters.PreviewProfileAdapter;
import com.example.rum8.controllers.PreviewProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.PreviewProfileControllerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class PreviewProfileFragment extends Fragment implements PreviewProfileControllerListener {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private PreviewProfileController controller;
    private View rootView;

    private TextView firstName;
    private TextView academicYear;
    private ImageView profilePicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        controller = new PreviewProfileController(this);

        rootView = inflater.inflate(R.layout.fragment_preview_profile, container, false);

        tablayout = rootView.findViewById(R.id.preview_profile_tablayout_id);
        appBarLayout = rootView.findViewById(R.id.preview_profile_appbarid);
        viewPager = rootView.findViewById(R.id.preview_profile_viewpager_id);
        profilePicture = rootView.findViewById(R.id.user_profile_picture);

        PreviewProfileAdapter adapter = new PreviewProfileAdapter(
                getChildFragmentManager());

        System.out.println("adapter");

        adapter.AddFragment(new PreviewProfileTab1Fragment(), "General");
        adapter.AddFragment(new PreviewProfileTab2Fragment(), "Personal");
        adapter.AddFragment(new PreviewProfileTab3Fragment(), "Overview");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        controller.loadUserInfo();

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        firstName = rootView.findViewById(R.id.preview_first_name);
        firstName.setText((String) data.get(Db.Keys.FIRST_NAME));
        academicYear = rootView.findViewById(R.id.preview_academic_year);
        academicYear.setText(data.get(Db.Keys.ACADEMIC_YEAR) + " Year");
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {
        profilePicture.setImageBitmap(bitmap);
    }

    @Override
    public void showDefaultImage() {
        profilePicture.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.images));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFragment() {

    }

}
