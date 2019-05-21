package com.example.rum8.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
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
/*
    private Context context;
    private FirebaseRecyclerOptions<LinkListSingleLink> options;
    private Map<String, Object> users;

    public class LinkListSingleLinkHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView firstName;
        //id field?

        public LinkListSingleLinkHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.view_link_list_single_link_imageview);
            firstName = itemView.findViewById(R.id.general_info_first_name_field);
        }
    }


    public ViewLinkListRecycleViewAdapter(Map<String, Object> users){
        this.users = users;
    }
*/
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
