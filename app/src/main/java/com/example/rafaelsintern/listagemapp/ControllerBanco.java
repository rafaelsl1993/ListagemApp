package com.example.rafaelsintern.listagemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControllerBanco {
    private SQLiteDatabase db;
    private SQLiteHelper banco;

    public ControllerBanco(Context context){
        banco = new SQLiteHelper(context);
    }

    public String add(Moviment mov){
        db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("description", mov.getSummary());
        values.put("value", mov.getValue());
        values.put("date", mov.getDate().toString());

        long txt = db.insert("carteiraEletronica", null, values);

        db.close();

        if(txt>0){
            return "Sucesso";
        }
        return "Erro...";
    }

    public Cursor listar(String condition){
        db = banco.getReadableDatabase();

        Cursor cursor = db.query("carteiraEletronica",new String[]{ "description","value","date"} ,condition,null,null,null,null);

        db.close();

        return cursor;
    }

    public void deleta(Moviment mov){
        db = banco.getWritableDatabase();

        String value ="description = " + mov.getSummary() + " AND value = " + mov.getValue() + " AND date = " + mov.getDate();

        db.delete("carteiraEletronica", value, null);

        db.close();
    }

}
