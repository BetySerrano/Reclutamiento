package com.example.reclutamiento.RECLUTAMIENTO;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.reclutamiento.INCLUDES.HerramientaRequest;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivityRegistroHerramienta extends AppCompatActivity implements View.OnClickListener {

    //declaracion de variables
    String id_usuario,tag;
    private AsyncHttpClient cliente;
    public int dia,mes,anio;
    TextView txtnombreID;
    EditText txtnombreHerramienta, txtunidadHerramienta, dateEntrega, dateEntregaRH,edtxtID;
    Button btnguardaHerramienta;
    ImageButton imgbntmenu,btnfechaEntrega, btnfechaEntregaRH,imgbtnbuscaID,imgbtnverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_herramienta);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        cliente = new AsyncHttpClient();
        //obtencion de id
        txtnombreID = findViewById(R.id.txtnombreID);
        edtxtID = findViewById(R.id.edtxtID);
        imgbtnbuscaID= findViewById(R.id.imgbtnbuscaID);
        txtnombreHerramienta = findViewById(R.id.txtnomHerramienta);
        txtunidadHerramienta = findViewById(R.id.txtunidadHerramienta);
        dateEntrega = findViewById(R.id.dateEntrega);
        dateEntregaRH = findViewById(R.id.dateEntregaRH);
        btnfechaEntrega = findViewById(R.id.btnfechaEntrega);
        btnfechaEntregaRH = findViewById(R.id.btnfechaEntregaRH);
        btnguardaHerramienta = findViewById(R.id.btnguardaherramienta);
        imgbtnverID = findViewById(R.id.imgbtnverID);
        imgbntmenu=findViewById(R.id.imgbtnmenu);

        imgbntmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu= new Intent(MainActivityRegistroHerramienta.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        btnfechaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == btnfechaEntrega){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityRegistroHerramienta.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateEntrega.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });
        btnfechaEntregaRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == btnfechaEntregaRH){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityRegistroHerramienta.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateEntregaRH.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });

        imgbtnbuscaID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaEmpleado("http://45.40.160.177/APPRH/reclutamiento/buscaEmpleadoDetalle.php?idEmpleado="+edtxtID.getText()+"");
            }
        });
        //ver datos registro herramientas
        imgbtnverID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEntrega.setFocusable(false);
                dateEntregaRH.setFocusable(false);
                VerdatosH("http://45.40.160.177/APPRH/reclutamiento/buscaHerramientas.php?idEmpleado="+edtxtID.getText()+"");
            }
        });

        btnguardaHerramienta.setOnClickListener(this);
    }
    //muestra datos de adeudo
    private void VerdatosH(String urld) {
        JsonArrayRequest jsonArrayRequestt = new JsonArrayRequest(urld, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        txtnombreHerramienta.setText(jsonObject.getString("nombreHerramienta"));
                        txtunidadHerramienta.setText(jsonObject.getString("unidad"));
                        dateEntrega.setText(jsonObject.getString("fechaEntregaEmp"));
                        dateEntregaRH.setText(jsonObject.getString("fechaEntregaRh"));

                    } catch (JSONException e) {
                        Toast.makeText(MainActivityRegistroHerramienta.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRegistroHerramienta.this, "El empleado no tiene adeudo de herramientas", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRegistroHerramienta.this);
        requestQueue.add(jsonArrayRequestt);
    }
    //muestra informacion del empleado
    private void buscaEmpleado(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        String nom = jsonObject.getString("nombre");
                        String apP = jsonObject.getString("apPaterno");
                        String apM = jsonObject.getString("apMaterno");
                        txtnombreID.setText(nom+" "+apP+" "+apM);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityRegistroHerramienta.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRegistroHerramienta.this,"El registro no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRegistroHerramienta.this);
        requestQueue.add(jsonArrayRequest);
    }

    //inserta un nuevo registro de herramientas
    @Override
    public void onClick(View v) {
        edtxtID.setError(null);
        final int id = Integer.parseInt(edtxtID.getText().toString());
        if("".equals(id)){
            edtxtID.setError("Introduce el  ID del empleado");
            edtxtID.requestFocus();
            //termina ejecución y manda error
            return;
        }
        //varibles que recibe datos de los editext
        txtnombreHerramienta.setError(null); //quita errores
        final String nomHerramienta = txtnombreHerramienta.getText().toString();
        //validación de campos obligatorios
        if("".equals(nomHerramienta)){
            txtnombreHerramienta.setError("Introduce el  nombre de la herramienta");
            txtnombreHerramienta.requestFocus();
            //termina ejecución y manda error
            return;
        }
        //varibles que recibe datos de los editext
        txtunidadHerramienta.setError(null); //quita errores
        final String uniHerramienta = txtunidadHerramienta.getText().toString();
        //validación de campos obligatorios
        if("".equals(uniHerramienta)){
            txtunidadHerramienta.setError("Introduce la unidad");
            txtunidadHerramienta.requestFocus();
            //termina ejecución y manda error
            return;
        }
        //varibles que recibe datos de los editext
        dateEntrega.setError(null); //quita errores
        dateEntrega.setFocusable(false);
        final String dateentrega = dateEntrega.getText().toString();
        //validación de campos obligatorios
        if("".equals(dateentrega)){
            dateEntrega.setError("Introduce fecha de entrega");
            dateEntrega.requestFocus();
            //termina ejecución y manda error
            return;
        }
        //varibles que recibe datos de los editext
        dateEntregaRH.setError(null); //quita errores
        dateEntregaRH.setFocusable(false);
        final String dateentregarh = dateEntregaRH.getText().toString();
        //validación de campos obligatorios
        if("".equals(dateentregarh)){
            dateEntregaRH.setError("Introduce fecha de entrega a RH");
            dateEntregaRH.requestFocus();
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
                    //condición sis e realizo de forma exitosa el registro
                    if(success){
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRegistroHerramienta.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("Se ha registrado de forma exitosa");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Mensaje");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();


                        //Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    }else{
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRegistroHerramienta.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("No se registro, verifica nuevamente");
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
        HerramientaRequest herramientaRequest = new HerramientaRequest(nomHerramienta,uniHerramienta,dateentrega,dateentregarh,id, responseListener);
        //definición clase requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRegistroHerramienta.this);
        requestQueue.add(herramientaRequest);

    }
}