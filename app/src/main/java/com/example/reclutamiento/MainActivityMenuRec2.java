package com.example.reclutamiento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclutamiento.RECLUTAMIENTO.MainActivityComentarios;
import com.example.reclutamiento.RECLUTAMIENTO.MainActivityLigasReclutamiento;
import com.example.reclutamiento.RECLUTAMIENTO.MainActivityProgramacion;

public class MainActivityMenuRec2 extends AppCompatActivity {
    //declaracion de variables
    String id_usuario,tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_rec2);
    }

    public void ligasReclutamiento(View view) {
        Intent intent =  new Intent(MainActivityMenuRec2.this, MainActivityLigasReclutamiento.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    public void Comentarios(View view) {
        Intent intent = new Intent(MainActivityMenuRec2.this, MainActivityComentarios.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    public void Programacion(View view) {
        Intent intent = new Intent(MainActivityMenuRec2.this, MainActivityProgramacion.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

}