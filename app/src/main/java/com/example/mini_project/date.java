package com.example.mini_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class date extends DialogFragment {
    Calendar c= Calendar.getInstance();
    int y= c.get(Calendar.YEAR);
    int m= c.get(Calendar.MONTH);
    int d=c.get(Calendar.DAY_OF_MONTH);
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(),y,m,d);
    }
}
