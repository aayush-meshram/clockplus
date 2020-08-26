package com.myapp.clock;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimerFragment extends Fragment implements TimePickerDialog.OnTimeSetListener{

    public TextView mText;
    public ProgressBar mProgress;
    public Button mSet;
    public Button mStart;
    public long timeLeftInMillis;
    public CountDownTimer countDownTimer;
    public boolean timerRunning;
    public int hrs, min, sec;
    public boolean isItTimerPart = false;

    public TimerFragment()  {

    }

    public View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_timer, container, false);
        mText = view.findViewById(R.id.mText);
        mSet = view.findViewById(R.id.mSet);
        mStart = view.findViewById(R.id.mStart);
        mProgress = view.findViewById(R.id.progress_bar);

        mStart.setVisibility(View.GONE);

        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog = new MyDialog();
                dialog.setTargetFragment(this, SyncStateContract.Constants.DIALOG_REQUEST_CODE);
                dialog.show(getFragmentManager(), SyncStateContract.Constants.DIALOG);
                isItTimerPart = true;
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStart.setVisibility(View.GONE);
                startTimer();
            }
        });

        return view;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if(isItTimerPart) {
            timeLeftInMillis = i * 24 * 60 * 1000;
            timeLeftInMillis += i1 * 60 * 1000;
            String s = i + ":" + i1 + ":00";
            mText.setText(s);
            mSet.setVisibility(View.GONE);
            mStart.setVisibility(View.VISIBLE);
            Snackbar.make(view, "Press start to continue timer", Snackbar.LENGTH_SHORT);
        }
    }

    public void startTimer()    {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                hrs = (int) (timeLeftInMillis / 1000) / 60 / 24;
                min = (int) (timeLeftInMillis / 1000) / 60;
                sec = (int) (timeLeftInMillis / 1000) % 60;

                String fomattedText = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, min, sec);
                mText.setText(fomattedText);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "Timer is done!", Toast.LENGTH_SHORT).show();
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }
}