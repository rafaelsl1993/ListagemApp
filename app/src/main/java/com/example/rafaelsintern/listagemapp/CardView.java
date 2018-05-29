package com.example.rafaelsintern.listagemapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class CardView extends AppCompatActivity implements MyDatePicker.OnItemSelectedListener {
    private String id;
    private Cursor mCursor;
    private Spinner description;
    private EditText value;
    private int day,month,year;
    public String format;
    private TextView date;
    public ArrayAdapter<CharSequence> adaptSpinner;
    private ActionBar actionBar;
    private MyDatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        adaptSpinner = ArrayAdapter.createFromResource(this, R.array.mySpinner, android.R.layout.simple_spinner_item);
        id = (getIntent().getSerializableExtra("id").toString());
        description = (Spinner) findViewById(R.id.CardEdtSummary);
        value = (EditText) findViewById(R.id.CardValueEdit);
        date = (TextView) findViewById(R.id.CardEdtDate);
        adaptSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        description.setAdapter(adaptSpinner);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCursor = getContentResolver().query(BancoProvider.CONTENT_URI,null,"_id = " + id,null,null);
        mCursor.moveToFirst();
        day = mCursor.getInt(mCursor.getColumnIndex("day"));
        month = mCursor.getInt(mCursor.getColumnIndex("month"));
        year = mCursor.getInt(mCursor.getColumnIndex("year"));
        description.setSelection(mCursor.getInt(mCursor.getColumnIndex("description")));
        value.setText(mCursor.getString(mCursor.getColumnIndex("value")));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        date.setText(DateFormat.getDateInstance().format(calendar.getTime()));

        actionBar = this.getSupportActionBar();
        // Seta de voltar para a tela anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void delMoviment(View view){
        getContentResolver().delete(BancoProvider.CONTENT_URI,"_id = " + id,null);
        this.finish();
    }

    public void editValues(View view){
        ContentValues values = new ContentValues();

        values.put("description", description.getSelectedItemPosition());
        values.put("value", Integer.parseInt(value.getText().toString()));
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);

        if(getContentResolver().update(BancoProvider.CONTENT_URI,values,"_id = " + id,null) == 1){
            this.finish();
        }

    }

    public void showDialog(View view){
        datePicker = new MyDatePicker();
        datePicker.show(getFragmentManager(), "DatePicker");
    }


    @Override
    public void setDate(String format) {
        date.setText(format);
        day = datePicker.dayOfMonth;
        month = datePicker.month;
        year = datePicker.year;
    }
}
