package com.example.rum8.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rum8.R;

public class potential_roommate_profile_default extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (container != null) {
            container.removeAllViews();
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_potential_roommate_profile_default, container, false);
    }


}
