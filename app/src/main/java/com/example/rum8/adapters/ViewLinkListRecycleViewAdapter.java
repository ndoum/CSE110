package com.example.rum8.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.activities.ViewLinkListActivity;
import com.example.rum8.dataModels.LinkListSingleLink;

import java.util.List;

public class ViewLinkListRecycleViewAdapter extends RecyclerView.Adapter<ViewLinkListRecycleViewAdapter.LinkListSingleLinkHolder> {

    private List<LinkListSingleLink> lLinks;
    ViewLinkListActivity activity;

    //View Holder for a LinkListSingleLink object
    public class LinkListSingleLinkHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView nameView;

        public LinkListSingleLinkHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.view_link_list_single_link_imageview);
            nameView = itemView.findViewById(R.id.view_link_list_single_link_textview);
        }
    }

    public ViewLinkListRecycleViewAdapter(){}

    public void setlLinks(List<LinkListSingleLink> lLinks){ this.lLinks = lLinks;}
    public void setActivity(ViewLinkListActivity activity){this.activity = activity;}

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

        TextView firstNameView = linkHolder.nameView;
        firstNameView.setText(link.getfirst_name()+" "+link.getlast_name());

        ImageView imageView = linkHolder.imageView;
        imageView.setImageBitmap(link.getBitMap());
    }

    @Override
    public int getItemCount(){
        return lLinks.size();
    }
}
