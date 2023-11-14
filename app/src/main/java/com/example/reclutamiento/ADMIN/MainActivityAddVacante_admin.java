package com.example.reclutamiento.ADMIN;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.reclutamiento.INCLUDES.VacanteRequest;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityAddVacante_admin extends AppCompatActivity implements View.OnClickListener {
    //declaración de variables
    String id_usuario,tag;
    EditText titulovacante,puestovacante,descripcion,sueldo,prestaciones,horarios,dias, txtbuscaIDvacante;
    Button btnguardavacante;
    ImageButton imgbtnbuscaVacante, imgbtnModificavacante,imgbtnEliminavacante,imgbtnAddvacante,imgbtnmenu;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_vacante);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

    //obtención ID de los txt y button del formulario
        titulovacante = findViewById(R.id.txtvacante);
        puestovacante = findViewById(R.id.txtpuesto);
        descripcion = findViewById(R.id.txtDescripcion);
        sueldo = findViewById(R.id.txtsueldo);
        prestaciones = findViewById(R.id.txtprestacionvacante);
        horarios = findViewById(R.id.txthorario);
        dias = findViewById(R.id.txtdias);
        btnguardavacante=findViewById(R.id.btnvacante);
        txtbuscaIDvacante = findViewById(R.id.txtbuscaIDvacante);
        imgbtnbuscaVacante = (ImageButton)findViewById(R.id.imgbtnbuscaVacante);
        imgbtnModificavacante= findViewById(R.id.imgbtnModificaVacante);
        imgbtnEliminavacante = findViewById(R.id.imgbtnEliminaVacante);
        imgbtnAddvacante = findViewById(R.id.imgbtnAddvacante);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu =  new Intent(MainActivityAddVacante_admin.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        //validacion editext titulovacante deshabilita copyandpaste
        titulovacante.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {

            }});
        puestovacante.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {
            }});
        descripcion.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {
            }});
        sueldo.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {
            }});
        prestaciones.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override
            public void onDestroyActionMode(ActionMode mode) {
            }});
        horarios.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {
            }});
        dias.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("Por motivos de seguridad esta función esta desactivada");
                //elegimo un titulo y configuramos para que se pueda quitar
                dialogBuilder.setCancelable(true).setTitle("Mensaje");
                //mostramos el dialogBuilder
                dialogBuilder.create().show();
                return false;
            } @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            } @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            } @Override public void onDestroyActionMode(ActionMode mode) {
            }});
        //onclick al botón guardar vacante
        btnguardavacante.setOnClickListener(this);

        imgbtnAddvacante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnModificavacante.setEnabled(false);
                imgbtnEliminavacante.setEnabled(false);
                Intent intent = new Intent(MainActivityAddVacante_admin.this,MainActivityAddVacante_admin.class);
                startActivity(intent);
            }
        });

        //buscar vacante
        imgbtnbuscaVacante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnguardavacante.setEnabled(false);
                buscaVacante("http://45.40.160.177/APPRH/reclutamiento/buscaVacante.php?idVacante="+txtbuscaIDvacante.getText()+"");
            }
        });
        //modificar vacante
        imgbtnModificavacante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnEliminavacante.setEnabled(false);
                btnguardavacante.setEnabled(false);
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea modificar la información?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modificaVacante("http://45.40.160.177/APPRH/reclutamiento/editaVacante.php");
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

        imgbtnEliminavacante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnguardavacante.setEnabled(false);
                imgbtnModificavacante.setEnabled(false);
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea eliminar esta vacante?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EliminaVacante("http://45.40.160.177/APPRH/reclutamiento/deleteVacante.php");
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

    @Override
    public void onClick(View v) {
        imgbtnModificavacante.setEnabled(false);
        imgbtnEliminavacante.setEnabled(false);
        //varibles que recibe datos de los editext
        titulovacante.setError(null); //quita errores
        final String titulovac = titulovacante.getText().toString();
        //validación de campos obligatorios
        if("".equals(titulovac)){
            titulovacante.setError("Introduce el  titulo de vacante");
            titulovacante.requestFocus();
            //termina ejecución y manda error
            return;
        }

        puestovacante.setError(null);
        final String puestovac  =puestovacante.getText().toString();
        if("".equals(puestovac)){
            puestovacante.setError("Introduce el puesto de vacante");
            puestovacante.requestFocus();
            return;
        }

        descripcion.setError(null);
        final String desc = descripcion.getText().toString();
        if("".equals(desc)){
            descripcion.setError("Introduce la descripción");
            descripcion.requestFocus();
            return;
        }

        sueldo.setError(null);
        final String sueldovac = sueldo.getText().toString();//Integer.parseInt(sueldo.getText().toString());
        if("".equals(sueldovac)){
            sueldo.setError("Introduce una cantidad");
            sueldo.requestFocus();
            return;
        }

        prestaciones.setError(null);
        final String prestacionesvac = prestaciones.getText().toString();
        if("".equals(prestacionesvac)){
            prestaciones.setError("Introduce las prestaciones");
            prestaciones.requestFocus();
            return;
        }

        horarios.setError(null);
        final String horariosvac = horarios.getText().toString();
        if("".equals(horariosvac)){
            horarios.setError("Introduce un horario");
            horarios.requestFocus();
            return;
        }

        dias.setError(null);
        final String diasvacante =dias.getText().toString();
        if("".equals(diasvacante)){
            dias.setError("Introduce los días laborables");
            dias.requestFocus();
            return;
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
                    int idVa = resp.getInt("idVacante");

                    //condición sis e realizo de forma exitosa el registro
                    if(success){
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("Se inserto la vacante con el ID: "+ idVa+"");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Registro exitoso");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();
                        titulovacante.getText().clear(); puestovacante.getText().clear(); descripcion.getText().clear();
                        sueldo.getText().clear(); prestaciones.getText().clear(); horarios.getText().clear(); dias.getText().clear();


                        //Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    }else{
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityAddVacante_admin.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("No se realizo el registro, verifica la información ingresada");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Error");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //declaración de objeto de tipo vacanteRequest
        VacanteRequest vacanteRequest = new VacanteRequest(titulovac,puestovac,desc,sueldovac,prestacionesvac,horariosvac,diasvacante, responseListener);
        //definición clase requestQueue
        requestQueue = Volley.newRequestQueue(MainActivityAddVacante_admin.this);
        requestQueue.add(vacanteRequest);

    }
//manda a la vista sustitucion
    public void sustitucion(View view) {
        Intent intent = new Intent(MainActivityAddVacante_admin.this, MainActivitySustitucion.class);
        startActivity(intent);
    }

    //metodo de busqueda
    private void buscaVacante(String url){
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        titulovacante.setText(jsonObject.getString("tituloVacante"));
                        puestovacante.setText(jsonObject.getString("puestoVacante"));
                        descripcion.setText(jsonObject.getString("descripcion"));
                        sueldo.setText(jsonObject.getString("sueldo"));
                        prestaciones.setText(jsonObject.getString("prestaciones"));
                        horarios.setText(jsonObject.getString("horarios"));
                        dias.setText(jsonObject.getString("diasLaborables"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityAddVacante_admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityAddVacante_admin.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(MainActivityAddVacante_admin.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void modificaVacante(String url){
        final String idVacante = txtbuscaIDvacante.getText().toString();
        final String titulo = titulovacante.getText().toString();
        final String puesto = puestovacante.getText().toString();
        final String desc = descripcion.getText().toString();
        final String suel = sueldo.getText().toString();
        final String prestacion = prestaciones.getText().toString();
        final String horario = horarios.getText().toString();
        final String diasL = dias.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityAddVacante_admin.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityAddVacante_admin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idVacante",idVacante);
                param.put("tituloVacante",titulo);
                param.put("puestoVacante",puesto);
                param.put("descripcion",desc);
                param.put("sueldo",suel);
                param.put("prestaciones",prestacion);
                param.put("horarios",horario);
                param.put("diasLaborables",diasL);
                return param;
            }
        };
       RequestQueue requestQueue = Volley.newRequestQueue(MainActivityAddVacante_admin.this);
        requestQueue.add(stringRequest);
    }

    //metodo para elimianr registro
    private void EliminaVacante(String url){
        final String idVacante = txtbuscaIDvacante.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityAddVacante_admin.this, "Eliminación exitosa", Toast.LENGTH_SHORT).show();
                limpiarformVacante();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityAddVacante_admin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idVacante",idVacante);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityAddVacante_admin.this);
        requestQueue.add(stringRequest);
    }
    //metodo para limpiar formulario despues de eliminar
    private void limpiarformVacante() {
        titulovacante.setText("");
        puestovacante.setText("");
        descripcion.setText("");
        sueldo.setText("");
        prestaciones.setText("");
        horarios.setText("");
        dias.setText("");
    }
}