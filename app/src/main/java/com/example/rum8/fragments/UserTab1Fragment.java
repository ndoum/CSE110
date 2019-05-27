package com.example.rum8.fragments;

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

import java.util.Map;

public class UserTab1Fragment extends Fragment implements MainControllerListener {

    private View view;
    private MainController controller;
    private TextView budgetField;
    private TextView livingAccommodationsField;
    private TextView otherThingsField;

    public UserTab1Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab1, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new MainController(this);
        controller.loadUserInfo();
        budgetField = view.findViewById(R.id.budget_text);
        livingAccommodationsField = view.findViewById(R.id.living_accommodations_text);
        otherThingsField = view.findViewById(R.id.other_thing_text);
    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final long budget = (long) data.get(Db.Keys.BUDGET);
        final String living_accommodations = (String) data.get(Db.Keys.LIVING_ACCOMMODATIONS);
        final String other_things_you_should_know = (String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW);

        budgetField.setText(String.valueOf(budget));
        livingAccommodationsField.setText(living_accommodations);
        otherThingsField.setText(other_things_you_should_know);

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
    public void goToAdvSettings() {
    }

}
