package com.example.rum8.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.ProfileSettingsActivity;
import com.example.rum8.controllers.ProfilePicUploadController;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfilePicUploadControllerListener;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class ProfileSettingsGeneralInfoFragment extends Fragment implements ProfileSettingsControllerListener, ProfilePicUploadControllerListener {
    private final int PICK_IMAGE_REQUEST =71;

    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;
    private Spinner academicYearSpinner;
    private Spinner collegeSpinner;
    private Button buttonNext;
    private Button buttonUploadPic;
    private Button buttonSavePic;

    private ProfileSettingsController controller;
    private ProfilePicUploadController uploadController;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private Uri filePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);

        controller = new ProfileSettingsController(this);
        uploadController = new ProfilePicUploadController(this);

        //NAME FIELDS
        firstNameField = rootView.findViewById(R.id.general_info_first_name_field);
        lastNameField = rootView.findViewById(R.id.general_info_last_name_field);

        //FILLING THE GENDER SPINNER
        genderSpinner = rootView.findViewById(R.id.general_info_gender_spinner);
        final ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_gender_items, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        //FILLING THE ACADEMIC YEAR SPINNER
        academicYearSpinner = rootView.findViewById(R.id.general_info_academic_year_spinner);
        final ArrayAdapter<CharSequence> academicYearAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_academic_year_items, android.R.layout.simple_spinner_item);
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        academicYearSpinner.setAdapter(academicYearAdapter);

        //FILLING THE COLLEGE SPINNER
        collegeSpinner = rootView.findViewById(R.id.general_info_college_spinner);
        final ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ps_general_info_college_items, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(collegeAdapter);

        //FILLING THE BUTTON
        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);
        buttonUploadPic = rootView.findViewById(R.id.general_info_profile_image_upload_button);
        buttonSavePic = rootView.findViewById(R.id.general_info_profile_image_save_button);

        buttonNext.setOnClickListener(v -> {
            final Map<String, Object> userInfo = new HashMap<>();

            final String firstName = firstNameField.getText().toString();
            final String lastName = lastNameField.getText().toString();
            final String gender = genderSpinner.getSelectedItem().toString();
            final String year = academicYearSpinner.getSelectedItem().toString();
            final String college = collegeSpinner.getSelectedItem().toString();

            userInfo.put("first_name",firstName);
            userInfo.put("last_name", lastName);
            userInfo.put("gender", gender);
            userInfo.put("academic_year", year);
            userInfo.put("college", college);

            controller.onSubmit(userInfo);
        });

        buttonUploadPic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                uploadController.onSubmit();
            }
        });

        buttonUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        buttonSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        return rootView;
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    private void uploadImage() {

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        filePath =((ProfileSettingsActivity) getActivity()).getPath();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }


}
