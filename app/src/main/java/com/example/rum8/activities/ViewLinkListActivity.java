package com.example.rum8.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.example.rum8.viewHolders.LinkListSingleLinkHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;


public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore dbStore;
    private FirebaseDatabase db;
    private Query query;
    private com.google.firebase.firestore.Query queryStore;
    private FirestoreRecyclerOptions<LinkListSingleLink> options;
    private FirestoreRecyclerAdapter adapter;
    private LinkListSingleLinkHolder singleLinkHolder;
    private static int counter = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        initViews();
        initController();
    }

    private void initViews() {
        query = FirebaseDatabase.getInstance().getReference().child("users").limitToFirst(20);
        dbStore = FirebaseFirestore.getInstance();
        queryStore = dbStore.collection("users").limit(20);
        System.out.println(queryStore.toString());
        options = new FirestoreRecyclerOptions.Builder<LinkListSingleLink>()
                .setQuery(queryStore, LinkListSingleLink.class)
                .setLifecycleOwner(this)
                .build();
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        adapter = new FirestoreRecyclerAdapter<LinkListSingleLink, LinkListSingleLinkHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LinkListSingleLinkHolder linkHolder, int position, @NonNull LinkListSingleLink link) {
                //linkHolder.imageView.setImageDrawable(link.getImage().getDrawable());
                //linkHolder.firstName.setText(link.getfirst_name() + " " + link.getlast_name());
                //COULDNT FIGURE OUT HOW TO SET THE TEXT TO THE TEXTVIEW IN THE VIEW HOLDER
                System.out.println("\"" + counter++ + ". " + link.getfirst_name() + " " + link.getlast_name() + "\"");
            }

            @NonNull
            @Override
            public LinkListSingleLinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_link_list_single_link, parent, false);
                return new LinkListSingleLinkHolder(view);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void initController() {
        controller = new ViewLinkListController(this);
    }
}
