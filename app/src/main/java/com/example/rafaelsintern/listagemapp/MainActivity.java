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
    private ArrayList<Moviment> list = new ArrayList<Moviment>(); // Vetor de movimentações que será utilizado na RecyclerView
    private RecyclerView mRecycler;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate é chamado para criar a Activity sendo que todos os EditText, Buttons, etc
        super.onCreate(savedInstanceState);                 // devem ser inicializadas com os ID's referentes
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            list = (ArrayList<Moviment>) savedInstanceState.getSerializable("MOVIMENTS");
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecycler = findViewById(R.id.RList);
        EdtSummary = (EditText) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
        mRecycler.setLayoutManager(mLayoutManager);
        adapterView();
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceToSave){  //Quando ocorre a mudança de orientação do dispositivo(vertical e horizontal)
        super.onSaveInstanceState(instanceToSave);              //A Activity é destruida e com isso se perde todas as variáveis e informações
        instanceToSave.putSerializable("MOVIMENTS", list);      //Por isso deve ser implementado o onSaveInstanceState, para não perder informações
                                                                //Estudar Activity Lifecicle
    }

    public void funcAdiciona(View view){            //Função invocada quando o botão é clicado, captura os valores das TextEdits e armazena no vetor
        Moviment mov = new Moviment(EdtSummary.getText().toString(), Float.parseFloat(EdtValue.getText().toString()));
        list.add(mov);
        adapterView();              //Chama função para adaptar a RecyclerView
    }
    public void adapterView(){
        mAdapter = new MyViewAdapter(list);     //Como sempre a List é modificada, é necessário que o Adapter seja instanciado com a mais recente List
        mRecycler.setAdapter(mAdapter);         //Após isso é necessário também que a Recycler receba a nova Adapter
    }
}




