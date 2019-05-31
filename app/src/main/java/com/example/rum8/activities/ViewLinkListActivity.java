package com.example.rum8.activities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.adapters.ViewLinkListRecycleViewAdapter;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;

import java.util.ArrayList;

public class ViewLinkListActivity extends AppCompatActivity implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private ArrayList<LinkListSingleLink> links;
    private ViewLinkListRecycleViewAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initController();
        setContentView(R.layout.activity_view_link_list);
        controller.prepareLinks();
        initViews();
    }

    private void initViews() {
        links = new ArrayList<>();
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewLinkListRecycleViewAdapter();

    }

    @Override
    public ArrayList<LinkListSingleLink> getLinks() {
        return links;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(ViewLinkListActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDefaultImage() {

    }


    private void initController() {
        controller = new ViewLinkListController(this);
    }

    @Override
    public void addNewLink(LinkListSingleLink newLink) {
        if (newLink.getBitMap() == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.images);
            newLink.setBitMap(bitmap);
        }
        links.add(newLink);
    }

    @Override
    public void displayLinks(ArrayList<LinkListSingleLink> links) {
        adapter.setlLinks(links);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void goToProfileSettings() {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void goToViewLinkList() {

    }

    @Override
    public void goToAdvSettings() {

    }

}
