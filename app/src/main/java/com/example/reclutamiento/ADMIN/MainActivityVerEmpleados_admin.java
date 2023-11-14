package com.example.reclutamiento.ADMIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.reclutamiento.INCLUDES.LigasProceso;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityVerEmpleados_admin extends AppCompatActivity {
//declaracion de variables
    ImageButton imgbtnbuscaempleados,imgbtnmenu;
    ListView listEmpleados;
    String tag, id_usuario;
    ArrayList<Integer> listaIDE = new ArrayList<Integer>();
    ArrayList<String> listaNomE = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ver_empleados_admin);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        //obtener id
        imgbtnbuscaempleados= findViewById(R.id.imgbtnbuscaempleados);
        listEmpleados= findViewById(R.id.listEmpleados);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);
        //regresa a menu
        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenemenu = new Intent(MainActivityVerEmpleados_admin.this, MainActivityMenuRec.class);
                startActivity(intenemenu);
            }
        });
        //redirecciona a web service para búsqueda
        imgbtnbuscaempleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerempleados("http://45.40.160.177/APPRH/reclutamiento/buscaEmpleado.php");
            }
        });
        //clic en list para ver información del postulante
        listEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tempidE =listaIDE.get(position).toString();
                Intent intent = new Intent(MainActivityVerEmpleados_admin.this, MainActivityDetalleEmpleado_admin.class);
                intent.putExtra("idemp",tempidE);
                startActivity(intent);

            }
        });
    }
    //regresa json con listado de postulantes y agrega a la lista
    private void obtenerempleados(String url) {

        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        // PostulanteClass postulanteClass = new PostulanteClass();
                        jsonObject = response.getJSONObject(b);
                        int ide = jsonObject.getInt("idEmpleado");
                        String nombrE = jsonObject.getString("nombre");
                        listaIDE.add(ide);
                        listaNomE.add(nombrE);
                        //agrega Array list a la lista para visualizar la lista de postulantes
                        ArrayAdapter<LigasProceso> listp = new ArrayAdapter(MainActivityVerEmpleados_admin.this, android.R.layout.simple_list_item_1,listaNomE);
                        listEmpleados.setAdapter(listp);

                    } catch (JSONException e) {
                        Toast.makeText(MainActivityVerEmpleados_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityVerEmpleados_admin.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityVerEmpleados_admin.this);
        requestQueue.add(jsonArrayRequest);
    }
}