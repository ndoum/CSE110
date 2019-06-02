package com.example.rum8.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rum8.R;
import com.example.rum8.activities.MatchedRoommateProfileActivity;
import com.example.rum8.dataModels.LinkListSingleLink;
import com.example.rum8.listeners.ViewLinkListControllerListener;

import java.util.ArrayList;
import java.util.List;

public class ViewLinkListRecycleViewAdapter extends RecyclerView.Adapter<ViewLinkListRecycleViewAdapter.LinkListSingleLinkHolder> implements ViewLinkListControllerListener {

    private List<LinkListSingleLink> lLinks;
    private View view;
    public static final String USER_ID_STRING = "passed_user_id";

    @Override
    public void goToProfileSettings() {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void goToViewLinkList() {

    }

    @Override
    public void goToSettings() {

    }

    @Override
    public void addNewLink(LinkListSingleLink link) {

    }

    @Override
    public void displayLinks(ArrayList<LinkListSingleLink> links) {

    }

    @Override
    public ArrayList<LinkListSingleLink> getLinks() {
        return null;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showDefaultImage() {

    }


    //View Holder for a LinkListSingleLink object
    public class LinkListSingleLinkHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameView;
        public TextView majorView;
        public LinearLayoutCompat linkClickable;

        public LinkListSingleLinkHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.view_link_list_single_link_imageview);
            nameView = itemView.findViewById(R.id.view_link_list_single_link_textview);
            majorView = itemView.findViewById(R.id.view_link_list_single_link_major_year_textview);
            linkClickable = itemView.findViewById(R.id.activity_view_link_list_single_link_linear_layout);
        }
    }

    public void setlLinks(List<LinkListSingleLink> lLinks) {
        this.lLinks = lLinks;
    }

    @Override
    public ViewLinkListRecycleViewAdapter.LinkListSingleLinkHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.activity_view_link_list_single_link, parent, false);
        final LinkListSingleLinkHolder linkHolder = new LinkListSingleLinkHolder(view);

        return linkHolder;
    }

    @Override
    public void onBindViewHolder(final LinkListSingleLinkHolder linkHolder, final int position) {
        LinkListSingleLink link = lLinks.get(position);

        final TextView firstNameView = linkHolder.nameView;
        firstNameView.setText(link.getfirst_name() + " " + link.getlast_name());

        final TextView majorView = linkHolder.majorView;
        majorView.setText(link.getMajor());

        final ImageView imageView = linkHolder.imageView;
        imageView.setImageBitmap(link.getBitMap());

        final LinearLayoutCompat clickable = linkHolder.linkClickable;
        clickable.setOnClickListener(v -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, MatchedRoommateProfileActivity.class);
            intent.putExtra(USER_ID_STRING, link.getUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lLinks.size();
    }
}
