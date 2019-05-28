package com.example.rum8.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rum8.R;
import com.example.rum8.controllers.MainController;
import com.example.rum8.database.Db;
import com.example.rum8.listeners.MainControllerListener;

import java.util.Map;

public class UserTab3Fragment extends Fragment implements MainControllerListener {
    View view;

    private MainController controller;

    private TextView questionOneYesResponse;
    private TextView questionOneSometimesResponse;
    private TextView questionOneNoResponse;

    private TextView questionTwoYesResponse;
    private TextView questionTwoSometimesResponse;
    private TextView questionTwoNoResponse;

    private TextView questionThreeYesResponse;
    private TextView questionThreeSometimesResponse;
    private TextView questionThreeNoResponse;

    private TextView questionFourYesResponse;
    private TextView questionFourSometimesResponse;
    private TextView questionFourNoResponse;

    private TextView questionFiveYesResponse;
    private TextView questionFiveSometimesResponse;
    private TextView questionFiveNoResponse;

    private TextView questionSixYesResponse;
    private TextView questionSixSometimesResponse;
    private TextView questionSixNoResponse;

    private TextView questionSevenYesResponse;
    private TextView questionSevenSometimesResponse;
    private TextView questionSevenNoResponse;

    private TextView questionEightYesResponse;
    private TextView questionEightSometimesResponse;
    private TextView questionEightNoResponse;

    public UserTab3Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab3, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        controller = new MainController(this);
        controller.loadUserInfo();
       questionOneYesResponse = view.findViewById(R.id.tab3_question_1_yes);
        questionOneSometimesResponse = view.findViewById(R.id.tab3_question_1_sometimes);
        questionOneNoResponse = view.findViewById(R.id.tab3_question_1_no);

        questionTwoYesResponse = view.findViewById(R.id.tab3_question_2_yes);
        questionTwoSometimesResponse = view.findViewById(R.id.tab3_question_2_sometimes);
        questionTwoNoResponse = view.findViewById(R.id.tab3_question_2_no);

        questionThreeYesResponse = view.findViewById(R.id.tab3_question_3_yes);
        questionThreeSometimesResponse = view.findViewById(R.id.tab3_question_3_sometimes);
        questionThreeNoResponse = view.findViewById(R.id.tab3_question_3_no);

        questionFourYesResponse = view.findViewById(R.id.tab3_question_4_yes);
        questionFourSometimesResponse = view.findViewById(R.id.tab3_question_4_sometimes);
        questionFourNoResponse = view.findViewById(R.id.tab3_question_4_no);

        questionFiveYesResponse = view.findViewById(R.id.tab3_question_5_yes);
        questionFiveSometimesResponse = view.findViewById(R.id.tab3_question_5_sometimes);
        questionFiveNoResponse = view.findViewById(R.id.tab3_question_5_no);

        questionSixYesResponse = view.findViewById(R.id.tab3_question_6_yes);
        questionSixSometimesResponse = view.findViewById(R.id.tab3_question_6_sometimes);
        questionSixNoResponse = view.findViewById(R.id.tab3_question_6_no);

        questionSevenYesResponse = view.findViewById(R.id.tab3_question_7_yes);
        questionSevenSometimesResponse = view.findViewById(R.id.tab3_question_7_sometimes);
        questionSevenNoResponse = view.findViewById(R.id.tab3_question_7_no);

        questionEightYesResponse = view.findViewById(R.id.tab3_question_8_yes);
        questionEightSometimesResponse = view.findViewById(R.id.tab3_question_8_sometimes);
        questionEightNoResponse = view.findViewById(R.id.tab3_question_8_no);




    }

    @Override
    public void showCurrentUserInfo(final Map<String, Object> data) {
        final Long cleanVal = (Long) data.get(Db.Keys.CLEAN_VALUE);
        final Long reservedVal = (Long) data.get(Db.Keys.RESERVED_VALUE);
        final Long partyVal = (Long) data.get(Db.Keys.PARTY_VALUE);
        final Long alcoholVal = (Long) data.get(Db.Keys.ALCOHOL_VALUE);
        final Long smokeVal = (Long) data.get(Db.Keys.SMOKE_VALUE);
        final Long stayUpVal = (Long) data.get(Db.Keys.STAY_UP_LATE_ON_WEEKDAYS_VALUE);
        final Long guestsVal= (Long) data.get(Db.Keys.OVERNIGHT_GUESTS_VALUE);
        final Long petsVal = (Long) data.get(Db.Keys.ALLOW_PETS_VALUE);

        if (cleanVal == 1){
            questionOneYesResponse.setText("X");
            questionOneSometimesResponse.setText("");
            questionOneNoResponse.setText("");
        }
        else if (cleanVal == -1){
            questionOneYesResponse.setText("");
            questionOneSometimesResponse.setText("");
            questionOneNoResponse.setText("X");

        }
        else{
            questionOneYesResponse.setText("");
            questionOneSometimesResponse.setText("X");
            questionOneNoResponse.setText("");
        }

        if (reservedVal == 1){
            questionTwoYesResponse.setText("X");
            questionTwoSometimesResponse.setText("");
            questionTwoNoResponse.setText("");
        }
        else if (reservedVal == -1){
            questionTwoYesResponse.setText("");
            questionTwoSometimesResponse.setText("");
            questionTwoNoResponse.setText("X");

        }
        else{
            questionTwoYesResponse.setText("");
            questionTwoSometimesResponse.setText("X");
            questionTwoNoResponse.setText("");
        }
        if (partyVal == 1){
            questionThreeYesResponse.setText("X");
            questionThreeSometimesResponse.setText("");
            questionThreeNoResponse.setText("");
        }
        else if (partyVal == -1){
            questionThreeYesResponse.setText("");
            questionThreeSometimesResponse.setText("");
            questionThreeNoResponse.setText("X");

        }
        else{
            questionThreeYesResponse.setText("");
            questionThreeSometimesResponse.setText("X");
            questionThreeNoResponse.setText("");
        }
        if (alcoholVal == 1){
            questionFourYesResponse.setText("X");
            questionFourSometimesResponse.setText("");
            questionFourNoResponse.setText("");
        }
        else if (alcoholVal == -1){
            questionFourYesResponse.setText("");
            questionFourSometimesResponse.setText("");
            questionFourNoResponse.setText("X");

        }
        else{
            questionFourYesResponse.setText("");
            questionFourSometimesResponse.setText("X");
            questionFourNoResponse.setText("");
        }
        if (smokeVal == 1){
            questionFiveYesResponse.setText("X");
            questionFiveSometimesResponse.setText("");
            questionFiveNoResponse.setText("");
        }
        else if (smokeVal == -1){
            questionFiveYesResponse.setText("");
            questionFiveSometimesResponse.setText("");
            questionFiveNoResponse.setText("X");

        }
        else{
            questionFiveYesResponse.setText("");
            questionFiveSometimesResponse.setText("X");
            questionFiveNoResponse.setText("");
        }
        if (stayUpVal == 1){
            questionSixYesResponse.setText("X");
            questionSixSometimesResponse.setText("");
            questionSixNoResponse.setText("");
        }
        else if (stayUpVal == -1){
            questionSixYesResponse.setText("");
            questionSixSometimesResponse.setText("");
            questionSixNoResponse.setText("X");

        }
        else{
            questionSixYesResponse.setText("");
            questionSixSometimesResponse.setText("X");
            questionSixNoResponse.setText("");
        }
        if (guestsVal == 1){
            questionSevenYesResponse.setText("X");
            questionSevenSometimesResponse.setText("");
            questionSevenNoResponse.setText("");
        }
        else if (guestsVal == -1){
            questionSevenYesResponse.setText("");
            questionSevenSometimesResponse.setText("");
            questionSevenNoResponse.setText("X");

        }
        else{
            questionSevenYesResponse.setText("");
            questionSevenSometimesResponse.setText("X");
            questionSevenNoResponse.setText("");
        }
        if (petsVal == 1){
            questionEightYesResponse.setText("X");
            questionEightSometimesResponse.setText("");
            questionEightNoResponse.setText("");
        }
        else if (petsVal == -1){
            questionEightYesResponse.setText("");
            questionEightSometimesResponse.setText("");
            questionEightNoResponse.setText("X");

        }
        else{
            questionEightYesResponse.setText("");
            questionEightSometimesResponse.setText("X");
            questionEightNoResponse.setText("");
        }

        onResume();
    }

    @Override
    public void setFragment() {

    }

    @Override
    public void setFragmentEmpty() {

    }

    @Override
    public void setUserProfileImage(Bitmap bitmap) {

    }


    @Override
    public void showToast(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToProfileSettings() {
    }

    @Override
    public void goToLogin() {
    }

    @Override
    public void goToLinkList() {

    }

    @Override
    public void goToAdvSettings() {
    }

}
