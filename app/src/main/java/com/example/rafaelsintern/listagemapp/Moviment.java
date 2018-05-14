package com.example.rafaelsintern.listagemapp;

public class Moviment {
    private String summary;
    private float value;
    private int ca;

    public Moviment(String summ, float v) {
        this.summary = summ;
        this.value = v;
        if(v>=0)
            ca = 1;
        else
            ca = 0;
    }

    public String getSummary() {
        return summary;
    }

    public float getValue() {
        return value;
    }

    public int getCA(){
        return ca;
    }
}