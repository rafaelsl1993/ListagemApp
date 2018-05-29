package com.example.rafaelsintern.listagemapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BancoProvider extends ContentProvider{
    private SQLiteHelper banco;
    private SQLiteDatabase db;
    static final String PROVIDER_NAME = "com.example.rafaelsintern.listagemapp.BancoProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/carteiraEletronica";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static final int DESCRIPTION = 100;
    private static final int VALUE = 101;
    private static final int _ID = 103;
    private static final int DAY = 106;
    private static final int MONTH = 105;
    private static final int YEAR = 104;

    private static final UriMatcher mMatcher =new UriMatcher(UriMatcher.NO_MATCH);

    static{
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/description",DESCRIPTION);
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/value",VALUE);
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/year",YEAR);
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/month",MONTH);
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/day",DAY);
        mMatcher.addURI(PROVIDER_NAME,"/carteiraEletronica/#id",_ID);
    }

    @Override
    public boolean onCreate() {
        banco = new SQLiteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        db = banco.getReadableDatabase();
        int opc = mMatcher.match(uri);
        Cursor cursor;

        cursor = db.query("carteiraEletronica", projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //NÃO ESTÁ IMPLEMENTADO
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        db = banco.getWritableDatabase();

        Uri uriReturn;

        long id;

        id = db.insert("carteiraEletronica", null, values);

        uriReturn = ContentUris.withAppendedId(CONTENT_URI, id);
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        db = banco.getWritableDatabase();

        db.delete("carteiraEletronica", selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return 1;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        db = banco.getWritableDatabase();

        if(db.update("carteiraEletronica",values,selection,selectionArgs) == 1){
            getContext().getContentResolver().notifyChange(uri, null);
            db.close();
            return 1;
        }
        return 0;
    }

}
