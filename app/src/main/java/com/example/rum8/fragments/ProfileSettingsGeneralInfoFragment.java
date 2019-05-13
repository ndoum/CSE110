package com.example.rum8.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.ProfileSettingsActivity;
import com.example.rum8.controllers.ProfileSettingsController;
import com.example.rum8.listeners.ProfileSettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ProfileSettingsGeneralInfoFragment extends Fragment implements ProfileSettingsControllerListener {

    private final static double ONE_HUNDRED_PERCENT = 100.0;
    private final static int MAX_SIZE = 180; // height of imageView
    private final static  String PROGRESS_TITLE= "Uploading...";
    private final static String FILE_PATH = "profile_pictures/";
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;
    private Spinner academicYearSpinner;
    private Spinner collegeSpinner;
    private Button buttonNext;
    private Button buttonChoosePic;
    private Button buttonUploadPic;
    private ProfileSettingsController controller;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile_settings_general_info, container, false);
/*
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

        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);
        buttonChoosePic = rootView.findViewById(R.id.general_info_profile_image_upload_button);
        buttonUploadPic = rootView.findViewById(R.id.general_info_profile_image_save_button);

        imageView = rootView.findViewById(R.id.general_info_profile_image_view);

        buttonNext.setOnClickListener(v -> {
            final Map<String, Object> userInfo = new HashMap<String, Object>() {{
                put("first_name", firstNameField.getText().toString());
                put("last_name", lastNameField.getText().toString());
                put("gender", genderSpinner.getSelectedItem().toString());
                put("academic_year", academicYearSpinner.getSelectedItem().toString());
                put("college", collegeSpinner.getSelectedItem().toString());
            }};

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
        */

        return rootView;
    }

    @Override
    public void onViewCreated (View rootView, Bundle savedInstanceState){
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

        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);
        buttonChoosePic = rootView.findViewById(R.id.general_info_profile_image_upload_button);
        buttonUploadPic = rootView.findViewById(R.id.general_info_profile_image_save_button);

        imageView = rootView.findViewById(R.id.general_info_profile_image_view);

        buttonNext.setOnClickListener(v -> {
            final Map<String, Object> userInfo = new HashMap<String, Object>() {{
                put("first_name", firstNameField.getText().toString());
                put("last_name", lastNameField.getText().toString());
                put("gender", genderSpinner.getSelectedItem().toString());
                put("academic_year", academicYearSpinner.getSelectedItem().toString());
                put("college", collegeSpinner.getSelectedItem().toString());
            }};

            controller.onSubmit(userInfo);
        });

        buttonChoosePic.setOnClickListener(v -> chooseImage());


        buttonUploadPic.setOnClickListener(v -> uploadImage());

    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        System.out.println("onResume is called");
        imageView.invalidate();
        super.onResume();
        // to show the picture after the user selected
        final View view = getView();
        imageView = view.findViewById(R.id.general_info_profile_image_view);
        Bitmap bitmap = ((ProfileSettingsActivity) getActivity()).getBitmap();
        if (bitmap != null) {
            bitmap = resize(bitmap, MAX_SIZE, MAX_SIZE);
            imageView.setImageBitmap(bitmap);
        }
    }

    private static Bitmap resize(final Bitmap image, final int maxWidth, final int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            return Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
        } else {
            return image;
        }
    }

    // helper function to choose image from user's device
    private void chooseImage() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
        onResume();
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
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        final String message = "Successfully uploaded";
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        final String message = "Network error";
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (ONE_HUNDRED_PERCENT*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                        final String message = "Uploaded " + (int)progress + "%";
                        progressDialog.setMessage(message);
                    });
        }
    }

    @Override
    public void showToast(final String message, final int toastLength) {
        Toast.makeText(getActivity(), message, toastLength).show();
    }

}
