package com.example.rafaelsintern.listagemapp;

import java.io.Serializable;
import java.util.Date;

public class Moviment implements Serializable { //Para que não ocorra erros com a onSaveInstanceState, é necessário que a classe Moviments implemente a Serializablea
    private String summary;                     //Pois é o modo que estou salvando a List no momento que ocorre alguma mudança no Lifecicle da Activity
    private float value;
    private boolean ca;                 //True, valor positivo          False, valor negativo           Usado para identificar e deixar o valor Verde ou Vermelho
    private Date date;

    public Moviment(String summ, float v, String d) {
        this.summary = summ;
        this.value = v;
        if(v>=0)
            ca = true;
        else
            ca = false;
    }

    public String getSummary() {
        return summary;
    }

    public float getValue() {
        return value;
    }

    public boolean getCA(){
        return ca;
    }

    public Date getDate(){
        return date;
    }

}