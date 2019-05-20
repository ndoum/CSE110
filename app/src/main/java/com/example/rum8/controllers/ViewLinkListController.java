package com.example.rum8.controllers;

import com.example.rum8.database.Db;
import com.example.rum8.listeners.ViewLinkListControllerListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;

public class ViewLinkListController {

    private ViewLinkListControllerListener controllerListener;



    public ViewLinkListController(final ViewLinkListControllerListener controllerListener) {
        this.controllerListener = controllerListener;
    }

    public void onGoToProfileSettingsButtonClicked(){ controllerListener.goToProfileSettings();};
    public void onLogoutButtonClicked(){ controllerListener.goToLogin();}
    public void onGoToViewLinkListButtonClicked(){ controllerListener.goToViewLinkList();}
    public void onGoToAdvSettingsButtonClicked(){ controllerListener.goToAdvSettings();}

    public Task<byte[]> loadDefaultUserProfileImage(final FirebaseStorage storage){
        return Db.fetchDefaultUserProfilePicture(storage);
    }

    public Task<byte[]> loadLinkProfileImage(final FirebaseStorage storage, final String linkUid){
        return Db.fetchLinkProfilePicture(storage, linkUid);
    }

}
