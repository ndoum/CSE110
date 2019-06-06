package com.example.rum8.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RandomLinkController;
import com.example.rum8.listeners.RandomLinkControllerListener;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class RandomLink extends AppCompatActivity implements RandomLinkControllerListener {

    private RandomLinkController controller;
    private String randomUid;

    public static final String USER_ID_STRING = "passed_user_id";
    private EasyFlipView mYourFlipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_link);
        initController();
        this.randomUid = "";
        controller.getRandomLink();
        initController();
        initViews();
        mYourFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        mYourFlipView.flipTheView();
        Toast.makeText(RandomLink.this, "Front Card", Toast.LENGTH_SHORT).show();
        mYourFlipView.flipTheView();
    }

    private void initViews() {

        //mYourFlipView.flipTheView(false);
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
