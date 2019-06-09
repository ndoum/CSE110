package com.example.rum8.listeners;

import com.example.rum8.dataModels.LinkListSingleLink;

import java.util.ArrayList;

/**
 * Interface for view link list controller
 */
public interface ViewLinkListControllerListener {
    void goToProfileSettings();

    void goToLogin();

    void goToViewLinkList();

    void goToSettings();


    void addNewLink(LinkListSingleLink link);

    void displayLinks(ArrayList<LinkListSingleLink> links);

    ArrayList<LinkListSingleLink> getLinks();

    void showToast(final String message);

    void showDefaultImage();

}
