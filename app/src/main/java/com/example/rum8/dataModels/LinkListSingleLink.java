package com.example.rum8.dataModels;

import android.graphics.Bitmap;

public class LinkListSingleLink {
    private String firstName;
    private String lastName;
    private String uid;
    private Bitmap bitMap;

    public LinkListSingleLink(String firstName, String lastName, String uid, Bitmap bitmap) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
        this.bitMap = bitmap;
    }

    public String getfirst_name() {
        return firstName;
    }

    public String getlast_name() {
        return lastName;
    }

    public Bitmap getBitMap() {
        return this.bitMap;
    }

}
