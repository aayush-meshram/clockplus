package com.myapp.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassificationManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.nio.channels.CancelledKeyException;
import java.text.DateFormat;
import java.util.Calendar;

public class AlarmFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {


    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentHolder = inflater.inflate(R.layout.fragment_alarm, container, false);

        final TextView mText = parentHolder.findViewById(R.id.mText);
        final TextView mTime = parentHolder.findViewById(R.id.mTime);
        Button mButton = parentHolder.findViewById(R.id.mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String s1 = i + ":" + i1;

                        mTime.setText(s1);
                        mText.setText("Alarm Set!");

                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, i);
                        c.set(Calendar.MINUTE, i1);
                        c.set(Calendar.MILLISECOND, 0);

                        Toast.makeText(getContext(), "Alarm set for " + c, Toast.LENGTH_SHORT).show();
                    }
                });
                timePickerFragment.show(getFragmentManager(), "Time Picker");
            }
        });
        return parentHolder;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        c.set(Calendar.MILLISECOND, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}