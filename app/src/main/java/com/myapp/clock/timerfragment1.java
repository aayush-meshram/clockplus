package com.myapp.clock;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;
import java.util.Objects;

public class timerfragment1 extends Fragment {
    public EditText mSec;
    public TextView mTIME;
    public Button mButtonSelected;
    public CountDownTimer countDownTimer;
    public long timeLeftinMillis;
    int hrs, min ,sec;
    public ProgressBar mProgressBar;
    Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timerfrag1, container, false);
        mSec = view.findViewById(R.id.mSec);
        mTIME = view.findViewById(R.id.mTIME);
        mButtonSelected = view.findViewById(R.id.mButtonStart);
        mTIME.setVisibility(View.INVISIBLE);
        mProgressBar = view.findViewById(R.id.mProgress);
        mProgressBar.setVisibility(View.INVISIBLE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        mButtonSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSec.setVisibility(View.INVISIBLE);
                mTIME.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setMax(100);
                mButtonSelected.setVisibility(View.INVISIBLE);
                if (mSec.getText().toString().isEmpty())
                    return;
                else {
                    timeLeftinMillis = Long.parseLong(mSec.getText().toString()) * 1000;
                    final int orig = (int)timeLeftinMillis/1000;
                    countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeLeftinMillis = millisUntilFinished;
                            hrs = (int) (timeLeftinMillis / 1000) / 60 / 24;
                            min = (int) (timeLeftinMillis / 1000) / 60;
                            sec = (int) (timeLeftinMillis / 1000) % 60;
                            String s1 = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, min, sec);
                            int prog = (int)timeLeftinMillis/1000;
                            int percentage =(int) (prog/orig * 100);
                            mProgressBar.setProgress(100 - percentage, true);
                            mProgressBar.isShown();
                            mTIME.setText(s1);
                        }

                        @Override
                        public void onFinish() {
                            mSec.setVisibility(View.VISIBLE);
                            mButtonSelected.setVisibility(View.VISIBLE);
                            mTIME.setVisibility(View.INVISIBLE);
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mTIME.setText("00:00");
                            vibrator.vibrate(1000);
                            //Snackbar.make(Objects.requireNonNull(getView()),"TIME UP", Snackbar.LENGTH_SHORT);
                            Toast.makeText(getContext(), "Time is up!", Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                }
            }
        });

        return view;
    }

}
