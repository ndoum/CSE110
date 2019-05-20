package com.example.rum8.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import static android.content.ContentValues.TAG;
public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore dbStore;
    private FirebaseAuth auth;
    private Map<String, Object> potentialMap;
    private com.google.firebase.firestore.Query queryStore;
    private FirestoreRecyclerOptions<LinkListSingleLink> options;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        initViews();
        initController();
    }

    private void initViews() {
        dbStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        //fetch user info
        Db.fetchUserInfo(dbStore, auth.getCurrentUser()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error loading user info");
            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                potentialMap = (Map<String, Object>) documentSnapshot.get("potential");
                System.out.println("Getting potential links...");
                System.out.println(potentialMap.keySet());
            }
        });









        //fetch all users from firestore (need to get from the match group later)
        /*queryStore = dbStore.collection("users");

        //build single links
        options = new FirestoreRecyclerOptions.Builder<LinkListSingleLink>()
                .setQuery(queryStore, LinkListSingleLink.class)
                .setLifecycleOwner(this)
                .build();

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
*/
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    private void initController() {
        controller = new ViewLinkListController(this);
    }
}
