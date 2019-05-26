package com.example.rum8.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class ViewLinkListRecycleViewAdapter extends RecyclerView.Adapter<ViewLinkListRecycleViewAdapter.LinkListSingleLinkHolder> {

    private List<LinkListSingleLink> lLinks;


    public class LinkListSingleLinkHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView firstNameView;
        public Button button_link;
        
        public LinkListSingleLinkHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.view_link_list_single_link_imageview);
            firstNameView = (TextView) itemView.findViewById(R.id.view_link_list_single_link_textview);
        }
    }

    public ViewLinkListRecycleViewAdapter(List<LinkListSingleLink> lLinks){
        this.lLinks = lLinks;
    }

    @Override
    public ViewLinkListRecycleViewAdapter.LinkListSingleLinkHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view;
        view = inflater.inflate(R.layout.activity_view_link_list_single_link, parent, false);
        LinkListSingleLinkHolder linkHolder = new LinkListSingleLinkHolder(view);

        return linkHolder;
    }

    @Override
    public void onBindViewHolder(LinkListSingleLinkHolder linkHolder, int position){
        LinkListSingleLink link = lLinks.get(position);

        TextView textView = linkHolder.firstNameView;
        textView.setText(link.getfirst_name());

        //ImageView imageView = linkHolder.imageView;

    }

    @Override
    public int getItemCount(){
        return lLinks.size();
    }

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
/*
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
*/
    /*public ViewLinkListRecycleViewAdapter(Class<LinkListSingleLink> modelClass,
                                          int modelLayout,
                                          Class<LinkListSingleLinkHolder> viewHolderClass,
                                          FirebaseDatabase ref,
                                          Context context) {
        super(new FirebaseRecyclerOptions.Builder<LinkListSingleLink>().setIndexedQuery();
        this.context = context;
    }*/



}
