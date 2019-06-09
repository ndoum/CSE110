package com.example.rum8.dialog;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rum8.R;
import com.example.rum8.activities.MatchedRoommateProfileActivity;
import com.example.rum8.controllers.MatchedRoommateProfileController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MatchedRoommateProfileControllerListener;

import java.util.Map;

/**
 * Class that create a popUp view for user to send message to matched user.
 */
public class PhoneContactDialog extends AppCompatDialogFragment implements MatchedRoommateProfileControllerListener {

    // Initialize class variable
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private Button sendMsgButton;
    private Button closePopupButton;
    private EditText messageContent;
    private MatchedRoommateProfileController controller;
    private TextView phoneNumberTextView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.text_message_pop_up, null);
        messageContent = view.findViewById(R.id.pop_up_message_content);
        phoneNumberTextView = view.findViewById(R.id.phone_number_text_view);
        sendMsgButton = view.findViewById(R.id.pop_up_send_message_button);
        closePopupButton = view.findViewById(R.id.pop_up_close_button);
        controller = new MatchedRoommateProfileController(this);
        controller.loadMatchUserContactInfo(((MatchedRoommateProfileActivity) getActivity()).getMatchedUserId());

        builder.setView(view).setTitle("Text Message");

        closePopupButton.setOnClickListener(v -> dismiss());

        sendMsgButton.setEnabled(true);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            sendMsgButton.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.SEND_SMS },
                    SEND_SMS_PERMISSION_REQUEST_CODE);

        }

        sendMsgButton.setOnClickListener(v -> {
            String smsMessage = messageContent.getText().toString();
            String phoneNumber = phoneNumberTextView.getText().toString();
            if (!TextUtils.isEmpty(smsMessage) && !TextUtils.isEmpty(phoneNumber)) {
                if (checkPermission(Manifest.permission.SEND_SMS)) {
                    String SENT = "Message Sent";
                    String DELIVERED = "Message Delivered";
                    PendingIntent sentPI = PendingIntent.getBroadcast(getContext(), 0, new Intent(SENT), 0);
                    PendingIntent deliveredPI = PendingIntent.getBroadcast(getContext(), 0, new Intent(DELIVERED), 0);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+1" + phoneNumber, null, smsMessage, sentPI, deliveredPI);
                    showToast("Text message sent");
                    dismiss();
                } else {
                    showToast("Failed Permission denied");
                }
            } else {
                showToast("Eneter a valid message");
            }

        });

        return builder.create();

    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission((getActivity()), permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
        case SEND_SMS_PERMISSION_REQUEST_CODE:
            if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                sendMsgButton.setEnabled(true);
            }
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMatchedInfo(Map<String, Object> data) {
        final String phoneNumberString = (String) data.get(Db.Keys.PHONE_NUMBER);
        phoneNumberTextView.setText(phoneNumberString);
    }

    @Override
    public void setMatchedUserProfileImage(Bitmap bitmap) {

    }

    @Override
    public void showDefaultImage() {

    }
}
