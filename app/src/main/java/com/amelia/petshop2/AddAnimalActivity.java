package com.amelia.petshop2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAnimalActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnAddHome;
    EditText etPetNama,etPetKategori,etPetGender,etPetAge,etPetWeight,etPetHeight,etPetDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        database = new Database(this);
        etPetNama = findViewById(R.id.etPetNama);
        etPetKategori = findViewById(R.id.etPetKategori);
        etPetGender = findViewById(R.id.etPetGender);
        etPetAge = findViewById(R.id.etPetAge);
        etPetWeight = findViewById(R.id.etPetWeight);
        etPetHeight = findViewById(R.id.etPetHeight);
        etPetDetail = findViewById(R.id.etPetDetail);
        btnAddHome = findViewById(R.id.btnAddHome);
        btnAddHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SQLiteDatabase db = database.getWritableDatabase();
                    db.execSQL("INSERT INTO hewan(nama,kategori,jenis_kelamin,umur,berat,tinggi,detail) values('"+
                            etPetNama.getText().toString() + "','" +
                            etPetKategori.getText().toString() + "','" +
                            etPetGender.getText().toString() + "','" +
                            etPetAge.getText().toString() + "','" +
                            etPetWeight.getText().toString() + "','" +
                            etPetHeight.getText().toString() + "','" +
//                            etPetHeight.getText().toString() + "','" +
//                            etPetHeight.getText().toString() + "','" +
                            etPetDetail.getText().toString() + "')");
                    Toast.makeText(AddAnimalActivity.this, "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
                    HomeActivity.ha.RefreshList();
                    finish();
            }
        });
    }
}