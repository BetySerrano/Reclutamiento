package com.example.reclutamiento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityMenuRec extends AppCompatActivity {
    //declararion de variables
    String tag, id_usuario;
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menurec);

            //obtiene  el tipo de usuario para validar en cada activity
        String url = "http://192.168.0.15/APPRH/login/validar_usuario.php?codigo="+id_usuario+"";
        JsonArrayRequest arrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        tipo = jsonObject.getInt("tipo");
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityMenuRec.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityMenuRec.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityMenuRec.this);
        requestQueue.add(arrayRequest);
    }
        public void SubmenuAdministracion (View view){
            switch (tipo) {
                case 0: //Administrador
                Intent intent = new Intent(MainActivityMenuRec.this, MainActivitySubMenu_admin.class);
                intent.putExtra("id", id_usuario);
                intent.putExtra("tag", tag);
                startActivity(intent);
                break;
            }
         }

        public void SubmenuReclutamiento (View view){
            switch (tipo) {
                case 0: //Administrador
                    Intent intent = new Intent(MainActivityMenuRec.this, MainActivityMenuRec2.class);
                    intent.putExtra("id", id_usuario);
                    intent.putExtra("tag", tag);
                    startActivity(intent);
            }
    }

        public void SubmenuEmpleado (View view){
            switch (tipo) {
                case 1: //Empleado
                Intent intent = new Intent(MainActivityMenuRec.this, MainActivitySubMenu_empleado.class);
                intent.putExtra("id", id_usuario);
                intent.putExtra("tag", tag);
                startActivity(intent);
            }
    }

}