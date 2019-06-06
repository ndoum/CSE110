package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RandomLinkController;
import com.example.rum8.listeners.RandomLinkControllerListener;

public class RandomLink extends AppCompatActivity implements RandomLinkControllerListener {

    private RandomLinkController controller;
    private String randomUid;
    private ImageButton random_button_1;
    private ImageButton random_button_2;
    private ImageButton random_button_3;
    public static final String USER_ID_STRING = "passed_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_link);
        this.randomUid = "";
        initController();
        initViews();
    }

    private void initViews() {
    }

    private void initController() {
        this.controller = new RandomLinkController(this);
    }

    @Override
    public void setRandomUid(String randomUid) {
        this.randomUid = randomUid;
    }

    @Override
    public String getRandomUid() {
        return this.randomUid;
    }
}
