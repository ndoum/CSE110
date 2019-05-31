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
import com.example.rum8.controllers.MainController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;

import java.util.Map;

public class UserTab2Fragment extends Fragment implements MainControllerListener{

    private View view;
    private MainController controller;
    private TextView aboutMeField;
    private TextView interestField;
    private TextView hobbiesField;

    public UserTab2Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab2, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new MainController(this);
        controller.loadUserInfo();
        aboutMeField = view.findViewById(R.id.about_me_text);
        interestField = view.findViewById(R.id.interest_text);
        hobbiesField = view.findViewById(R.id.hobbies_text);
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
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void goToProfileSettings() {
    }

    @Override
    public void goToLogin() {
    }

    @Override
    public void goToLinkList() {

    }

    @Override
    public void goToAdvancedProfileSettings() {
    }

    @Override
    public void setFragment() {

    }

    @Override
    public void setFragmentEmpty() {

    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showPopup() {}

}
