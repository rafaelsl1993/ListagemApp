package com.example.rafaelsintern.listagemapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String DATA_NAME = "banco.db";
    private static final String TABLE = "carteiraEletronica";
    private static final String ID = "_id";
    private static final String DESCRIPTION = "description";
    private static final String VALUE = "value";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String DAY = "day";
    private static final int VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATA_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE " + TABLE + " (" + ID + " integer primary key autoincrement, " +
                DESCRIPTION + " integer, " + VALUE + " float not null, " + YEAR + " float not null, " + MONTH + " float, " +
                DAY + " float not null" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}