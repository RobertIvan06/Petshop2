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

public class HomeActivity extends AppCompatActivity {
    String[] daftar;
    GridView gridView;
    Menu menu;
    Button btnAddHome,btnHistoryHome;
    protected Cursor cursor;
    Database database;
    public static HomeActivity ha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnAddHome = findViewById(R.id.btnAddHome);
        btnAddHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(HomeActivity.this,AddAnimalActivity.class);
                startActivity(pindah);
            }
        });
        btnHistoryHome = findViewById(R.id.btnHistoryHome);
        btnHistoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(pindah);
            }
        });
        ha = this;
        database = new Database(this);
        RefreshList();
    }
    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM hewan", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        gridView.setSelected(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Detail Hewan","Update Hewan","Hapus Hewan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailAnimalActivity.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateAnimalActivity.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM hewan where nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) gridView.getAdapter()).notifyDataSetInvalidated();
    }
}