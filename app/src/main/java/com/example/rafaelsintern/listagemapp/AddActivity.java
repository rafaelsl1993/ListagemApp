package com.example.rafaelsintern.listagemapp;


import android.content.ContentValues;
import android.content.SyncRequest;
import android.net.Network;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;


/*
    Activity para adicionar itens na lista
 */
public class AddActivity extends AppCompatActivity implements MyDatePicker.OnItemSelectedListener {
    private MyDatePicker datePicker;
    private Spinner EdtSummary;
    private TextView EdtDate;
    private EditText EdtValue;
    private TextView EdtCurrency;
    private ActionBar actionBar;
    private ArrayAdapter<CharSequence> adaptSpinner;
    private int day, month, year;
    private String usd,brl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        adaptSpinner = ArrayAdapter.createFromResource(this, R.array.mySpinner, android.R.layout.simple_spinner_item);
        EdtSummary = (Spinner) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
        EdtDate = (TextView) findViewById(R.id.EdtDate);
        adaptSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EdtSummary.setAdapter(adaptSpinner);
        EdtCurrency = findViewById(R.id.EdtCurrency);
        try {
            usd = getIntent().getStringExtra("usd");
            brl = getIntent().getStringExtra("brl");
        }catch(Exception msg) {
            Toast.makeText(this, R.string.ratesError, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        EdtDate.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));

        actionBar = this.getSupportActionBar();
        year = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
        month = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MONTH) + 1;
        day = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_MONTH);
        // Seta de voltar para a tela anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        try{
            String currencyText = new String("USD    =   "+ usd + "\nBRL    =   "+ brl);
            EdtCurrency.setText(currencyText);
        }catch(Exception msg){
            Toast.makeText(this, R.string.ratesError, Toast.LENGTH_SHORT).show();
        }

    }

    // Evento para voltar para a parent(tela anterior)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showDialog(View view) {
        datePicker = new MyDatePicker();
        datePicker.show(getFragmentManager(), "DatePicker");
    }

    public void funcAdiciona(View view) {            //Função invocada quando o botão é clicado, captura os valores das TextEdits e armazena no vetor
        ContentValues values = new ContentValues();
        try {
            values.put("description", EdtSummary.getSelectedItemPosition());
            values.put("value", Float.parseFloat(EdtValue.getText().toString()));
            values.put("year", year);
            values.put("month", month);
            values.put("day", day);
        } catch (Exception msg) {
            Toast.makeText(this, "Error " + msg.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            Uri uri;
            try {
                uri = getContentResolver().insert(BancoProvider.CONTENT_URI, values);
                Toast.makeText(this,R.string.success, Toast.LENGTH_LONG).show();
            } catch (android.database.SQLException msn) {
                Toast.makeText(this, "Error " + msn.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                EdtSummary.setSelection(0);
                EdtValue.setText("");
                EdtDate.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
            }
        }
    }

    @Override
    public void setDate(String format) {
        EdtDate.setText(format);
        year = datePicker.year;
        month = datePicker.month;
        day = datePicker.dayOfMonth;
    }

}