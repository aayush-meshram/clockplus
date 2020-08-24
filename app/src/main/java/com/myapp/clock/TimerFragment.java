package com.myapp.clock;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TimerFragment extends Fragment {

    public ProgressBar progressBar;

    public TimerFragment() {
        // Required empty public constructor
    }

    public TextView mText;
    public Button buttonSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentHolder = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        progressBar = parentHolder.findViewById(R.id.progress_bar);
        mText = parentHolder.findViewById(R.id.timerProgress);
        buttonSelect = parentHolder.findViewById(R.id.mButtonSet);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        return parentHolder;
    }

    public void openDialog()   {
        MyDialog myDialog = new MyDialog();
        myDialog.show(getFragmentManager(), "TIMER DIALOG");
    }
}