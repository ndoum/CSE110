package com.example.rum8.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ProfileSettingsGeneralInfoFragment extends Fragment implements ProfileSettingsControllerListener {

    private final double ONE_HUNDRED_PERCENT = 100.0;
    private final String PROGRESS_TITLE= "Uploading...";
    private final String FILE_PATH = "profile_pictures/";
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;
    private Spinner academicYearSpinner;
    private Spinner collegeSpinner;
    private Button buttonNext;
    private Button buttonChoosePic;
    private Button buttonUploadPic;
    private ProfileSettingsController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);

        controller = new ProfileSettingsController(this);

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
        buttonChoosePic = rootView.findViewById(R.id.general_info_profile_image_upload_button);
        buttonUploadPic = rootView.findViewById(R.id.general_info_profile_image_save_button);

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

        buttonChoosePic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                chooseImage();
            }
        });


        buttonUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                uploadImage();
            }
        });

        return rootView;
    }

    // helper function to choose image from user's device
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
    }

    // helper function to upload chosen picture to firebase
    private void uploadImage() {

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageReference = storage.getReference();

        final Uri filePath = ((ProfileSettingsActivity) getActivity()).getFilePath();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle(PROGRESS_TITLE);
            progressDialog.show();

            final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

            StorageReference ref = storageReference.child(FILE_PATH+ userID);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            final String message = "Successfully uploaded";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            final String message = "Network error";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (ONE_HUNDRED_PERCENT*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            final String message = "Uploaded " + (int)progress + "%";
                            progressDialog.setMessage(message);
                        }
                    });
        }
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }

}
