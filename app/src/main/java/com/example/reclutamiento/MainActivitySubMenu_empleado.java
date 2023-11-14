package com.example.reclutamiento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclutamiento.EMPLEADO.MainActivityDocEmpleado;

public class MainActivitySubMenu_empleado extends AppCompatActivity {

    String tag,id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub_menu_empleado);
    }

    public void DocEmpleado(View view) {
        Intent intent = new Intent(MainActivitySubMenu_empleado.this, MainActivityDocEmpleado.class);
        intent.putExtra("id",id_usuario);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

}