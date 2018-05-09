package com.example.rafaelsintern.listagemapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText EdtAdiciona;
    private ArrayList<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.EdtList);
        EdtAdiciona = (EditText) findViewById(R.id.EdtAdiciona);

    }

    private ArrayAdapter<String> adapt = null;

    public void funcAdiciona(View view){

        lista.add(EdtAdiciona.getText().toString());                                //capturar valor EdtAdiciona
        adapt = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lista);  //Adapta ArrayList para Listar

        listView.setAdapter(adapt);                    //Listar valor capturado
    }
}