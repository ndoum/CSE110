package com.example.rum8.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.viewHolders.LinkListSingleLinkHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

//DONT KNOW WHAT IM DOING HERE
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ViewLinkListRecycleViewAdapter extends FirebaseRecyclerAdapter<LinkListSingleLink, LinkListSingleLinkHolder> {

    private Context context;
    private FirebaseRecyclerOptions<LinkListSingleLink> options;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewLinkListRecycleViewAdapter(@NonNull FirebaseRecyclerOptions<LinkListSingleLink> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LinkListSingleLinkHolder linkListSingleLinkHolder, int i, @NonNull LinkListSingleLink linkListSingleLink) {

    }

    @NonNull
    @Override
    public LinkListSingleLinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    /*public ViewLinkListRecycleViewAdapter(Class<LinkListSingleLink> modelClass,
                                          int modelLayout,
                                          Class<LinkListSingleLinkHolder> viewHolderClass,
                                          FirebaseDatabase ref,
                                          Context context) {
        super(new FirebaseRecyclerOptions.Builder<LinkListSingleLink>().setIndexedQuery();
        this.context = context;
    }*/



}
