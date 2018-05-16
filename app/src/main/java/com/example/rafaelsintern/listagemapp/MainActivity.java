package com.example.rafaelsintern.listagemapp;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Moviment> list = new ArrayList<Moviment>(); // Vetor de movimentações que será utilizado na RecyclerView
    private RecyclerView mRecycler;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolBar;

    public static void listAdd(Moviment mov){
        list.add(mov);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate é chamado para criar a Activity sendo que todos os EditText, Buttons, etc
        super.onCreate(savedInstanceState);                 // devem ser inicializadas com os ID's referentes
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            list = (ArrayList<Moviment>) savedInstanceState.getSerializable("MOVIMENTS");
        }
        //mToolBar = (Toolbar) findViewById(R.id.toolBar);
        //setSupportActionBar(mToolBar);

        mLayoutManager = new LinearLayoutManager(this);
        mRecycler = findViewById(R.id.RList);

        mRecycler.setLayoutManager(mLayoutManager);
        adapterView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        list.add((new Moviment("TROCA", 000000)));

        mRecycler.setLayoutManager(mLayoutManager);
        adapterView();
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceToSave) {  //Quando ocorre a mudança de orientação do dispositivo(vertical e horizontal)
        super.onSaveInstanceState(instanceToSave);              //A Activity é destruida e com isso se perde todas as variáveis e informações
        instanceToSave.putSerializable("MOVIMENTS", list);      //Por isso deve ser implementado o onSaveInstanceState, para não perder informações
        //Estudar Activity Lifecicle
    }

    public void funcAbreTela(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void adapterView() {
        mAdapter = new MyViewAdapter(list);     //Como sempre a List é modificada, é necessário que o Adapter seja instanciado com a mais recente List
        mRecycler.setAdapter(mAdapter);         //Após isso é necessário também que a Recycler receba a nova Adapter
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.TelaAdd)
            funcAbreTela(item.getActionView());

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
