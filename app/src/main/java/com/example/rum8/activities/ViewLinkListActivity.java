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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
                //link's profile image
                //linkHolder.imageView.setImageDrawable(link.getImage().getDrawable());


                FirebaseAuth auth = FirebaseAuth.getInstance();
                final FirebaseUser user = auth.getCurrentUser();
                final FirebaseStorage storage = FirebaseStorage.getInstance();

                controller.loadLinkProfileImage(storage, link.getUid()).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        //image.setImageBitmap(bmp);
                        Drawable draw = new BitmapDrawable(getResources(), bmp);
                        linkHolder.imageView.setImageDrawable(draw);
                    }
                }).addOnFailureListener(exception -> {
                    // fetch default if the user does not upload
                    controller.loadDefaultUserProfileImage(storage).addOnSuccessListener(bytes -> {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        //imageView.setImageBitmap(bmp);
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
                linkHolder.firstNameView.setText(link.getfirst_name() + " " + link.getlast_name());
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
