package com.myapp.clock;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class timerfragment1 extends Fragment {
    public EditText mSec;
    public TextView mTIME;
    public Button mButtonSelected;
    public CountDownTimer countDownTimer;
    public long timeLeftinMillis;
    int hrs, min ,sec;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timerfrag1, container, false);
        mSec = view.findViewById(R.id.mSec);
        mTIME = view.findViewById(R.id.mTIME);
        mButtonSelected = view.findViewById(R.id.mButtonStart);
        mTIME.setVisibility(View.INVISIBLE);

        mButtonSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSec.setVisibility(View.INVISIBLE);
                mTIME.setVisibility(View.VISIBLE);
                if (mSec.getText().toString().isEmpty())
                    return;
                else {
                    timeLeftinMillis = Long.parseLong(mSec.getText().toString()) * 1000;
                    countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeLeftinMillis = millisUntilFinished;
                            hrs = (int) (timeLeftinMillis / 1000) / 60 / 24;
                            min = (int) (timeLeftinMillis / 1000) / 60;
                            sec = (int) (timeLeftinMillis / 1000) % 60;
                            String s1 = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, min, sec);
                            mTIME.setText(s1);
                        }

                        @Override
                        public void onFinish() {
                            mSec.setVisibility(View.VISIBLE);
                            mButtonSelected.setVisibility(View.VISIBLE);
                            mTIME.setVisibility(View.INVISIBLE);
                            mTIME.setText("00:00");
                            Toast.makeText(getContext(), "DONE!", Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                }
            }
        });

        return view;
    }
}
