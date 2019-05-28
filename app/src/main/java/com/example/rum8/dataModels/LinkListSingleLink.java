package com.example.rum8.dataModels;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class LinkListSingleLink {
    private String firstName;
    private String lastName;
    private String uid;
    private ImageView imageView;
    private Bitmap bitMap;

    public LinkListSingleLink() {
    } //Needed for Firebase

    public LinkListSingleLink(/*ImageView imageView, */String firstName, String lastName, String uid, Bitmap bitmap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
        //this.imageView= imageView;
        this.bitMap = bitmap;
    }

    public void setfirst_name(String firstName) {
        this.firstName = firstName;
    }

    public String getfirst_name() {
        return firstName;
    }

    public void setlast_name(String lastName) {
        this.lastName = lastName;
    }

    public String getlast_name() {
        return lastName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setImageView(ImageView imageView){ this.imageView = imageView; }

    public ImageView getImage_view(){ return this.imageView; }

    public void setBitMap(Bitmap bitmap){ this.bitMap = bitmap; }

    public Bitmap getBitMap(){ return this.bitMap; }

}
