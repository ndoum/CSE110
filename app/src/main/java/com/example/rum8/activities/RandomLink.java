package com.example.rum8.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rum8.R;
import com.example.rum8.controllers.RandomLinkController;
import com.example.rum8.listeners.RandomLinkControllerListener;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class RandomLink extends AppCompatActivity implements RandomLinkControllerListener {

    private RandomLinkController controller;
    private String randomUid;
    private ImageButton random_button_1;
    private ImageButton random_button_2;
    private ImageButton random_button_3;
    public static final String USER_ID_STRING = "passed_user_id";
    private ImageView closePopup;
    private Dialog dia;
    private Button surpriseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_link);
        EasyFlipView easyFlipView1 = (EasyFlipView) findViewById(R.id.easyFlipView1);
        EasyFlipView easyFlipView2 = (EasyFlipView) findViewById(R.id.easyFlipView2);
        EasyFlipView easyFlipView3 = (EasyFlipView) findViewById(R.id.easyFlipView3);
        dia = new Dialog(this);
        this.randomUid = "";
        initController();

        findViewById(R.id.front1).setOnClickListener(v -> {
            easyFlipView1.flipTheView();
            showPopup();

        });

        findViewById(R.id.front2).setOnClickListener(v -> {
            easyFlipView2.flipTheView();
            showPopup();

        });

        findViewById(R.id.front3).setOnClickListener(v -> {
            easyFlipView3.flipTheView();
            showPopup();

        });

        initViews();
    }


    public void showPopup() {
        dia.setContentView(R.layout.surprise_me);
        closePopup = (ImageView) dia.findViewById(R.id.close_popup_surprise);
        surpriseButton = (Button) dia.findViewById(R.id.surprise_button);
        dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dia.show();
        surpriseButton.setOnClickListener(v -> {
//go to the profile
        });
        closePopup.setOnClickListener(v -> {
            dia.dismiss();
        });
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
