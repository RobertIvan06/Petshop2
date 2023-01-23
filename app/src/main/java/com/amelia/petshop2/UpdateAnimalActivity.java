package com.amelia.petshop2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAnimalActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnAddHome;
    EditText etPetNama,etPetKategori,etPetGender,etPetAge,etPetWeight,etPetHeight,etPetDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_animal);
        database = new Database(this);
        etPetNama = findViewById(R.id.etPetNama);
        etPetKategori = findViewById(R.id.etPetKategori);
        etPetGender = findViewById(R.id.etPetGender);
        etPetAge = findViewById(R.id.etPetAge);
        etPetWeight = findViewById(R.id.etPetWeight);
        etPetHeight = findViewById(R.id.etPetHeight);
        etPetDetail = findViewById(R.id.etPetDetail);
        btnAddHome = findViewById(R.id.btnAddHome);
        SQLiteDatabase db=database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM hewan WHERE nama = '"+
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();

        if(cursor.getCount() >0){
            cursor.moveToPosition(0);
            etPetNama.setText(cursor.getString(0).toString());
            etPetKategori.setText(cursor.getString(1).toString());
            etPetGender.setText(cursor.getString(2).toString());
            etPetAge.setText(cursor.getString(3).toString());
            etPetWeight.setText(cursor.getString(4).toString());
            etPetHeight.setText(cursor.getString(5).toString());
            etPetDetail.setText(cursor.getString(6).toString());
        }
        btnAddHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("UPDATE Hewan set nama='"+
                        etPetNama.getText().toString() +"', kategori='" +
                        etPetKategori.getText().toString() +"', jenis_kelamin='" +
                        etPetGender.getText().toString() +"', umur='" +
                        etPetAge.getText().toString() +"', berat='"+
                        etPetWeight.getText().toString() +"', tinggi='"+
                        etPetHeight.getText().toString() +"', detail='"+
                        etPetDetail.getText().toString() +"' WHERE nama = '" +
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(UpdateAnimalActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                HomeActivity.ha.RefreshList();
                finish();
            }
        });
    }
}