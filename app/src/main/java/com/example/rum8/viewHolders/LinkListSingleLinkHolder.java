package com.example.rum8.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;


public class LinkListSingleLinkHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView firstNameView;
    //id field?

    public LinkListSingleLinkHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.view_link_list_single_link_imageview);
        firstNameView = (TextView) itemView.findViewById(R.id.general_info_first_name_field);
    }

}
