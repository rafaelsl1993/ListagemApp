package com.example.rafaelsintern.listagemapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
/*Utiliza a RecyclerView.Adapter para adaptar a listagem dos itens
  no MainActivity
 */
public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder> {

    private ArrayList<Moviment> list;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView description;
        public TextView value;

        public MyViewHolder(View view){
            super(view);
            description = (TextView) view.findViewById(R.id.Txt1);  //Recebe o ID dos Txt para pegar os valores digitados
            value = (TextView) view.findViewById(R.id.Txt2);
        }
    }

    public MyViewAdapter(ArrayList<Moviment> list){     //Construtor de MyViewAdapter para receber a List
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup p, int tipo){
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.my_view, p, false); //Inflate na View o Layout utilizado
        return new MyViewHolder(v); //Manda View para MyViewHolder
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.description.setText(list.get(position).getSummary());    //Insere a Description e Value na MyViewHolder
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
