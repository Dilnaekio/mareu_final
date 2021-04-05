package dfmareu.com.ui.create;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import dfmareu.com.R;
import dfmareu.com.base.BaseActivity;
import dfmareu.com.databinding.ActivityCreateReunionBinding;
import dfmareu.com.util.ReunionUtil;

public class CreateReunion extends BaseActivity {

    //Bundle
    public static final String NAVIGATIONparticipants = "dfmareu.com.Views.NAVIGATIONparticipants";
    public static final String NAVIGATIONsubject = "dfmareu.com.Views.NAVIGATIONsubject";
    public static final String NAVIGATIONroom = "dfmareu.com.Views.NAVIGATIONroom";
    public static final String NAVIGATIONday = "dfmareu.com.Views.NAVIGATIONday";
    public static final String NAVIGATIONmonth = "dfmareu.com.Views.NAVIGATIONmonth";
    public static final String NAVIGATIONyear = "dfmareu.com.Views.NAVIGATIONyear";
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
    ArrayList<String> mParticipantsList = new ArrayList<>();

    //Edits text to add subject and participants to the reunion
    EditText mSubject, mParticipants;

    //Rooms
    Spinner vSpinnerRooms;
    String[] mRooms;

    //Buttons for selecting date, time and accept a mail
    Button mSelectDate, mSelectTime, mAcceptParticipant;

    //Boolean to check if theses var are empty or not
    boolean listIsEmpty, hourIsEmpty, subjectIsEmpty = true;

    //Resources
    Resources resources;
    int redColor;
    Drawable redContour;
    ActivityCreateReunionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureBindingViews();
        configureResources();
        configureSpinner();
        configureRecyclerView();

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

        mAcceptParticipant.setOnClickListener(v -> {

            String participantMail = mParticipants.getText().toString();
            if (ReunionUtil.isMailValid(participantMail)) {
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

        Calendar calendar = Calendar.getInstance();

        mSelectDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            date = new DatePickerDialog(CreateReunion.this, (view1, year1, month1, day) -> mChosenDate.setText(getString(R.string.chosen_date, day, (month1 + 1), year1)), year, month, dayOfMonth);
            date.show();
        });

        mSelectTime.setOnClickListener(v -> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            time = new TimePickerDialog(CreateReunion.this, (view12, hourOfDay, minute) -> mChosenTime.setText(getString(R.string.chosen_hour, hourOfDay, minute)), hour, min, true);
            time.show();
        });

        binding.fabCancel.setOnClickListener(v -> finish());

        binding.fabValidate.setOnClickListener(v -> {
            Bundle ReunionInformations = new Bundle();
            Intent sendInfos = new Intent();
            String subject = mSubject.getText().toString();
            String spinner = (String) vSpinnerRooms.getSelectedItem();
            String hour = mChosenTime.getText().toString();

            try {
                day = date.getDatePicker().getDayOfMonth();
                if (subject.length() == 0) {
                    mSubject.setBackground(redContour);
                    subjectIsEmpty = true;
                } else {
                    subjectIsEmpty = false;
                }
                if (mParticipantsList.isEmpty()) {
                    mParticipants.setBackground(redContour);
                    mEmptyRecycler.setTextColor(redColor);
                    listIsEmpty = true;
                } else {
                    listIsEmpty = false;
                }
                if (hour.contains("Aucune heure")) {
                    mChosenTime.setText(R.string.Reunion_Error_TimeTxt);
                    mChosenTime.setTextColor(redColor);
                    mSelectTime.setBackgroundColor(redColor);
                    hourIsEmpty = true;
                } else {
                    hourIsEmpty = false;
                }
                if (!subjectIsEmpty && !listIsEmpty && !hourIsEmpty) {
                    ReunionInformations.putStringArrayList(NAVIGATIONparticipants, mParticipantsList);
                    ReunionInformations.putString(NAVIGATIONsubject, subject);
                    ReunionInformations.putString(NAVIGATIONroom, spinner);
                    ReunionInformations.putInt(NAVIGATIONday, day);
                    ReunionInformations.putInt(NAVIGATIONmonth, month);
                    ReunionInformations.putInt(NAVIGATIONyear, year);
                    ReunionInformations.putString(NAVIGATIONtime, hour);
                    sendInfos.putExtra(NAVIGATIONbundle, ReunionInformations);
                    setResult(Activity.RESULT_OK, sendInfos);
                    finish();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                mChosenDate.setText(R.string.Reunion_Error_DateTxt);
                mChosenDate.setTextColor(redColor);
                mSelectDate.setBackgroundColor(redColor);
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
        redColor = getResources().getColor(R.color.red);
        redContour = getResources().getDrawable(R.drawable.edittxt_color_contour);
    }

    private void configureSpinner() {
        mRooms = resources.getStringArray(R.array.ReunionRooms);
        ArrayAdapter<String> roomsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, mRooms);
        vSpinnerRooms.setAdapter(roomsAdapter);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.fabValidate.setSize(FloatingActionButton.SIZE_MINI);
            binding.fabCancel.setSize(FloatingActionButton.SIZE_MINI);

        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.fabValidate.setSize(FloatingActionButton.SIZE_NORMAL);
            binding.fabCancel.setSize(FloatingActionButton.SIZE_NORMAL);
        }
    }
}
