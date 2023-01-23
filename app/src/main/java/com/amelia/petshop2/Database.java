package com.amelia.petshop2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="PetShop.db";
    private static final int DATABASE_VERSION = 1;

    public Database (Context context){
        super (context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE hewan (nama text null,kategori text null,jenis_kelamin text null,umur integer null,berat integer null,tinggi integer null,detail integer null,image_name text null,image_file blob null);";
        Log.d("Data","onCreate: " + sql);
        db.execSQL(sql);
        String sql2 = "CREATE TABLE adopsi (nama text, kategori text,image_name text null,image_file blob null);";
        Log.d("Data2","onCreate: " + sql2);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
