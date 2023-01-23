package com.amelia.petshop2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

public class HistoryActivity extends AppCompatActivity {
    String[] daftar;
    GridView gridView;
    Menu menu;
    Button btnViewPet;
    protected Cursor cursor;
    Database database;
    public static HistoryActivity hia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        btnViewPet = findViewById(R.id.btnViewPet);
        btnViewPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(HistoryActivity.this,HomeActivity.class);
                startActivity(pindah);
            }
        });
        hia = this;
        database = new Database(this);
        RefreshList();
    }
    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM adopsi", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        gridView.setSelected(true);
    }
}