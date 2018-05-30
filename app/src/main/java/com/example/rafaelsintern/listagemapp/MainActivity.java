package com.example.rafaelsintern.listagemapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Cursor mCursor;
    private static ArrayList<Moviment> list = new ArrayList<Moviment>(); // Vetor de movimentações que será utilizado na RecyclerView
    private RecyclerView mRecycler;
    private MyViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;
    private String url = "http://data.fixer.io/api/latest?access_key=d6addeca492227a4b294040e88381fab&symbols=USD,BRL";
    private JSONObject mJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate é chamado para criar a Activity sendo que todos os EditText, Buttons, etc
        super.onCreate(savedInstanceState);                 // devem ser inicializadas com os ID's referentes
        setContentView(R.layout.activity_main);
        mLayoutManager = new LinearLayoutManager(this);     //Set LayoutManager como Linear, poderia ser outras de acordo com a utilização
        mRecycler = findViewById(R.id.RList);   //mRecycler recebe o ID do Recycler da UI
        //Após o fim de onCreate, onResume é chamado -- LifeCycle*/
        fab = findViewById(R.id.mainFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWindow();
            }
        });
        getCurrencyRates();
    }

    public void addWindow(){
        Intent intent = new Intent(this, AddActivity.class);    //Intent, utilizado para abrir outra Activity(Tela)
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        mCursor = getContentResolver().query(BancoProvider.CONTENT_URI,null,null,null,null);
        Moviment mov;
        if(mCursor.moveToFirst()){
            do{
                mov  = new Moviment(mCursor.getInt(mCursor.getColumnIndex("description")),mCursor.getFloat(mCursor.getColumnIndex("value")),
                        Integer.parseInt(mCursor.getString(mCursor.getColumnIndex("year"))),
                        mCursor.getInt(mCursor.getColumnIndex("month")), mCursor.getInt(mCursor.getColumnIndex("day")),mCursor.getInt(mCursor.getColumnIndex("_id")));
                list.add(mov);
            }while(mCursor.moveToNext());
        }

        mRecycler.setLayoutManager(mLayoutManager); // Link entre Recycler e Layout
        adapterView();  //Chama função para listar o que tiver na ArrayList list
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceToSave) {  //Quando ocorre a mudança de orientação do dispositivo(vertical e horizontal)
        super.onSaveInstanceState(instanceToSave);              //A Activity é destruida e com isso se perde todas as variáveis e informações
        instanceToSave.putSerializable("MOVIMENTS", list);      //Por isso deve ser implementado o onSaveInstanceState, para não perder informações
        //Estudar Activity Lifecycle
    }

    public void funcAbreTela(View view){
        Intent intent = new Intent(this, AddActivity.class);    //Intent, utilizado para abrir outra Activity(Tela)
        JSONObject jsonArray;
        try {
            jsonArray = mJson.getJSONObject("rates");
            String usd = jsonArray.getString("USD");
            String brl = jsonArray.getString("BRL");
            intent.putExtra("usd", usd);
            intent.putExtra("brl", brl);
        }catch(JSONException msg){

        }finally {
            startActivity(intent);  //Inicia a outra Activity
        }
    }

    public void adapterView(){
        //mAdapter recebe a lista de itens e depois é passado para a mRecycler para ser listado
        mAdapter = new MyViewAdapter(list, getResources().getStringArray(R.array.mySpinner));     //Como sempre a List é modificada, é necessário que o Adapter seja instanciado com a mais recente List
        mRecycler.setAdapter(mAdapter);         //Após isso é necessário também que a Recycler receba a nova Adapter
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.TelaAdd)    //Id do botão do menu (res/menu/menu_main.xml)
            funcAbreTela(item.getActionView()); //chama função funcAbreTela(abre tela de adicionar elementos na lista)

        return super.onOptionsItemSelected(item);
    }

    public void showItem(View view){
        Intent intent = new Intent(this, CardView.class);
        intent.putExtra("id",view.getTag().toString());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  //inflate no menu o menu_main.xml
        return true;
    }

    public void getCurrencyRates() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mJson = response;
                        Toast.makeText(getBaseContext(), (R.string.ratesCurrency),Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), (R.string.ratesError),Toast.LENGTH_SHORT).show();
                    }
                });


        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}

