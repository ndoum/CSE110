package com.example.rum8.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RandomLinkController;
import com.example.rum8.listeners.RandomLinkControllerListener;

public class RandomLink extends AppCompatActivity implements RandomLinkControllerListener {

    private RandomLinkController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_link);
        initController();
        initView();
    }

    private void initView() {
        controller.getRandomLink();
    }

    private void initController() {
        this.controller = new RandomLinkController(this);
    }
}
