package com.example.rum8.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.MatchedRoommateProfileActivity;
import com.example.rum8.controllers.MatchedRoommateProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.dialog.PhoneContactDialog;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;

import java.util.Map;

public class MatchedFullViewTabFourFragment extends Fragment implements MatchedRoommateProfileControllerListener {
    View view;
    private MatchedRoommateProfileController controller;

    private LinearLayout facebookLinearLayout;
    private LinearLayout phoneNumberLinearLayout;
    private LinearLayout snapchatLinearLayout;
    private LinearLayout emailLinearLayout;
    private TextView facebookTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;
    private TextView snapchatTextView;
    private String emailPassed;
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private Button popUp;
    public String matchedUserPhoneNumber;

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

        controller = new MatchedRoommateProfileController(this);
        controller.loadMatchUserContactInfo(((MatchedRoommateProfileActivity) getActivity()).getMatchedUserId());

        facebookLinearLayout.setClickable(true);
        facebookLinearLayout.setOnClickListener(v -> {
            String text = facebookTextView.getText().toString();
            ClipData clipData = ClipData.newPlainText("text", text );
            clipboardManager.setPrimaryClip(clipData);
            showToast("Facebook link: " + text + " copied to clipboard");

        });

        snapchatLinearLayout.setClickable(true);
        snapchatLinearLayout.setOnClickListener(v -> {
            String text = snapchatTextView.getText().toString();
            ClipData clipData = ClipData.newPlainText("text", text );
            clipboardManager.setPrimaryClip(clipData);
            showToast("Snapchat username: " + text + " copied to clipboard");

        });



        phoneNumberLinearLayout.setClickable(true);
        phoneNumberLinearLayout.setOnClickListener(v -> {
            openDialog();
        });
        emailLinearLayout.setClickable(true);


        emailLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailPassed});
            startActivity(intent);
        });




        return view;
    }


    public void openDialog(){
        PhoneContactDialog phoneContactDialog = new PhoneContactDialog();
        phoneContactDialog.show(getFragmentManager(), "pop up");

    }






    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMatchedInfo(Map<String, Object> data) {
        final String facebook = (String) data.get(Db.Keys.FACEBOOK);
        final String email = (String) data.get(Db.Keys.EMAIL);
        final String phoneNumber = (String) data.get(Db.Keys.PHONE_NUMBER);
        final String snapchat = (String) data.get(Db.Keys.SNAPCHAT);

        emailPassed = email;

        emailTextView.setText(email);


        if (facebook.equals("") && facebook.length() <= 0){
            facebookLinearLayout.setVisibility(View.GONE);
        }
        else{
            facebookTextView.setText(facebook);
        }

        if (phoneNumber.equals("") && phoneNumber.length() <= 0){
            phoneNumberLinearLayout.setVisibility(View.GONE);
        }
        else{
            phoneNumberTextView.setText(phoneNumber);
        }

        if (snapchat.equals("") && snapchat.length() <= 0){
            snapchatLinearLayout.setVisibility(View.GONE);
        }
        else{
            snapchatTextView.setText(snapchat);
        }


        onResume();

    }

    @Override
    public void setMatchedUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}
