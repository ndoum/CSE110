package com.example.rum8.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;

public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore dbStore;
    private com.google.firebase.firestore.Query queryStore;
    private FirestoreRecyclerOptions<LinkListSingleLink> options;
    private FirestoreRecyclerAdapter adapter;
    private int counter = 1, imagecounter=1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        initViews();
        initController();
    }

    private void initViews() {
        dbStore = FirebaseFirestore.getInstance();

        //fetch all users from firestore (need to get from the match group later)
        queryStore = dbStore.collection("users");

        //build single links
        options = new FirestoreRecyclerOptions.Builder<LinkListSingleLink>()
                .setQuery(queryStore, LinkListSingleLink.class)
                .setLifecycleOwner(this)
                .build();
        System.out.print("OPTIONS CREATED");
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);

        adapter = new FirestoreRecyclerAdapter<LinkListSingleLink, LinkListSingleLinkHolder>(options) {
            @Override
            //fetch info of link to display
            protected void onBindViewHolder(@NonNull LinkListSingleLinkHolder linkHolder, int position, @NonNull LinkListSingleLink link) {

                final FirebaseStorage storage = FirebaseStorage.getInstance();
                // fetch link's image
                String imageUid = "";
                if(link.getUid() != null) {
                    imageUid = link.getUid();
                }
                controller.loadLinkProfileImage(storage, imageUid).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        Drawable draw = new BitmapDrawable(getResources(), bmp);
                        linkHolder.imageView.setImageDrawable(draw);
                    }
                }).addOnFailureListener(exception -> {
                    // fetch default if the user does not upload
                    controller.loadDefaultUserProfileImage(storage).addOnSuccessListener(bytes -> {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        Drawable draw = new BitmapDrawable(getResources(), bmp);
                        linkHolder.imageView.setImageDrawable(draw);
                    });
                    // show error message if both way fails
                    int errorCode = ((StorageException) exception).getErrorCode();
                    if (errorCode != StorageException.ERROR_OBJECT_NOT_FOUND) {
                        final String message = "Network error";
                        Toast.makeText(ViewLinkListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

                //link's first name and last name
                linkHolder.setFirstNameViewText(link.getfirst_name() + " " + link.getlast_name());
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
