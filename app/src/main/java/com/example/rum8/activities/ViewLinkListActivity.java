package com.example.rum8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.adapters.ViewLinkListRecycleViewAdapter;
import com.example.rum8.controllers.ViewLinkListController;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static android.content.ContentValues.TAG;


public class ViewLinkListActivity extends AppCompatActivity
        implements ViewLinkListControllerListener {

    private ViewLinkListController controller;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    //private Set<String> linkListUidSet;
    private ArrayList<LinkListSingleLink> links; //matched links
    private ViewLinkListRecycleViewAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_link_list);
        links = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        initController();
        initViews();
    }

    private void initViews() {
        controller.fetchLinkListUidsFromDB();
        System.out.println("FINISHED POPULATING LINK LIST CONTENT");

        //System.out.println("IN LINKS:...");
        //System.out.println(links);
        /*LinkListSingleLink test1 = new LinkListSingleLink("Tina", "Hsieh", "1234567890");
        //addNewLink(test1);
        links.add(test1);
        LinkListSingleLink test2 = new LinkListSingleLink("Oli", "Zhou", "0987654321");
        links.add(test2);*/
        /*
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        adapter = new ViewLinkListRecycleViewAdapter(links);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        */

        //ViewLinkListRecycleViewAdapter
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
    public void addNewLink(HashMap<String, Object> documentData, String uid) {
        System.out.println("creating linkListSingleLink Object for " + uid);
        String first_name = (String) documentData.get("first_name");
        String last_name = (String) documentData.get("last_name");
        LinkListSingleLink newLink = new LinkListSingleLink(first_name, last_name, uid);
        links.add(newLink);
    }

    @Override
    public void displayLinks() {
        recyclerView = findViewById(R.id.activity_view_link_list_recycler_view);
        adapter = new ViewLinkListRecycleViewAdapter(links);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void populateRecylcerViewContent(Set<String> uids){
        System.out.println("IN POPULATE RECYCLER VIEW CONTENT");
        for(String uid:uids){
            Db.fetchLinkInfo(db, uid).addOnFailureListener(e ->
                    Log.d(TAG, "Fetch matched list failed"))
                    .addOnSuccessListener(documentSnapshot -> {
                        HashMap<String, Object> uidMap = (HashMap<String, Object>) documentSnapshot.getData();
                        String name = (String) uidMap.get("first_name");
                        System.out.println("FUNCTION CALL TO ADDNEWLINK for: "+name);
                        addNewLink(uidMap, uid);
                    });
        }
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

    /*@Override
    public void addToLinks(LinkListSingleLink link) {
        //System.out.println("ADDING TO LINKS!!!!!");
        links.add(link);
        System.out.println("IN FUNCTION ADD TO LINKS...");
        System.out.println(links);
    }*/


}
