package com.myapp.clock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class StopwatchFragment extends Fragment {


    public StopwatchFragment() {
        // Required empty public constructor
    }

    public Button mStart;
    public Button mStop;
    public Button mPause;
    public Chronometer mChronometer;

    private long pause;
    private boolean running;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        mStart = view.findViewById(R.id.start);
        mStop = view.findViewById(R.id.stop);
        mPause = view.findViewById(R.id.pause);
        mChronometer = view.findViewById(R.id.mChronometer);
        mChronometer.setFormat("Time: %s");
        mChronometer.setBase(SystemClock.elapsedRealtime());

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChronometer(view);
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetChronometer(view);
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseChronometer(view);
            }
        });

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });

        return view;
    }

    public void startChronometer(View v)    {
        if (!running) {
            mChronometer.setBase(SystemClock.elapsedRealtime() - pause);
            mChronometer.start();
            running = true;
            mStart.setVisibility(View.INVISIBLE);
        }
    }

    public void pauseChronometer(View v)     {
        if (running) {
            mChronometer.stop();
            pause = SystemClock.elapsedRealtime() - mChronometer.getBase();
            running = false;
            mStart.setVisibility(View.VISIBLE);
        }
    }

    public void resetChronometer(View v)    {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        pause = 0;
        mStart.setVisibility(View.VISIBLE);
    }

}