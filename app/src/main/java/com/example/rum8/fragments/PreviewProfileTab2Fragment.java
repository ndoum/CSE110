package com.example.rum8.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.PreviewProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.PreviewProfileControllerListener;

import java.util.Map;

public class PreviewProfileTab2Fragment extends Fragment implements PreviewProfileControllerListener {

    private View view;
    private PreviewProfileController controller;
    private TextView aboutMeField;
    private TextView interestField;
    private TextView hobbiesField;

    public PreviewProfileTab2Fragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_preview_profile_tab2, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new PreviewProfileController(this);
        controller.loadUserInfo();
        aboutMeField = view.findViewById(R.id.about_me_text);
        interestField = view.findViewById(R.id.interest_text);
        hobbiesField = view.findViewById(R.id.hobbies_text);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final String about_me = (String) data.get(Db.Keys.ABOUT_ME);
        final String interest = (String) data.get(Db.Keys.INTERESTS);
        final String hobbies = (String) data.get(Db.Keys.HOBBIES);

        aboutMeField.setText(about_me);
        interestField.setText(interest);
        hobbiesField.setText(hobbies);

        onResume();
    }

    @Override
    public void setFragment() {

    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}