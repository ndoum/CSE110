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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.activities.ProfileSettingsActivity;
import com.example.rum8.activities.SettingsActivity;
import com.example.rum8.controllers.SettingsController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.SettingsControllerListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class SettingsGeneralFragment extends Fragment implements SettingsControllerListener {

    private final static int MAX_SIZE = 180; // height of imageView
    private final static String PROGRESS_TITLE = "Uploading...";
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private Spinner genderSpinner;
    private Spinner academicYearSpinner;
    private Spinner collegeSpinner;
    private ArrayAdapter<CharSequence> genderAdapter;
    private ArrayAdapter<CharSequence> collegeAdapter;
    private ArrayAdapter<CharSequence> academicYearAdapter;
    private Button buttonSave;
    private Button buttonNext;
    private Button buttonChoosePic;
    private Button buttonUploadPic;
    private SettingsController controller;
    private ImageView imageView;
    private ProgressDialog progressDialog;

    private static Bitmap resize(final Bitmap image, final int maxWidth, final int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            return Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
        } else {
            return image;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_settings_general, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new SettingsController(this);

        //NAME FIELDS
        firstNameField = rootView.findViewById(R.id.general_info_first_name_field);
        lastNameField = rootView.findViewById(R.id.general_info_last_name_field);
        controller.usernameEntered = firstNameField.getText().toString();
        controller.usernameEntered_lastName = lastNameField.getText().toString();

        //FILLING THE GENDER SPINNER
        genderSpinner = rootView.findViewById(R.id.general_info_gender_spinner);
        genderAdapter = ArrayAdapter.createFromResource(getActivity(),
            R.array.settings_gender_items, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        //FILLING THE ACADEMIC YEAR SPINNER
        academicYearSpinner = rootView.findViewById(R.id.general_info_academic_year_spinner);
        academicYearAdapter = ArrayAdapter.createFromResource(getActivity(),
            R.array.settings_academic_year_items, android.R.layout.simple_spinner_item);
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        academicYearSpinner.setAdapter(academicYearAdapter);

        //FILLING THE COLLEGE SPINNER
        collegeSpinner = rootView.findViewById(R.id.general_info_college_spinner);
        collegeAdapter = ArrayAdapter.createFromResource(getActivity(),
            R.array.settings_college_items, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(collegeAdapter);

        controller.loadUserInfo();

        progressDialog = new ProgressDialog(getActivity());
        imageView = rootView.findViewById(R.id.general_info_profile_image_view);

        // fetch user's profile picture
        controller.loadUserProfileImage();

        buttonNext = rootView.findViewById(R.id.general_info_profile_next_button);
        buttonNext.setOnClickListener(v -> {
            ((ProfileSettingsActivity) getActivity()).setViewPager(1);

        });

        buttonChoosePic = rootView.findViewById(R.id.general_info_profile_image_upload_button);
        buttonUploadPic = rootView.findViewById(R.id.general_info_profile_image_save_button);

        buttonSave = rootView.findViewById(R.id.general_info_profile_save_button);

        buttonSave.setOnClickListener(v -> {
            final Map<String, Object> userInfo = new HashMap<String, Object>() {{
                put(Db.Keys.FIRST_NAME, firstNameField.getText().toString());
                put(Db.Keys.LAST_NAME, lastNameField.getText().toString());
                put(Db.Keys.GENDER, genderSpinner.getSelectedItem().toString());
                put(Db.Keys.ACADEMIC_YEAR, academicYearSpinner.getSelectedItem().toString());
                put(Db.Keys.COLLEGE, collegeSpinner.getSelectedItem().toString());
            }};
            controller.onSaveButtonClicked(userInfo);
        });

        buttonChoosePic.setOnClickListener(v -> controller.onChooseImageCliked());

        buttonUploadPic.setOnClickListener(v -> controller.onUploadImageClicked(getFilePath()));

    }

    private Uri getFilePath() {
        return ((SettingsActivity) getActivity()).getFilePath();
    }

    @Override
    public void onResume() {
        imageView.invalidate();
        super.onResume();
        // to show the picture after the user selected
        final View view = getView();
        imageView = view.findViewById(R.id.general_info_profile_image_view);
        Bitmap bitmap = ((SettingsActivity) getActivity()).getBitmap();
        if (bitmap != null) {
            bitmap = resize(bitmap, MAX_SIZE, MAX_SIZE);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void chooseImage() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        onResume();
    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void showCurrentUserInfo(Map<String, Object> data) {
        final String userGender = (String) data.get(Db.Keys.GENDER);
        final String userYear = (String) data.get(Db.Keys.ACADEMIC_YEAR);
        final String userCollege = (String) data.get(Db.Keys.COLLEGE);
        final String userFirstName = (String) data.get(Db.Keys.FIRST_NAME);
        final String userLastName = (String) data.get(Db.Keys.LAST_NAME);

        genderSpinner.setSelection(genderAdapter.getPosition(userGender));
        academicYearSpinner.setSelection(academicYearAdapter.getPosition(userYear));
        collegeSpinner.setSelection(collegeAdapter.getPosition(userCollege));

        firstNameField.setText(userFirstName);
        lastNameField.setText(userLastName);
    }

    @Override
    public void showDefaultImage() {
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.images));
    }

    @Override
    public void showUploadImageProgress() {
        progressDialog.setTitle(PROGRESS_TITLE);
        progressDialog.show();
    }

    @Override
    public void hideUploadImageProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void updateUploadImagePercentage(double percentage) {
        final String message = "Uploaded " + (int) percentage + "%";
        progressDialog.setMessage(message);
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
