package com.myapp.clock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TimerFragment extends Fragment implements MyDialog.MyDialogListener {

    public ProgressBar progressBar;

    public TimerFragment() {
        //mText = getView().findViewById(R.id.timerProgress);
        // Required empty public constructor
    }

    public TextView mText;
    public Button buttonSelect;
    public Button update;

    MyDialog.MyDialogListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentHolder = inflater.inflate(R.layout.fragment_timer, container, false);
        // Inflate the layout for this fragment
        getArguments().getString("title", "Lorem Ipsum");
        progressBar = parentHolder.findViewById(R.id.progress_bar);
        progressBar.setProgress(5);
        mText = parentHolder.findViewById(R.id.timerProgress);
        buttonSelect = parentHolder.findViewById(R.id.mButtonSet);
        update = parentHolder.findViewById(R.id.mButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(10);
            }
        });
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
        myDialog.show(getFragmentManager(), "TIME PICKER");
    }

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        MyDialog myDialog = MyDialog.newInstance("Select Time");
        // SETS the target fragment for use later when sending results
        myDialog.setTargetFragment(TimerFragment.this, 300);
        myDialog.show(fm, "fragment_edit_name");

    }



    // This is called when the dialog is completed and the results have been passed

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(getContext(), "Hello there"+ inputText, Toast.LENGTH_SHORT).show();
    }

}