package com.example.rum8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;

import java.util.Map;

public class UserTab1Fragment extends Fragment implements MainControllerListener {

    View view;
    public UserTab1Fragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_tab1, container, false);

        return view;
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final String about_me = (String) data.get(Db.Keys.ABOUT_ME);
        final String hobbies = (String) data.get(Db.Keys.HOBBIES);
        final String interests = (String) data.get(Db.Keys.INTERESTS);
        final String living_accommodations = (String) data.get(Db.Keys.LIVING_ACCOMMODATIONS);
        final String other_things_you_should_know = (String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW);
        final String phone_number = (String) data.get(Db.Keys.PHONE_NUMBER);
    }

    @Override
    public void showToast(final String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToProfileSettings(){}

    @Override
    public void goToLogin(){}

    @Override
    public void goToAdvSettings(){}

}
