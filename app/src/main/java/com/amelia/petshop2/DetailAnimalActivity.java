package com.amelia.petshop2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailAnimalActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnAdopt;
    TextView tvPetNama,tvPetKategori,tvPetGender,tvPetAge,tvPetWeight,tvPetHeight,tvPetDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);
        database = new Database(this);
        tvPetNama = findViewById(R.id.tvPetNama);
        tvPetKategori = findViewById(R.id.tvPetKategori);
        tvPetGender = findViewById(R.id.tvPetGender);
        tvPetAge = findViewById(R.id.tvPetUmur);
        tvPetWeight = findViewById(R.id.tvPetWeight);
        tvPetHeight = findViewById(R.id.tvPetHeight);
        tvPetDetail = findViewById(R.id.tvPetDetail);
        btnAdopt = findViewById(R.id.btnAdopt);
        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                insert();
                delete();
                finish();
            }
        });
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM Hewan WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() >0) {
            tvPetNama.setText(cursor.getString(0).toString());
            tvPetKategori.setText(cursor.getString(1).toString());
            tvPetGender.setText(cursor.getString(2).toString());
            tvPetAge.setText(cursor.getString(3).toString() + " Years");
            tvPetWeight.setText(cursor.getString(4).toString() + " Kg");
            tvPetHeight.setText(cursor.getString(5).toString() + " Cm");
            tvPetDetail.setText(cursor.getString(6).toString());
        }
    }
    public void insert(){
        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL("INSERT INTO adopsi (nama,kategori) values('"+
                tvPetNama.getText().toString() + "','" +
                tvPetKategori.getText().toString() + "')");
        HistoryActivity.hia.RefreshList();
    }
    public void delete(){
        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL("DELETE FROM hewan where nama = '" + getIntent().getStringExtra("nama") + "'");
        Toast.makeText(DetailAnimalActivity.this, "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
        HomeActivity.ha.RefreshList();
    }

}
