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
import com.example.rum8.activities.MatchedRoommateProfileActivity;
import com.example.rum8.controllers.MatchedRoommateProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;

import java.util.Map;

public class MatchedFullViewTabOneFragment extends Fragment implements MatchedRoommateProfileControllerListener {

    private View view;
    private MatchedRoommateProfileController matchedController;
    private TextView budgetField;
    private TextView livingAccommodationsField;
    private TextView otherThingsField;
    private TextView roomTypeField;

    public MatchedFullViewTabOneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_view_tab_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        matchedController = new MatchedRoommateProfileController(this);
        matchedController.loadMatchUserInfo(((MatchedRoommateProfileActivity) getActivity()).getMatchedUserId());
        livingAccommodationsField = view.findViewById(R.id.living_accommodations_text);
        otherThingsField = view.findViewById(R.id.other_thing_text);
        roomTypeField = view.findViewById(R.id.room_type_text);
        budgetField = view.findViewById(R.id.budget_text);
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMatchedInfo(Map<String, Object> data) {
        final String budgetMin = (String) data.get(Db.Keys.BUDGET_MIN);
        final String budgetMax = (String) data.get(Db.Keys.BUDGET_MAX);
        final String room_type = (String) data.get(Db.Keys.ROOM_TYPE);

        final String living_accommodations = (String) data.get(Db.Keys.LIVING_ACCOMMODATIONS);
        final String other_things_you_should_know = (String) data.get(Db.Keys.OTHER_THINGS_YOU_SHOULD_KNOW);

        budgetField.setText("$" + budgetMin + " - " + budgetMax);
        livingAccommodationsField.setText(living_accommodations);
        otherThingsField.setText(other_things_you_should_know);
        roomTypeField.setText(room_type);

        onResume();
    }

    @Override
    public void setMatchedUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}
