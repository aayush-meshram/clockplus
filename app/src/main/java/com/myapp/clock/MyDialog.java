package com.myapp.clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by User on 12/10/2017.
 */

public class MyDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";
    private static final String MY_VALUE = "SET TIME";
    public static final int DIALOG_REQUEST_CODE = 1;

    public interface ISelectedData {
        void onSelectedData(String string);
    }
    public ISelectedData mOnInputSelected;

    //widgets
    private EditText hh, mm ,ss;
    private TextView mActionOk, mActionCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_frag, null))
                // Add action buttons
                .setPositiveButton("OK",  (DialogInterface.OnClickListener)getContext())
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener)getContext());
        return builder.create();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_frag, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        mActionCancel = view.findViewById(R.id.action_cancel);
        hh = view.findViewById(R.id.hours);
        mm = view.findViewById(R.id.minutes);
        ss = view.findViewById(R.id.seconds);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");
                long timer = Long.parseLong(hh.getText().toString()) * 60 * 60 * 1000;
                timer += Long.parseLong(mm.getText().toString()) * 60 * 1000;
                timer += Long.parseLong(ss.getText().toString()) * 1000;
                String input = String.valueOf(timer);
                if(!input.equals("")){

//                    MainFragment fragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
//                    fragment.mInputDisplay.setText(input);

                    mOnInputSelected.onSelectedData(input);
                }
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (ISelectedData) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DIALOG_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                if (data.getExtras().containsKey(MY_VALUE)) {

                    String myValue = data.getExtras().getString(MY_VALUE);

                    // Use the returned value
                }
            }
        }
    }

}