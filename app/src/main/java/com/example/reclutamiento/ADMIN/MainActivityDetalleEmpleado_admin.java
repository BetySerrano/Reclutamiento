package com.example.reclutamiento.ADMIN;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityDetalleEmpleado_admin extends AppCompatActivity {
    //declaracion de variables
    String tag,id_usuario,tempidE;
    EditText txtfechaingresoemp,txtnombreemp,txtapPaternoemp,txtapMaternoemp,txtcalleemp,txtNoIntemp,txtNoExtemp,txtCPemp,txtcoloniaemp,txtmunicipioemp,
    txtestadoemp,txtnumeroemp,txtemailemp,txtsexoemp,txtedoCivilemp,txtfechanacemp,txtareemp,txtpuestoemp;
    ImageButton imgbtnmenu;
    ListView listDocEmpleado;
    private AsyncHttpClient cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_empleado_admin);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");
        cliente = new AsyncHttpClient();
        //obtener ID
        txtfechaingresoemp=findViewById(R.id.txtfechaingresoemp);
        txtnombreemp=findViewById(R.id.txtnombreemp);
        txtapPaternoemp=findViewById(R.id.txtApPaternoemp);
        txtapMaternoemp= findViewById(R.id.txtApMaternoemp);
        txtcalleemp=findViewById(R.id.txtcalleemp);
        txtNoIntemp= findViewById(R.id.txtNoIntemp);
        txtNoExtemp= findViewById(R.id.txtNoExtemp);
        txtCPemp=findViewById(R.id.txtCPemp);
        txtcoloniaemp= findViewById(R.id.txtcoloniaemp);
        txtmunicipioemp= findViewById(R.id.txtmunicipioemp);
        txtestadoemp= findViewById(R.id.txtestadoemp);
        txtnumeroemp= findViewById(R.id.txtnumeroemp);
        txtemailemp= findViewById(R.id.txtEmailemp);
        txtsexoemp = findViewById(R.id.txtsexoemp);
        txtedoCivilemp= findViewById(R.id.txtedoCivilemp);
        txtfechanacemp = findViewById(R.id.txtfechanacemp);
        txtareemp= findViewById(R.id.txtareaemp);
        txtpuestoemp = findViewById(R.id.txtpuestoemp);
        imgbtnmenu = findViewById(R.id.imgbtnmenu);
        listDocEmpleado = findViewById(R.id.listDocEmpleado);

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityDetalleEmpleado_admin.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        tempidE = getIntent().getStringExtra("idemp");

        DetalleEmpleado("http://45.40.160.177/APPRH/reclutamiento/buscaEmpleadoDetalle.php?idEmpleado="+tempidE+"");

        //abrir url de la liga
        listDocEmpleado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = listDocEmpleado.getAdapter().getItem(position);
                Uri uri = Uri.parse(obj.toString());
                Intent intentview = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentview);
            }
        });
        //conumir web service para obtener link de descarga para documentos
        obtenerLinkDoc();
    }

      private void DetalleEmpleado(String url) {
        JsonArrayRequest arrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        //no editable los edittext
                        txtfechaingresoemp.setFocusable(false);
                        txtnombreemp.setFocusable(false);
                        txtapPaternoemp.setFocusable(false);
                        txtapMaternoemp.setFocusable(false);
                        txtcalleemp.setFocusable(false);
                        txtNoIntemp.setFocusable(false);
                        txtNoExtemp.setFocusable(false);
                        txtCPemp.setFocusable(false);
                        txtcoloniaemp.setFocusable(false);
                        txtmunicipioemp.setFocusable(false);
                        txtestadoemp.setFocusable(false);
                        txtnumeroemp.setFocusable(false);
                        txtemailemp.setFocusable(false);
                        txtsexoemp.setFocusable(false);
                        txtedoCivilemp.setFocusable(false);
                        txtfechanacemp.setFocusable(false);
                        txtareemp.setFocusable(false);
                        txtpuestoemp.setFocusable(false);
                        jsonObject = response.getJSONObject(b);
                        txtnombreemp.setText(jsonObject.getString("nombre"));
                        txtapPaternoemp.setText(jsonObject.getString("apPaterno"));
                        txtapMaternoemp.setText(jsonObject.getString("apMaterno"));
                        txtcalleemp.setText(jsonObject.getString("calle"));
                        txtNoExtemp.setText(jsonObject.getString("numeroExt"));
                        txtNoIntemp.setText(jsonObject.getString("numeroInt"));
                        txtCPemp.setText(jsonObject.getString("codigoPostal"));
                        txtcoloniaemp.setText(jsonObject.getString("colonia"));
                        txtmunicipioemp.setText(jsonObject.getString("municipio"));
                        txtestadoemp.setText(jsonObject.getString("estado"));
                        txtnumeroemp.setText(jsonObject.getString("telefono"));
                        txtemailemp.setText(jsonObject.getString("email"));
                        txtsexoemp.setText(jsonObject.getString("sexo"));
                        txtedoCivilemp.setText(jsonObject.getString("estadoCivil"));
                        txtfechanacemp.setText(jsonObject.getString("fechaNacimiento"));
                        txtareemp.setText(jsonObject.getString("area"));
                        txtpuestoemp.setText(jsonObject.getString("puesto"));
                        ontieneFechaIngreso("http://45.40.160.177/APPRH/reclutamiento/buscaFechaIngreso.php?idEmpleado="+tempidE+"");
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityDetalleEmpleado_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityDetalleEmpleado_admin.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDetalleEmpleado_admin.this);
        requestQueue.add(arrayRequest);
    }

    private void ontieneFechaIngreso(String url) {
        JsonArrayRequest jsonArrayRequest1  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject1 = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject1 = response.getJSONObject(b);
                        txtfechaingresoemp.setText(jsonObject1.getString("fechaEntrada"));
                    }
                    catch (JSONException e) {
                        Toast.makeText(MainActivityDetalleEmpleado_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityDetalleEmpleado_admin.this,"No existe fecha de ingreso",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDetalleEmpleado_admin.this);
        requestQueue.add(jsonArrayRequest1);
    }

    private void obtenerLinkDoc() {
        String url ="http://45.40.160.177/APPRH/reclutamiento/buscaFechaIngreso.php?idEmpleado="+tempidE+"";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                listarLinks(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
    //obtiene json de web service y agrega a lista para mostrar
    private void listarLinks(String respuesta) {
        ArrayList<String> listDocs = new ArrayList<String>();
        try {
            JSONArray jsonArray =  new JSONArray(respuesta);
            for(int i = 0; i<jsonArray.length(); i++){
                String linktitulo = jsonArray.getJSONObject(i).getString("titulo");
                String linkcedula = jsonArray.getJSONObject(i).getString("cedula");
                String linkIne = jsonArray.getJSONObject(i).getString("ine");
                String linkcurp = jsonArray.getJSONObject(i).getString("curp");
                String linkactaNacimiento= jsonArray.getJSONObject(i).getString("actaNacimiento");
                String linknumSeguroSocial = jsonArray.getJSONObject(i).getString("numSeguroSocial");
                String linkcartilla = jsonArray.getJSONObject(i).getString("cartilla");
                String linkcomprobanteDomicilio = jsonArray.getJSONObject(i).getString("comprobanteDomicilio");
                String linkantecedentesNoPenales = jsonArray.getJSONObject(i).getString("antecedentesNoPenales");
                listDocs.add(linktitulo);
                listDocs.add(linkcedula);
                listDocs.add(linkIne);
                listDocs.add(linkcurp);
                listDocs.add(linkactaNacimiento);
                listDocs.add(linknumSeguroSocial);
                listDocs.add(linkcartilla);
                listDocs.add(linkcomprobanteDomicilio);
                listDocs.add(linkantecedentesNoPenales);
            }
            ArrayAdapter<LigasProceso> lp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listDocs);
            listDocEmpleado.setAdapter(lp);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}