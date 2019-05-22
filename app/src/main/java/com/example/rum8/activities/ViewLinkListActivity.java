package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashSet;
import java.util.Set;

public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore dbStore;
    private FirebaseAuth auth;
    private Set<String> linkListUidSet;
    private com.google.firebase.firestore.Query queryStore;
    private FirestoreRecyclerOptions<LinkListSingleLink> options;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        linkListUidSet = new HashSet<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        initController();
        initViews();
    }

    private void initViews() {
        //fetch matched link list
        controller.fetchLinkListUidsFromDB();

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

    private void initController() {
        controller = new ViewLinkListController(this);
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
}
