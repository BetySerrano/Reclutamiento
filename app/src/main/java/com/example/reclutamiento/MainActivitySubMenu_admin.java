package com.example.reclutamiento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclutamiento.ADMIN.MainActivityAddVacante_admin;
import com.example.reclutamiento.ADMIN.MainActivityBajaColaborador;
import com.example.reclutamiento.ADMIN.MainActivityRenovacionContrato;
import com.example.reclutamiento.ADMIN.MainActivityVerEmpleados_admin;
import com.example.reclutamiento.ADMIN.MainActivityVerPostulantes_admin;
import com.example.reclutamiento.RECLUTAMIENTO.MainActivityRegistroHerramienta;

public class MainActivitySubMenu_admin extends AppCompatActivity {
        String tag, id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub_menu_admin);
    }

    public void addVacante(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityAddVacante_admin.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }


    public void addPostulante(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityVerPostulantes_admin.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }


    public void addEmpleo(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityVerEmpleados_admin.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    public void renovacioncontrato(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityRenovacionContrato.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    public void bajacolaborador(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityBajaColaborador.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    public void registroHerramienta(View view) {
        Intent intent = new Intent(MainActivitySubMenu_admin.this, MainActivityRegistroHerramienta.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

}