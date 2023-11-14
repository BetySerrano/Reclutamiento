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
import com.example.reclutamiento.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityVerPostulantes_admin extends AppCompatActivity {
    //declaracion de variables
    String tag, id_usuario;
    ImageButton imgbtnmenu,imgbtnbuscapostulante;
    ListView listpostulante;
    ArrayList<Integer> listapos = new ArrayList<Integer>();
    ArrayList<String> listaposnomb = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ver_postulantes_admin);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        //obtencion de ID
        imgbtnmenu = findViewById(R.id.imgbtnmenu);
        imgbtnbuscapostulante = findViewById(R.id.imgbtnbuscapostulantes);
        listpostulante = findViewById(R.id.listPostulantes);
        //regresa a menu
        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenemenu = new Intent(MainActivityVerPostulantes_admin.this,MainActivityVerPostulantes_admin.class);
                startActivity(intenemenu);
            }
        });
        //redirecciona a web service para búsqueda
        imgbtnbuscapostulante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerpostulantes("http://45.40.160.177/APPRH/reclutamiento/buscaPostulante.php");
            }
        });
        //clic en list para ver información del postulante
        listpostulante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tempid =listapos.get(position).toString();
                Intent intent = new Intent(MainActivityVerPostulantes_admin.this, MainActivityDetallePostulante_admin.class);
                intent.putExtra("idpos",tempid);
                startActivity(intent);

            }
        });
    }
//regresa json con listado de postulantes y agrega a la lista
    private void obtenerpostulantes(String url) {

        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                       // PostulanteClass postulanteClass = new PostulanteClass();
                        jsonObject = response.getJSONObject(b);
                        int id = jsonObject.getInt("idPostulante");
                        String nombr = jsonObject.getString("nombre");
                        listapos.add(id);
                        listaposnomb.add(nombr);
                        //agrega Array list a la lista para visualizar la lista de postulantes
                        ArrayAdapter<LigasProceso> listp = new ArrayAdapter(MainActivityVerPostulantes_admin.this, android.R.layout.simple_list_item_1,listaposnomb);
                        listpostulante.setAdapter(listp);

                    } catch (JSONException e) {
                        Toast.makeText(MainActivityVerPostulantes_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityVerPostulantes_admin.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityVerPostulantes_admin.this);
        requestQueue.add(jsonArrayRequest);
    }
}