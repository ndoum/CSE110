package com.example.rum8.fragments;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

/**
 * Class that contains the preview profile tab four for preview
 * profile activity.
 */
public class PreviewProfileTab4Fragment extends Fragment implements PreviewProfileControllerListener {

    // Initialize class variable
    View view;
    private PreviewProfileController controller;

    private LinearLayout facebookLinearLayout;
    private LinearLayout phoneNumberLinearLayout;
    private LinearLayout snapchatLinearLayout;
    private LinearLayout emailLinearLayout;
    private TextView facebookTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;
    private TextView snapchatTextView;
    private String emailPassed;

    ClipboardManager clipboardManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_view_tab_four, container, false);

        facebookLinearLayout = view.findViewById(R.id.facebook_linear_layout);
        phoneNumberLinearLayout = view.findViewById(R.id.phone_number_linear_layout);
        phoneNumberTextView = view.findViewById(R.id.phone_number_text_view);
        facebookTextView = view.findViewById(R.id.facebook_text_view);
        emailTextView = view.findViewById(R.id.email_text_view);
        snapchatLinearLayout = view.findViewById(R.id.snapchat_linear_layout);
        snapchatTextView = view.findViewById(R.id.snapchat_text_view);
        emailLinearLayout = view.findViewById(R.id.email_linear_layout);

        clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        controller = new PreviewProfileController(this);
        controller.loadUserInfo();

        return view;
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showCurrentUserInfo(Map<String, Object> data) {
        final String facebook = (String) data.get(Db.Keys.FACEBOOK);
        final String email = (String) data.get(Db.Keys.EMAIL);
        final String phoneNumber = (String) data.get(Db.Keys.PHONE_NUMBER);
        final String snapchat = (String) data.get(Db.Keys.SNAPCHAT);

        emailPassed = email;

        emailTextView.setText(email);


        if (facebook.equals("") && facebook.length() <= 0) {
            facebookLinearLayout.setVisibility(View.GONE);
        } else {
            facebookTextView.setText(facebook);
        }

        if (phoneNumber.equals("") && phoneNumber.length() <= 0) {
            phoneNumberLinearLayout.setVisibility(View.GONE);
        } else {
            phoneNumberTextView.setText(phoneNumber);
        }

        if (snapchat.equals("") && snapchat.length() <= 0) {
            snapchatLinearLayout.setVisibility(View.GONE);
        } else {
            snapchatTextView.setText(snapchat);
        }


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