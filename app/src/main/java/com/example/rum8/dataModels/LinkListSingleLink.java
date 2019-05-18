package com.example.rum8.dataModels;

public class LinkListSingleLink {
    private String firstName;
    private String lastName;
    private String uid;

    public LinkListSingleLink() {
    } //Needed for Firebase

    public LinkListSingleLink(/*ImageView image,*/ String firstName, String lastName, String uid) {
        //this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
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

}
