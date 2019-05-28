package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.adapters.ViewLinkListRecycleViewAdapter;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;

    private ArrayList<LinkListSingleLink> links; //matched links
    private ViewLinkListRecycleViewAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initController();
        setContentView(R.layout.activity_view_link_list);
        db = FirebaseFirestore.getInstance();
        controller.prepareLinks();

        initViews();
    }

    private void initViews() {
        System.out.println("PREPARING LINKS");
        links = new ArrayList<>();
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewLinkListRecycleViewAdapter();

        final Button button_goToFullProfileView = findViewById(R.id.go_to_matched_roommate_full);
        button_goToFullProfileView.setOnClickListener(v -> controller.onGoToFullProfileButtonClicked());


    }

    @Override
    public ArrayList<LinkListSingleLink> getLinks(){
        return links;
    }

    private void initController() {
        controller = new ViewLinkListController(this);
    }

    @Override
    public void addNewLink(LinkListSingleLink newLink) {
        links.add(newLink);
    }

    @Override
    public void displayLinks(ArrayList<LinkListSingleLink> links) {
        System.out.println("Links in DISPLAY LINKS");
        System.out.println(links);

        adapter.setlLinks(links);
        recyclerView.setAdapter(adapter);
        System.out.println("DISPLAYED!!!!!");
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_go_to_profile_settings:
                controller.onGoToProfileSettingsButtonClicked();
                return true;
            case R.id.main_activity_log_out:
                controller.onLogoutButtonClicked();
                return true;
            case R.id.main_activity_go_to_view_link_list:
                controller.onGoToViewLinkListButtonClicked();
                return true;
            case R.id.main_activity_go_to_adv_settings:
                controller.onGoToAdvSettingsButtonClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //Creates the menu inside of the toolbar
        getMenuInflater().inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public void goToProfileSettings() {
        final Intent intent  = new Intent(ViewLinkListActivity.this, ProfileSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToLogin() {
        final Intent intent  = new Intent(ViewLinkListActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToViewLinkList() {
        //stays at current page
    }

    @Override
    public void goToAdvSettings() {
        final Intent intent  = new Intent(ViewLinkListActivity.this, AdvancedSettingsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void gotToFullProfile() {
        final Intent intent  = new Intent(ViewLinkListActivity.this, MatchedRoommateProfileActivity.class);
        startActivity(intent);
        finish();

    }

    /*@Override
    public void addToLinks(LinkListSingleLink link) {
        //System.out.println("ADDING TO LINKS!!!!!");
        links.add(link);
        System.out.println("IN FUNCTION ADD TO LINKS...");
        System.out.println(links);
    }*/




}
