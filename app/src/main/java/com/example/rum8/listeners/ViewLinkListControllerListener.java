package com.example.rum8.listeners;

import java.util.HashMap;
import java.util.Set;

public interface ViewLinkListControllerListener {

    void goToProfileSettings();
    void goToLogin();
    void goToViewLinkList();
    void goToAdvSettings();
    void populateRecylcerViewContent(Set<String> uids);
    void addNewLink(HashMap<String, Object> documentData, String uid);
}
