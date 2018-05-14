package com.example.rafaelsintern.listagemapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText EdtSummary;
    private EditText EdtValue;
    private ArrayList<Moviment> list = new ArrayList<Moviment>();
    private RecyclerView mRecycler;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new LinearLayoutManager(this);
        mRecycler = findViewById(R.id.RList);
        EdtSummary = (EditText) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
        mRecycler.setLayoutManager(mLayoutManager);



    }


    public void funcAdiciona(View view){
        Moviment mov = new Moviment(EdtSummary.getText().toString(), Float.parseFloat(EdtValue.getText().toString()));
        list.add(mov);

        mAdapter = new MyViewAdapter(list);
        mRecycler.setAdapter(mAdapter);

    }
}

















