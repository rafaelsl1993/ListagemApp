package com.example.rafaelsintern.listagemapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
/*
    Activity para adicionar itens na lista
 */
public class AddActivity extends AppCompatActivity {
    private EditText EdtSummary;
    private EditText EdtValue;
    private EditText EdtDate;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EdtSummary = (EditText) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
        EdtDate =(EditText) findViewById(R.id.EdtData);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Customizar titulo do menu
        actionBar = this.getSupportActionBar();
        actionBar.setTitle("Adicionar");

        // Seta de voltar para a tela anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    // Evento para voltar para a parent(tela anterior)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void funcAdiciona(View view){            //Função invocada quando o botão é clicado, captura os valores das TextEdits e armazena no vetor
        Moviment mov = new Moviment(EdtSummary.getText().toString(), Float.parseFloat(EdtValue.getText().toString()), EdtDate.getText().toString());
        //intent.getParcelableArrayListExtra("list");

        MainActivity mainActivity = (MainActivity) getParent();
        mainActivity.listAdd(mov);
        //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
    }

}
