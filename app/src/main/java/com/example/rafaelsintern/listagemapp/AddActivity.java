package com.example.rafaelsintern.listagemapp;


        import android.content.Intent;


        import android.os.Bundle;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText EdtSummary;
    private EditText EdtValue;
    private Intent intent;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EdtSummary = (EditText) findViewById(R.id.EdtSummary);
        EdtValue = (EditText) findViewById(R.id.EdtValue);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Display custom title
        actionBar = this.getSupportActionBar();
        actionBar.setTitle("TITULO");

        // Display the back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    // Back arrow click event to go to the parent Activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void funcAdiciona(View view){            //Função invocada quando o botão é clicado, captura os valores das TextEdits e armazena no vetor
        Moviment mov = new Moviment(EdtSummary.getText().toString(), Float.parseFloat(EdtValue.getText().toString()));
        //intent.getParcelableArrayListExtra("list");

        MainActivity mainActivity = (MainActivity) getParent();
        mainActivity.listAdd(mov);

    }

}
