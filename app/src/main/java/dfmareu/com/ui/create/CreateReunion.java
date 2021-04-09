package dfmareu.com.ui.create;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import dfmareu.com.R;
import dfmareu.com.base.BaseActivity;
import dfmareu.com.databinding.ActivityCreateReunionBinding;
import dfmareu.com.util.CheckReunionInformations;
import dfmareu.com.util.IsMailValid;

public class CreateReunion extends BaseActivity {

    //Bundle
    public static final String NAVIGATIONparticipants = "dfmareu.com.Views.NAVIGATIONparticipants";
    public static final String NAVIGATIONsubject = "dfmareu.com.Views.NAVIGATIONsubject";
    public static final String NAVIGATIONroom = "dfmareu.com.Views.NAVIGATIONroom";
    public static final String NAVIGATIONdate = "dfmareu.com.Views.NAVIGATIONdate";
    public static final String NAVIGATIONtime = "dfmareu.com.Views.NAVIGATIONtime";
    public static final String NAVIGATIONbundle = "dfmareu.com.Views.NAVIGATIONbundle";

    //TextViews for date and time picked by User
    TextView mChosenDate, mChosenTime, mEmptyRecycler;

    //Date/TimePickerDialog and var used for calendar
    DatePickerDialog date;
    TimePickerDialog time;
    int day, month, year;

    //RecyclerView guests list
    RecyclerView vGuestRecyclerView;
    final ArrayList<String> mParticipantsList = new ArrayList<>();

    //Edits text to add subject and participants to the reunion
    EditText mSubject, mParticipants;

    //Rooms
    Spinner vSpinnerRooms;
    String[] mRooms;

    //Buttons for selecting date, time and accept a mail
    Button mSelectDate, mSelectTime, mAcceptParticipant;

    //Resources
    Resources resources;
    int redColor;
    ActivityCreateReunionBinding binding;
    CheckReunionInformations checkReunionInformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureBindingViews();
        configureResources();
        configureSpinner();
        configureRecyclerView();

        //Button and listener to check if user are typing mails in the edit text. If not, the user can't click on the button
        mAcceptParticipant.setEnabled(false);
        mParticipants.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mAcceptParticipant.setEnabled(true);
            }
        });

        //Listener to check is mails entered by user are valid (ex : id@domain.com)
        mAcceptParticipant.setOnClickListener(v -> {
            String participantMail = mParticipants.getText().toString();
            if (IsMailValid.isMailValid(participantMail)) {
                mParticipantsList.add(participantMail);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CreateReunion.this);
                binding.activityCreateReunionRecyclerView.setLayoutManager(layoutManager);
                binding.activityCreateReunionRecyclerView.setAdapter(new CreateReunionAdapter(mParticipantsList));
                vGuestRecyclerView.setVisibility(View.VISIBLE);
                mEmptyRecycler.setVisibility(View.GONE);
                Toast.makeText(this, "Participant ajouté !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mail invalide !", Toast.LENGTH_SHORT).show();
            }
            mParticipants.getText().clear();
            mAcceptParticipant.setEnabled(false);
        });

        //Calendar is used to know date and time in PickersDialog
        Calendar calendar = Calendar.getInstance();
        //Button to choose date
        mSelectDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            date = new DatePickerDialog(CreateReunion.this, (view1, year1, month1, day) -> mChosenDate.setText(getString(R.string.chosen_date, day, (month1 + 1), year1)), year, month, dayOfMonth);
            date.show();
        });

        //Button to choose time
        mSelectTime.setOnClickListener(v -> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            time = new TimePickerDialog(CreateReunion.this, (view12, hourOfDay, minute) -> mChosenTime.setText(getString(R.string.chosen_hour, hourOfDay, minute)), hour, min, true);
            time.show();
        });

        //Cancel the creation
        binding.fabCancel.setOnClickListener(v -> finish());

        //Listener to validate if the new reunion is valid or not
        binding.fabValidate.setOnClickListener(v -> {
            Bundle ReunionInformations = new Bundle();
            Intent sendInfos = new Intent();
            String subject = mSubject.getText().toString();
            String spinner = (String) vSpinnerRooms.getSelectedItem();
            String hour = mChosenTime.getText().toString();
            checkReunionInformations = new CheckReunionInformations(subject, mParticipantsList, hour);
            //First, we must try to check if date had been chosen. Without try, we will get "Null Pointer Exception". We don't have this problem with time because it's a string
            try {
                day = date.getDatePicker().getDayOfMonth();
            } catch (NullPointerException e) {
                e.printStackTrace();
                mChosenDate.setText(R.string.Reunion_Error_DateTxt);
                mChosenDate.setTextColor(redColor);
            }
            //If all required informations are written, it will send theses to the main activity and finish the current activity
            if (checkReunionInformations.areInformationsCompleted()) {
                ReunionInformations.putStringArrayList(NAVIGATIONparticipants, mParticipantsList);
                ReunionInformations.putString(NAVIGATIONsubject, subject);
                ReunionInformations.putString(NAVIGATIONroom, spinner);
                //todo : je vais modifier l'envoie de date pour envoyer un string à la place => il faudra modifier Reunion + test
                ReunionInformations.putString(NAVIGATIONdate, (String) mChosenDate.getText());
                ReunionInformations.putString(NAVIGATIONtime, hour);
                sendInfos.putExtra(NAVIGATIONbundle, ReunionInformations);
                setResult(Activity.RESULT_OK, sendInfos);
                finish();
            } else {
                //If the informations are empty => set hint/text to red.
                if(checkReunionInformations.getNotHourEmpty()){
                    mChosenTime.setTextColor(redColor);
                }
                if(checkReunionInformations.getNotParticipantsEmpty()){
                    mParticipants.setHintTextColor(redColor);
                }
                if(checkReunionInformations.getNotSubjectEmpty()){
                    mSubject.setHintTextColor(redColor);
                }
            }
        });
    }

    protected void configureBindingViews() {
        binding = ActivityCreateReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        mChosenDate = binding.activityCreateReunionSelectedDate;
        mChosenTime = binding.activityCreateReunionSelectedTime;
        mEmptyRecycler = binding.activityCreateReunionTxtViewIfRecyclerEmpty;
        mSubject = binding.activityCreateReunionSubjectEditTxt;
        mParticipants = binding.activityCreateReunionParticipantEditTxt;
        vSpinnerRooms = binding.activityCreateReunionSpinnerRooms;
        mSelectDate = binding.activityCreateReunionDateBtn;
        mSelectTime = binding.activityCreateReunionTimeBtn;
        mAcceptParticipant = binding.activityCreateReunionParticipantsAcceptBtn;
        vGuestRecyclerView = binding.activityCreateReunionRecyclerView;
    }

    private void configureRecyclerView() {
        vGuestRecyclerView.setVisibility(View.GONE);
        mEmptyRecycler.setVisibility(View.VISIBLE);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void configureResources() {
        resources = getResources();
        redColor = resources.getColor(R.color.red);
    }

    private void configureSpinner() {
        mRooms = resources.getStringArray(R.array.ReunionRooms);
        ArrayAdapter<String> roomsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mRooms);
        vSpinnerRooms.setAdapter(roomsAdapter);
    }

    //If we must disabled "portrait" in the manifest, we already have this onConfigurationChanged method set up to clean data
    @Override
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        vSpinnerRooms.setSelection(0);
        mSubject.setText("");
        mChosenDate.setText(R.string.No_Date_Selected);
        mChosenTime.setText(R.string.No_Time_Selected);
        int size = mParticipantsList.size();
        if (size > 0) {
            mParticipantsList.subList(0, size).clear();
            if (mParticipantsList.isEmpty()) {
                vGuestRecyclerView.setVisibility(View.GONE);
                mEmptyRecycler.setVisibility(View.VISIBLE);
            }
        }
    }
}
