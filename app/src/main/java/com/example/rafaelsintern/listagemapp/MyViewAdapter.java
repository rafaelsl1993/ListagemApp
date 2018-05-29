package com.example.rafaelsintern.listagemapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*Utiliza a RecyclerView.Adapter para adaptar a listagem dos itens
  no MainActivity
 */
public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder>{
    private ArrayList<Moviment> list;
    private Context context;
    private String array[];


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView description;
        public TextView value;
        public TextView date;
        public CardView cardView;


        public MyViewHolder(View view){
            super(view);
            description = (TextView) view.findViewById(R.id.Txt1);  //Recebe o ID dos Txt para pegar os valores digitados
            value = (TextView) view.findViewById(R.id.Txt2);
            date = (TextView) view.findViewById(R.id.Txt3);
            cardView = (CardView) view.findViewById(R.id.CardView);
        }

    }


    public MyViewAdapter(ArrayList<Moviment> list, String array[]){     //Construtor de MyViewAdapter para receber a List
        this.list = list;
        this.array = array;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup p, int tipo){
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.my_view, p, false); //Inflate na View o Layout utilizado
        return new MyViewHolder(v); //Manda View para MyViewHolder
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.cardView.setTag(list.get(position).getId());
        holder.description.setText(array[list.get(position).getSummary()]);
        //holder.description.setText(list.get(position).getSummary());    //Insere a Description e Value na MyViewHolder
        Calendar mC = Calendar.getInstance();
        mC.set(list.get(position).getYear(),list.get(position).getMonth(),list.get(position).getDay());
        holder.date.setText(DateFormat.getDateInstance().format(mC.getTime()));

        if(list.get(position).getCA()) {    //Se Value positivo exibe em cor Verde(sistema RGB)
            holder.value.setText((Float.toString(list.get(position).getValue())));
            holder.value.setTextColor(Color.parseColor("#00FF00"));
        }
        else {  //Caso negativo é exibido em Vermelho e Negrito
            holder.value.setText((Float.toString(list.get(position).getValue())));
            holder.value.setTextColor(Color.parseColor("#FF0000"));
            holder.value.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}
