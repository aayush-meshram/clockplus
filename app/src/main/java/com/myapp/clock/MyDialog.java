package com.myapp.clock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment implements TextView.OnEditorActionListener {
    public int hours = 0;
    public int mins = 0;
    public int secs = 0;
    public EditText hh;
    public EditText mm;
    public EditText ss;
    public long time_timer;

    public MyDialog()   {

    }

    public static MyDialog newInstance(String title)    {
        MyDialog myDialog = new MyDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        myDialog.setArguments(args);
        return myDialog;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_frag, container);
    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hh = (EditText) view.findViewById(R.id.hours);
        mm = (EditText) view.findViewById(R.id.minutes);
        ss = (EditText) view.findViewById(R.id.seconds);

        // Fetch arguments from bundle and set title

        assert getArguments() != null;
        String title = getArguments().getString("title", "Enter Name");

        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field

        hh.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // 2. Setup a callback when the "Done" button is pressed on keyboard
        hh.setOnEditorActionListener((TextView.OnEditorActionListener)this);
        mm.setOnEditorActionListener((TextView.OnEditorActionListener)this);
        ss.setOnEditorActionListener((TextView.OnEditorActionListener)this);

        hh.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            MyDialogListener listener = (MyDialogListener) getActivity();

            time_timer = Long.parseLong(hh.getText().toString()) * 60 * 60 * 1000;
            time_timer += Long.parseLong(mm.getText().toString()) * 60 * 1000;
            time_timer += Long.parseLong(ss.getText().toString()) * 1000;

            listener.onFinishEditDialog(String.valueOf(time_timer));

            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    public interface MyDialogListener {
        void onFinishEditDialog(String inputText);
    }

}

