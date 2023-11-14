package com.example.reclutamiento.ADMIN;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.reclutamiento.INCLUDES.EmpleadoRequest;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivityDetallePostulante_admin extends AppCompatActivity {
    //declaracion de variables
    String tag,id_usuario,user,pass;
    int idE=0,tipo;
    EditText txtnombrepos,txtapPaternoPos,txtapMaternoPos,txtfechanacpos,txtcallepos,txtnumeroExtPos,txtnumeroIntpos,
            txtcpPos,txttelefonoPos,txtemailpos,txtmunicipiopos,txtestadopos,
            txtcoloniapos,txtsexopos,txtedocivilpos,txtareapos,txtpuestopos,txtfechaingresopos;
    ImageButton imgbtnagregarempleado,imgbtnmenu,imgbtnfechaingresopos;
    TextView txttipousuario;
    public int dia,mes,anio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_postulante_admin);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");
        //obtener ID
        txtnombrepos = findViewById(R.id.txtnombrepos);
        txtapPaternoPos = findViewById(R.id.txtapPaternopos);
        txtapMaternoPos = findViewById(R.id.txtapMaternopos);
        txtfechanacpos = findViewById(R.id.txtfechanacpos);
        txtcallepos = findViewById(R.id.txtcallepos);
        txtnumeroExtPos = findViewById(R.id.txtNumExtpos);
        txtnumeroIntpos= findViewById(R.id.txtNumIntpos);
        txtcpPos = findViewById(R.id.txtCPpos);
        txttelefonoPos = findViewById(R.id.txtnumeropos);
        txtemailpos=findViewById(R.id.txtemailpos);
        txtmunicipiopos = findViewById(R.id.txtmunicipiopos);
        txtestadopos=findViewById(R.id.txtestadopos);
        txtcoloniapos=findViewById(R.id.txtcoloniapos);
        txtsexopos=findViewById(R.id.txtsexopos);
        txtedocivilpos=findViewById(R.id.txtedocivilpos);
        txtareapos = findViewById(R.id.txtareapos);
        txtpuestopos = findViewById(R.id.txtpuestopos);
        imgbtnagregarempleado= findViewById(R.id.imgbtnagregarempleado);
        txtfechaingresopos=findViewById(R.id.txtfechaingresopos);
        imgbtnfechaingresopos=findViewById(R.id.imgbtnfechaingresopos);
        imgbtnmenu = findViewById(R.id.imgbtnmenu);
        txttipousuario=findViewById(R.id.txttipousuario);


        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityDetallePostulante_admin.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        String tempid = getIntent().getStringExtra("idpos");

        DetallePostulante("http://45.40.160.177/APPRH/reclutamiento/buscaPostulanteDetalle.php?idPostulante="+tempid+"");
        //muestra calendario
        imgbtnfechaingresopos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == imgbtnfechaingresopos){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDetallePostulante_admin.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            txtfechaingresopos.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();
                    //SELECCIONAR FECHA FIN
                }
            }
        });
        //agregar como empleado
        imgbtnagregarempleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityDetallePostulante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que deseas agregar al postulante como empleado?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //varibles que recibe datos de los editext
                            final String nombreEmpleado = txtnombrepos.getText().toString();
                            final String apPaterno = txtapPaternoPos.getText().toString();
                            final String apMaterno = txtapMaternoPos.getText().toString();
                            final String fechaNac = txtfechanacpos.getText().toString();
                            final String Calle = txtcallepos.getText().toString();
                            final int NoExt = Integer.parseInt(txtnumeroExtPos.getText().toString());
                            final int NoInt = Integer.parseInt(txtnumeroIntpos.getText().toString());
                            final int cp = Integer.parseInt(txtcpPos.getText().toString());
                            final String colonia = txtcoloniapos.getText().toString();
                            final String municipio = txtmunicipiopos.getText().toString();
                            final String estado= txtestadopos.getText().toString();
                            final String telefono = txttelefonoPos.getText().toString();
                            final String Email = txtemailpos.getText().toString();
                            final String Sexo = txtsexopos.getText().toString();
                            final String edoCivil = txtedocivilpos.getText().toString();
                            final String area = txtareapos.getText().toString();
                            final String puesto = txtpuestopos.getText().toString();
                            //validacion de campos obligatorios
                            txtfechaingresopos.setError(null);
                        final String fechaing = txtfechaingresopos.getText().toString();
                        if("".equals(fechaing)){
                            txtfechaingresopos.setError("Introduce la fecha de ingreso");
                            txtfechaingresopos.requestFocus();
                            //termina ejecución y manda error
                            return;
                        }
                        //validación de campos obligatorios
                        txttipousuario.setError(null);
                        final String tipou = txttipousuario.getText().toString();
                        if("".equals(tipou)){
                            txttipousuario.setError("Introduce tipo de usuario");
                            txttipousuario.requestFocus();
                            //termina ejecución y manda error
                            return;
                        }
                        //define tipo de usuario según lo tecleado
                       if(tipou.equals("Administrador")){
                           tipo = 0;
                       }else if (tipou.equals("Empleado")){
                           tipo = 1;
                       }

                        //definir elemento response
                            Response.Listener<String> responseListener =  new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //declaración de objeto tipo json
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        //declaración variable boolean
                                        Boolean success = jsonObject.getBoolean("success");
                                        String res = jsonObject.getString("id");
                                        JSONObject resp = new JSONObject(res);
                                        idE = resp.getInt("idEmpleado");
                                        //inserta fecha entrada en  empleadodoc
                                        insertafechaentradapos("http://45.40.160.177/APPRH/reclutamiento/insertaFechaEntrada.php?idEmpleado="+idE+""+"&fechaEntrada="+fechaing);
                                        //inserta información de login
                                        insertalogin("http://45.40.160.177/APPRH/reclutamiento/insert_tlogin.php");
                                        //condición si se realizo de forma exitosa el registro
                                        if(success){
                                            //se prepara la alerta creando nueva instancia
                                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityDetallePostulante_admin.this);
                                            //seleccionamos la cadena a mostrar
                                            dialogBuilder.setMessage("Se inserto el empleado con la siguiente información ID:"+ idE+"" +  "Tag:"+user +  "Contraseña:"+pass);
                                            //elegimo un titulo y configuramos para que se pueda quitar
                                            dialogBuilder.setCancelable(true).setTitle("Registro exitoso");
                                            //mostramos el dialogBuilder
                                            dialogBuilder.create().show();


                                            //Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                                        }else{
                                            //se prepara la alerta creando nueva instancia
                                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityDetallePostulante_admin.this);
                                            //seleccionamos la cadena a mostrar
                                            dialogBuilder.setMessage("No se realizo el registro, verifica la información ingresada");
                                            //elegimo un titulo y configuramos para que se pueda quitar
                                            dialogBuilder.setCancelable(true).setTitle("Error");
                                            //mostramos el dialogBuilder
                                            dialogBuilder.create().show();
                                            //Toast.makeText(getApplicationContext(),"No se realizo el registro, verifica la información ingresada",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            //declaración de objeto de tipo vacanteRequest
                            EmpleadoRequest empleadoRequest = new EmpleadoRequest(nombreEmpleado,apPaterno,apMaterno,Calle,NoExt,NoInt,cp,colonia,municipio,estado,telefono,Email,Sexo,edoCivil,fechaNac,area,puesto, responseListener);
                            //definición clase requestQueue
                            RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDetallePostulante_admin.this);
                            requestQueue.add(empleadoRequest);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                //mostramos el dialogBuilder
                dialogBuilder.create().show();

            }
        });

    }
    //visualiza detalle del postulante
    private void DetallePostulante(String url) {
        JsonArrayRequest arrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        //no editable los edittext
                        txtnombrepos.setFocusable(false);
                        txtapPaternoPos.setFocusable(false);
                        txtapMaternoPos.setFocusable(false);
                        txtfechanacpos.setFocusable(false);
                        txtcallepos.setFocusable(false);
                        txtnumeroExtPos.setFocusable(false);
                        txtnumeroIntpos.setFocusable(false);
                        txtcpPos.setFocusable(false);
                        txtcoloniapos.setFocusable(false);
                        txtmunicipiopos.setFocusable(false);
                        txtestadopos.setFocusable(false);
                        txttelefonoPos.setFocusable(false);
                        txtemailpos.setFocusable(false);
                        txtsexopos.setFocusable(false);
                        txtedocivilpos.setFocusable(false);
                        txtareapos.setFocusable(false);
                        txtpuestopos.setFocusable(false);
                        txtfechaingresopos.setFocusable(false);
                        jsonObject = response.getJSONObject(b);
                        txtnombrepos.setText(jsonObject.getString("nombre"));
                        txtapPaternoPos.setText(jsonObject.getString("apPaterno"));
                        txtapMaternoPos.setText(jsonObject.getString("apMaterno"));
                        txtfechanacpos.setText(jsonObject.getString("fechaNacimiento"));
                        txtcallepos.setText(jsonObject.getString("calle"));
                        txtnumeroExtPos.setText(jsonObject.getString("numeroExt"));
                        txtnumeroIntpos.setText(jsonObject.getString("numeroInt"));
                        txtcpPos.setText(jsonObject.getString("codigoPostal"));
                        txtcoloniapos.setText(jsonObject.getString("colonia"));
                        txtmunicipiopos.setText(jsonObject.getString("municipio"));
                        txtestadopos.setText(jsonObject.getString("estado"));
                        txttelefonoPos.setText(jsonObject.getString("telefono"));
                        txtemailpos.setText(jsonObject.getString("email"));
                        txtsexopos.setText(jsonObject.getString("sexo"));
                        txtedocivilpos.setText(jsonObject.getString("estadoCivil"));
                        txtareapos.setText(jsonObject.getString("area"));
                        txtpuestopos.setText(jsonObject.getString("Vacante"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityDetallePostulante_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityDetallePostulante_admin.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDetallePostulante_admin.this);
        requestQueue.add(arrayRequest);
    }
    //regresa respuesta de insercción
    private void insertafechaentradapos(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //declaración variable boolean
                    Boolean success = jsonObject.getBoolean("success");

                    //condición si se realizo de forma exitosa el registro
                    if (success) {
                    }
                }catch (Exception e ){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDetallePostulante_admin.this);
        requestQueue.add(stringRequest);
    }

    //realiza registro y pasa parametros
    private void  insertalogin(String url1){
        user = txtnombrepos.getText().toString().substring(0,1)+txtapPaternoPos.getText().toString();
        pass = txtfechaingresopos.getText().toString().substring(0,4)+"0Q"+txtapMaternoPos.getText().toString().substring(0,1)+"TvQ1";
        //inserta en tlogin
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityDetallePostulante_admin.this, "Se registro ususario y contraseña", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityDetallePostulante_admin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                //pasamos nuevos parametros para la modificacion
                params.put("codigo",idE+"");
                params.put("tag", user);
                params.put("pass",pass);
                params.put("tipo",tipo+"");
                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivityDetallePostulante_admin.this);
        requestQueue1.add(stringRequest1);
    }
}