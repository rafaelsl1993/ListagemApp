package com.example.rafaelsintern.listagemapp;


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


/*
    Activity para adicionar itens na lista
 */
public class AddActivity extends AppCompatActivity implements MyDatePicker.OnItemSelectedListener{
    private MyDatePicker datePicker;
    private Spinner EdtSummary;
    private TextView EdtDate;
    private EditText EdtValue;
    private ActionBar actionBar;
    private ArrayAdapter<CharSequence> adaptSpinner;
    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        adaptSpinner = ArrayAdapter.createFromResource(this, R.array.mySpinner, android.R.layout.simple_spinner_item);
        EdtSummary = (Spinner) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
        EdtDate =(TextView) findViewById(R.id.EdtDate);
        adaptSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EdtSummary.setAdapter(adaptSpinner);
    }

    @Override
    public void onResume() {
        super.onResume();

        EdtDate.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));

        actionBar = this.getSupportActionBar();

        // Seta de voltar para a tela anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // Evento para voltar para a parent(tela anterior)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showDialog(View view){
        datePicker = new MyDatePicker();
        datePicker.show(getFragmentManager(), "DatePicker");
    }

    public void funcAdiciona(View view){            //Função invocada quando o botão é clicado, captura os valores das TextEdits e armazena no vetor
        ContentValues values = new ContentValues();

        values.put("description", EdtSummary.getSelectedItemPosition());
        values.put("value", Float.parseFloat(EdtValue.getText().toString()));
        values.put("year", datePicker.year);
        values.put("month", datePicker.month);
        values.put("day", datePicker.dayOfMonth);

        Uri uri = getContentResolver().insert(BancoProvider.CONTENT_URI,values);


        if(uri!=null){
            EdtSummary.setSelection(0);
            EdtValue.setText("");
            EdtDate.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
        }

    }

    @Override
    public void setDate(String format) {
        EdtDate.setText(format);
    }

}