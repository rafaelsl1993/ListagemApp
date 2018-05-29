package com.example.rafaelsintern.listagemapp;

import java.io.Serializable;
import java.util.Date;

public class Moviment implements Serializable { //Para que não ocorra erros com a onSaveInstanceState, é necessário que a classe Moviments implemente a Serializablea
    private int summary;                     //Pois é o modo que estou salvando a List no momento que ocorre alguma mudança no Lifecicle da Activity
    private float value;
    private boolean ca;                 //True, valor positivo          False, valor negativo           Usado para identificar e deixar o valor Verde ou Vermelho
    private int year;
    private int month;
    private int day;
    private int id;



    public Moviment(int summ, float v,int year, int month, int day, int id) {
        this.summary = summ;
        this.id = id;
        this.value = v;
        if(v>=0)
            ca = true;
        else
            ca = false;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Moviment(int summ, float v,int year, int month, int day) {
        this.summary = summ;
        this.value = v;
        if(v>=0)
            ca = true;
        else
            ca = false;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSummary() {
        return summary;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public float getValue() {
        return value;
    }

    public boolean getCA(){
        return ca;
    }


}