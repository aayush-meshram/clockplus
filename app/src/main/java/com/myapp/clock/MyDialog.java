package com.myapp.clock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    public int hours = 0;
    public int mins = 0;
    public int secs = 0;
    public EditText hh;
    public EditText mm;
    public EditText ss;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_frag, null);

        hh = view.findViewById(R.id.hours);
        mm = view.findViewById(R.id.minutes);
        ss = view.findViewById(R.id.seconds);

        builder.setView(view)
                .setTitle("ENTER TIME")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        hours = Integer.parseInt(hh.getText().toString());
                        mins = Integer.parseInt(mm.getText().toString());
                        secs = Integer.parseInt(ss.getText().toString());

                        if (secs >= 60 || secs < 0) {
                            Toast.makeText(getContext(), "Second's parameter is invalid", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (mins >= 60 || mins < 0) {
                            Toast.makeText(getContext(), "Minute's parameter is invalid", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        String s1 = hours+":"+mins+":"+secs;

                        TimerFragment timerFragment = new TimerFragment();
                        timerFragment.mText.setText("00:30:10");

                    }
                });
        return builder.create();
    }

}

