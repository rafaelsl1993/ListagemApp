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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Moviment> list = new ArrayList<Moviment>(); // Vetor de movimentações que será utilizado na RecyclerView
    private RecyclerView mRecycler;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolBar; // Não utilizado, seria caso fosse utilizado uma ToolBar 100% minha

    public static void listAdd(Moviment mov){
        list.add(mov);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate é chamado para criar a Activity sendo que todos os EditText, Buttons, etc
        super.onCreate(savedInstanceState);                 // devem ser inicializadas com os ID's referentes
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            list = (ArrayList<Moviment>) savedInstanceState.getSerializable("MOVIMENTS");       //Caso já hava alguma instancia de objetos, para que não seja zerado a lista
        }
        //mToolBar = (Toolbar) findViewById(R.id.toolBar);
        //setSupportActionBar(mToolBar);

        mLayoutManager = new LinearLayoutManager(this);     //Set LayoutManager como Linear, poderia ser outras de acordo com a utilização
        mRecycler = findViewById(R.id.RList);   //mRecycler recebe o ID do Recycler da UI
        //Após o fim de onCreate, onResume é chamado -- LifeCycle
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRecycler.setLayoutManager(mLayoutManager); // Link entre Recycler e Layout
        adapterView();  //Chama função para listar o que tiver na ArrayList list
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceToSave) {  //Quando ocorre a mudança de orientação do dispositivo(vertical e horizontal)
        super.onSaveInstanceState(instanceToSave);              //A Activity é destruida e com isso se perde todas as variáveis e informações
        instanceToSave.putSerializable("MOVIMENTS", list);      //Por isso deve ser implementado o onSaveInstanceState, para não perder informações
        //Estudar Activity Lifecycle
    }

    public void funcAbreTela(View view) {
        Intent intent = new Intent(this, AddActivity.class);    //Intent, utilizado para abrir outra Activity(Tela)
        startActivity(intent);  //Inicia a outra Activity
    }

    public void adapterView() {
        //mAdapter recebe a lista de itens e depois é passado para a mRecycler para ser listado
        mAdapter = new MyViewAdapter(list);     //Como sempre a List é modificada, é necessário que o Adapter seja instanciado com a mais recente List
        mRecycler.setAdapter(mAdapter);         //Após isso é necessário também que a Recycler receba a nova Adapter
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.TelaAdd)    //Id do botão do menu (res/menu/menu_main.xml)
            funcAbreTela(item.getActionView()); //chama função funcAbreTela(abre tela de adicionar elementos na lista)

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //inflate no menu o menu_main.xml
        return true;
    }

}