package com.example.rum8.controllers;

import com.example.rum8.listeners.ProfileSettingsControllerListener;

public class ProfileSettingsController {
    private ProfileSettingsControllerListener controllerListener;

    public ProfileSettingsController(final ProfileSettingsControllerListener controllerListener){
        this.controllerListener = controllerListener;
    }
}
