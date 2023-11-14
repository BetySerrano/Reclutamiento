package com.example.reclutamiento.RECLUTAMIENTO;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
import com.example.reclutamiento.INCLUDES.LigasProceso;
import com.example.reclutamiento.INCLUDES.LigasProcesoRequest;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class MainActivityLigasReclutamiento extends AppCompatActivity implements View.OnClickListener {
    //declaración de variables
    String tag,id_usuario;
    EditText edtxtfechaRegistro, edtxtfechaContacto,edtxtfechaEntrevista,edtxtliga,txtbuscaIDliga,edtxtemailpostulante;
    Button btnguardaliga;
    ImageButton btnfecharegistro,btnfechacontacto,btnfechaentrevista,imgbtnAddliga,imgbtnbuscaliga,imgbtnModificaliga,imgbtnELiminaliga,imgbtnmenu,imgbtnenviarlink;
    ListView listligas;
    TextView txtlink;
    private AsyncHttpClient cliente;
    public int dia,mes,anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ligas_reclutamiento);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        //obtención de los ID txt y button
        edtxtfechaRegistro = findViewById(R.id.edtxtfechaRegistro);
        edtxtfechaContacto = findViewById(R.id.edtxtfechaContacto);
        edtxtfechaEntrevista = findViewById(R.id.edtxtfechaEntrevista);
        edtxtliga = findViewById(R.id.edtxtliga);
        btnguardaliga = findViewById(R.id.btnguardaliga);
        //listligas = findViewById(R.id.listLigas);
        btnfecharegistro = findViewById(R.id.btnfecharegistro);
        btnfechacontacto = findViewById(R.id.btnfechacontacto);
        btnfechaentrevista = findViewById(R.id.btnfechaentrevista);
        imgbtnAddliga = findViewById(R.id.imgbtnAddliga);
        imgbtnbuscaliga = findViewById(R.id.imgbtnbuscaliga);
        imgbtnModificaliga = findViewById(R.id.imgbtnModificaliga);
        imgbtnELiminaliga = findViewById(R.id.imgbtnEliminaliga);
        txtbuscaIDliga = findViewById(R.id.txtbuscaIDliga);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);
        edtxtemailpostulante=findViewById(R.id.edtxtemailpos);
        imgbtnenviarlink= findViewById(R.id.envairmail);
        txtlink = findViewById(R.id.txtenlacealta);
        listligas = findViewById(R.id.listligas);
        cliente = new AsyncHttpClient();

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityLigasReclutamiento.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });
        //validaciones copy and paste
        edtxtfechaRegistro.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
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
        edtxtfechaContacto.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
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
        edtxtfechaEntrevista.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
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
        edtxtliga.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
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
        //calendario
        btnfecharegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == btnfecharegistro){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityLigasReclutamiento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtxtfechaRegistro.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });

        btnfechacontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == btnfechacontacto){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityLigasReclutamiento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtxtfechaContacto.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });

        btnfechaentrevista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == btnfechaentrevista){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityLigasReclutamiento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtxtfechaEntrevista.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });

        //onclick al boton guardar liga
        btnguardaliga.setOnClickListener(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //agregar liga despues de hacer alguna accion
        imgbtnAddliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnModificaliga.setEnabled(false);
                imgbtnELiminaliga.setEnabled(false);
                Intent intent = new Intent(MainActivityLigasReclutamiento.this,MainActivityLigasReclutamiento.class);
                startActivity(intent);
            }
        });
        //buscar ligas
        imgbtnbuscaliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnguardaliga.setEnabled(false);
                buscaLigasProceso("http://45.40.160.177/APPRH/reclutamiento/buscaLigas.php?idLiga="+txtbuscaIDliga.getText()+"");
            }
        });
        imgbtnModificaliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnELiminaliga.setEnabled(false);
                btnguardaliga.setEnabled(false);
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea modificar la información?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modificaLigaProceso("http://45.40.160.177/APPRH/reclutamiento/editaLiga.php");
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
        imgbtnELiminaliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnguardaliga.setEnabled(false);
                imgbtnModificaliga.setEnabled(false);
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea eliminar la Liga de reclutamiento?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminaLiga("http://45.40.160.177/APPRH/reclutamiento/deleteLiga.php");
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
        obtenerLigas();
        //abrir url de la liga
        listligas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = listligas.getAdapter().getItem(position);
                Uri uri = Uri.parse(obj.toString());
                Intent intentview = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentview);
            }
        });

        imgbtnenviarlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtxtemailpostulante != null && txtlink!=null){
                    enviarEmail();
                }else{
                    Toast.makeText(MainActivityLigasReclutamiento.this,"Faltan información para poder enviar el Email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //enviar link de alta a postulantes
    private void enviarEmail() {
        String email = edtxtemailpostulante.getText().toString();
        String Link = txtlink.getText().toString();

        Intent intentmail = new Intent(Intent.ACTION_SEND);
        intentmail.setData(Uri.parse("mailto:"));
        intentmail.setType("text/plain");
        intentmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intentmail.putExtra(Intent.EXTRA_SUBJECT, "Registro");
        intentmail.putExtra(Intent.EXTRA_PROCESS_TEXT,"Ingresa al siguiente link y registra tus datos para continuar con el proceso, estaremos validando y pronto nos comunicaremos contigo. ¡Éxito!");
        intentmail.putExtra(Intent.EXTRA_TEXT,Link);
        try {
            startActivity(Intent.createChooser(intentmail,"Elegir un cliente de correo electrónico"));

        }catch (Exception ex){
            Toast.makeText(MainActivityLigasReclutamiento.this,ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    //metodo en procesos para mostrar las ligas de proceso
    private void obtenerLigas(){
        String url ="http://45.40.160.177/APPRH/reclutamiento/buscaLigasProceso.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody !=null){
                    listarLigas(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
    //obtiene json de web service y agrega a lista para mostrar
    private void listarLigas(String respuesta) {
        ArrayList<LigasProceso> lista = new ArrayList<LigasProceso>();
        try {
            JSONArray jsonArray =  new JSONArray(respuesta);
            for(int i = 0; i<jsonArray.length(); i++){
                LigasProceso ligasProceso = new LigasProceso();
                ligasProceso.setLigas(jsonArray.getJSONObject(i).getString("liga"));
                lista.add(ligasProceso);
            }
            ArrayAdapter<LigasProceso> lp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
            listligas.setAdapter(lp);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void eliminaLiga(String url) {
        final String idLiga = txtbuscaIDliga.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityLigasReclutamiento.this, "Eliminación exitosa", Toast.LENGTH_SHORT).show();
                limpiarformLigasReclutamiento();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityLigasReclutamiento.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idLiga",idLiga);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityLigasReclutamiento.this);
        requestQueue.add(stringRequest);
    }

    private void limpiarformLigasReclutamiento() {
        //metodo para limpiar formulario despues de eliminar
            edtxtfechaRegistro.setText("");
            edtxtfechaContacto.setText("");
            edtxtfechaEntrevista.setText("");
            edtxtliga.setText("");
    }

    private void modificaLigaProceso(String url) {
        final String idLiga = txtbuscaIDliga.getText().toString();
        final String fecharegistro = edtxtfechaRegistro.getText().toString();
        final String fechacontacto = edtxtfechaContacto.getText().toString();
        final String fechaentrevista = edtxtfechaEntrevista.getText().toString();
        final String liga = edtxtliga.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityLigasReclutamiento.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityLigasReclutamiento.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idLiga",idLiga);
                param.put("fechaRegistro",fecharegistro);
                param.put("fechaContacto",fechacontacto);
                param.put("fechaEntrevista",fechaentrevista);
                param.put("liga",liga);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityLigasReclutamiento.this);
        requestQueue.add(stringRequest);
    }

    private void buscaLigasProceso(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        edtxtfechaRegistro.setText(jsonObject.getString("fechaRegistro"));
                        edtxtfechaContacto.setText(jsonObject.getString("fechaContacto"));
                        edtxtfechaEntrevista.setText(jsonObject.getString("fechaEntrevista"));
                        edtxtliga.setText(jsonObject.getString("liga"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityLigasReclutamiento.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityLigasReclutamiento.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityLigasReclutamiento.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View v) {
        imgbtnModificaliga.setEnabled(false);
        imgbtnELiminaliga.setEnabled(false);
        //varibles que recibe datos de los editext
        edtxtfechaRegistro.setError(null); //quita errores
        final String fecharegistro = edtxtfechaRegistro.getText().toString();
        //validación de campos obligatorios
        if("".equals(fecharegistro)){
            edtxtfechaRegistro.setError("Introduce la fecha de registro");
            edtxtfechaRegistro.requestFocus();
            //termina ejecución y manda error
            return;
        }
        edtxtfechaContacto.setError(null); //quita errores
        final String fechacontacto = edtxtfechaContacto.getText().toString();
        //validación de campos obligatorios
        if("".equals(fechacontacto)){
            edtxtfechaContacto.setError("Introduce la fecha de contacto");
            edtxtfechaContacto.requestFocus();
            //termina ejecución y manda error
            return;
        }
        edtxtfechaEntrevista.setError(null); //quita errores
        final String fechaentrevista = edtxtfechaEntrevista.getText().toString();
        //validación de campos obligatorios
        if("".equals(fechaentrevista)){
            edtxtfechaEntrevista.setError("Introduce la fecha de entrevista");
            edtxtfechaEntrevista.requestFocus();
            //termina ejecución y manda error
            return;
        }
        edtxtliga.setError(null); //quita errores
        final String liga = edtxtliga.getText().toString();
        //validación de campos obligatorios
        if("".equals(fechacontacto)){
            edtxtliga.setError("Introduce la liga de proceso para reclutamiento");
            edtxtliga.requestFocus();
            //termina ejecución y manda error
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
                    int idL = resp.getInt("idLiga");
                    //condición sis e realizo de forma exitosa el registro
                    if(success){
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("Se inserto la liga de reclutamiento con el ID: "+ idL+"");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Registro exitoso");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();
                        edtxtfechaRegistro.getText().clear(); edtxtfechaContacto.getText().clear(); edtxtfechaEntrevista.getText().clear();
                        edtxtliga.getText().clear();

                    }else{
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityLigasReclutamiento.this);
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
        LigasProcesoRequest ligasProcesoRequest = new LigasProcesoRequest(fecharegistro,fechacontacto,fechaentrevista,liga, responseListener);
        //definición clase requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityLigasReclutamiento.this);
        requestQueue.add(ligasProcesoRequest);
    }
}