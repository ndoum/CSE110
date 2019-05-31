package com.example.rum8.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;

import java.util.Map;

public class MatchedFullViewTabFourFragment extends Fragment implements MatchedRoommateProfileControllerListener {
    View view;
    private Button emailButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_view_tab_four, container, false);
        emailButton = view.findViewById(R.id.contact_email_button);

        emailButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tempUser@gmail.com"});
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showMatchedInfo(Map<String, Object> data) {

    }

    @Override
    public void setMatchedUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}
