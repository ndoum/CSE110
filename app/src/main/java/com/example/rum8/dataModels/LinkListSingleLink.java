package com.example.rum8.dataModels;

import android.media.Image;
import android.widget.ImageView;

public class LinkListSingleLink {

    private ImageView image;
    private String firstName;
    private String lastName;
    private String uid;
    //other info we want to show

    public LinkListSingleLink() { } //Needed for Firebase

    public LinkListSingleLink(ImageView image, String firstName, String lastName, String uid) {
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return this.image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

}
