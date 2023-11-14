package com.example.reclutamiento.RECLUTAMIENTO;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.reclutamiento.INCLUDES.ProgramacionRequest;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class MainActivityProgramacion extends AppCompatActivity implements View.OnClickListener {

    //declaracion de variables
    String id_usuario,tag;
    EditText edtxtidprogramacion,edtxtfechavisita,edtxttallas;
    ImageButton imgbtnbuscaidprogramacion,imgbtnfotos,imgbtndocs,imgbtnfechavisita,imgbtnsubirfotos,imgbtnsubirexpedientes,imgbtnmenu,imgbtnverpro;
    Button btnguardaprogramacion;
    TextView txtnombreprogramacion,txtfiledocs;
    public int dia,mes,anio;
    Uri uridocs;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_programacion);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");
        //obtener id
        edtxtidprogramacion=findViewById(R.id.edtxtidprogramacionpos);
        edtxtfechavisita= findViewById(R.id.editTextDate);
        edtxttallas=findViewById(R.id.editTextTallas);
        imgbtnbuscaidprogramacion=findViewById(R.id.imgbtnbuscapostulantepro);
        imgbtnfotos=findViewById(R.id.imgbtnfotos);
        imgbtndocs=findViewById(R.id.imgbtnexpediente);
        imgbtnfechavisita=findViewById(R.id.imgbtnfechaproceso);
        btnguardaprogramacion=findViewById(R.id.btnguardaProgramacion);
        txtnombreprogramacion=findViewById(R.id.txtnombrepostulantepro);
        imgbtnsubirfotos=findViewById(R.id.imgbtnSubirfotos);
        imgbtnsubirexpedientes=findViewById(R.id.imgbtnSubirexpediente);
        txtfiledocs=findViewById(R.id.txtfiledocs);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);
        imgbtnverpro = findViewById(R.id.imgbtnverpro);

        storage = FirebaseStorage.getInstance();//regresa un objeto de firebase storage
        database = FirebaseDatabase.getInstance(); //regresa un objeto de firebase darabase

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu= new Intent(MainActivityProgramacion.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        //muestra calendario
        imgbtnfechavisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECCIONAR FECHA DE INICIO
                if (v == imgbtnfechavisita){
                    final Calendar c = Calendar.getInstance();
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityProgramacion.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtxtfechavisita.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        }
                    }, anio, mes, dia);
                    datePickerDialog.show();

                    //SELECCIONAR FECHA FIN
                }
            }
        });
        //busca postulante
        imgbtnbuscaidprogramacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaPostulante("http://45.40.160.177/APPRH/reclutamiento/buscaPostulanteDetalle.php?idPostulante="+edtxtidprogramacion.getText()+"");
            }
        });
        //guarda programacion
        btnguardaprogramacion.setOnClickListener(this);
        //seleccionar archivo
        imgbtndocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityProgramacion.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocsIma();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityProgramacion.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
            }
        });
        imgbtnfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityProgramacion.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocsIma();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityProgramacion.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
            }
        });
        //subir expediente
        imgbtnsubirexpedientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uridocs!=null){
                    uploadDocsIma(uridocs);
                }else{
                    Toast.makeText(MainActivityProgramacion.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgbtnsubirfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uridocs!=null){
                    uploadDocsIma(uridocs);
                }else{
                    Toast.makeText(MainActivityProgramacion.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgbtnverpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verdatos("http://45.40.160.177/APPRH/reclutamiento/buscaDatosProgramacion.php?idPostulante="+edtxtidprogramacion.getText()+"");
            }
        });
    }

    private void verdatos(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        edtxtfechavisita.setText(jsonObject.getString("fechaVisita"));
                        edtxttallas.setText(jsonObject.getString("tallas"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityProgramacion.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityProgramacion.this,"Error de consulta, o el dato no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityProgramacion.this);
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocsIma();
        }
        else{
            Toast.makeText(MainActivityProgramacion.this,"por favor acepta los permisos para cargar archivos",Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectDocsIma() {
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data!=null){
            uridocs=data.getData(); //regresa la uri del archivo seleccionado
            txtfiledocs.setText("Archivo seleccionado:"+data.getData().getLastPathSegment());
        }else{
            Toast.makeText(MainActivityProgramacion.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadDocsIma(Uri uridocs) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando archivo");
        progressDialog.setProgress(0);
        progressDialog.show();

        String filename = System.currentTimeMillis()+"";
        StorageReference storageReference = storage.getReference(); //regresa path
        storageReference.child("UploadsProgramacionEstudio").child(filename).putFile(uridocs)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url =taskSnapshot.getStorage().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(MainActivityProgramacion.this,"archivo cargado correctamente",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MainActivityProgramacion.this,"archivo no cargado",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivityProgramacion.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                progressDialog.setProgress(progress);
            }
        });
    }

    private void buscaPostulante(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        txtnombreprogramacion.setText(jsonObject.getString("nombre"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityProgramacion.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityProgramacion.this,"Error de consulta, o el dato no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityProgramacion.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View v) {
        final int idpos= Integer.parseInt(edtxtidprogramacion.getText().toString());
        final String fechav = edtxtfechavisita.getText().toString();
        final String tallas = edtxttallas.getText().toString();
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
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityProgramacion.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("Se ha registrado de forma exitosa");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Mensaje");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();
                        edtxtidprogramacion.getText().clear(); edtxtfechavisita.getText().clear(); edtxttallas.getText().clear();
                        txtnombreprogramacion.setText("");
                    }else{
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityProgramacion.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("No se registro, verifica la información ingresada");
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
        ProgramacionRequest vacanteRequest = new ProgramacionRequest(fechav,tallas,idpos, responseListener);
        //definición clase requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityProgramacion.this);
        requestQueue.add(vacanteRequest);
    }
}