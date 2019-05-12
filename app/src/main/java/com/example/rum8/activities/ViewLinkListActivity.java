package com.example.rum8.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.example.rum8.viewHolders.LinkListSingleLinkHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private Query query;
    private FirebaseRecyclerOptions<LinkListSingleLink> options;
    private FirebaseRecyclerAdapter adapter;
    private LinkListSingleLinkHolder singleLinkHolder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        initViews();
        initController();
    }

    private void initViews() {
        query = db.getInstance().getReference().child("users").limitToFirst(20);
        options = new FirebaseRecyclerOptions.Builder<LinkListSingleLink>()
                .setQuery(query, LinkListSingleLink.class)
                .build();
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        adapter = new FirebaseRecyclerAdapter<LinkListSingleLink, LinkListSingleLinkHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LinkListSingleLinkHolder linkHolder, int i, @NonNull LinkListSingleLink link) {//holder, int, model
                linkHolder.imageView.setImageDrawable(link.getImage().getDrawable());
                linkHolder.firstName.setText(link.getFirstName());
            }

            @NonNull
            @Override
            public LinkListSingleLinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_link_list_single_link, parent, false);
                return new LinkListSingleLinkHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void initController() { controller = new ViewLinkListController(this);}
}
