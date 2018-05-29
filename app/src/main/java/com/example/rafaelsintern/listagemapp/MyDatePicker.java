package com.example.rafaelsintern.listagemapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MyDatePicker extends DialogFragment implements OnDateSetListener {
    public int year,month,dayOfMonth;
    public String format;
    public TextView date;

    public MyDatePicker.OnItemSelectedListener listener;

    public interface OnItemSelectedListener{
        public void setDate(String format);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MyDatePicker.OnItemSelectedListener) context;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstances) {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new DatePickerDialog(getActivity(), this,year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mC = Calendar.getInstance();
        mC.set(year,month,dayOfMonth);
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        format = DateFormat.getDateInstance().format(mC.getTime());

        listener.setDate(format);

    }

}