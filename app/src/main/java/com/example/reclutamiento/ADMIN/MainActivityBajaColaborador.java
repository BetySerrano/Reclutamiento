package com.example.reclutamiento.ADMIN;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityBajaColaborador extends AppCompatActivity {
    //declaracion de variables
    String tag,id_usuario;
    EditText edtxtidbaja;
    ImageButton imgbtnArchivoBaja,imgbtnSubirbaja,imgbtnbuscabaja,imgbtnmenu;
    Button btnActualizaBaja;
    TextView txtfilebaja,txtnombrebaja;
    private AsyncHttpClient cliente;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri uridoc;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_baja_colaborador);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        cliente = new AsyncHttpClient();
        edtxtidbaja = findViewById(R.id.edtxtidBaja);
        imgbtnbuscabaja = findViewById(R.id.imgbtnbuscabaja);
        txtnombrebaja = findViewById(R.id.txtnombrebaja);
        imgbtnArchivoBaja = findViewById(R.id.imgbtnArchivoBaja);
        btnActualizaBaja = findViewById(R.id.btnActualizaBaja);
        imgbtnSubirbaja = findViewById(R.id.imgbtnSubirbaja);
        txtfilebaja = findViewById(R.id.txtfilebaja);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);

        storage = FirebaseStorage.getInstance();//regresa un objeto de firebase storage
        database = FirebaseDatabase.getInstance(); //regresa un objeto de firebase darabase

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityBajaColaborador.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });

        imgbtnArchivoBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityBajaColaborador.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocument();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityBajaColaborador.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
            }
        });
        imgbtnSubirbaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uridoc!=null){
                    uploadDoc(uridoc);
                }else{
                    Toast.makeText(MainActivityBajaColaborador.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgbtnbuscabaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaBaja("http://45.40.160.177/APPRH/reclutamiento/buscaEmpleadoDetalle.php?idEmpleado="+edtxtidbaja.getText()+"");
            }
        });

        btnActualizaBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityBajaColaborador.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea dar de baja al empleado?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bajaEmpleado("http://45.40.160.177/APPRH/reclutamiento/deleteEmpleado.php");
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

    private void buscaBaja(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        String nomb= jsonObject.getString("nombre");
                        String apPate = jsonObject.getString("apPaterno");
                        String apMate = jsonObject.getString("apMaterno");
                        txtnombrebaja.setText(nomb+" "+apPate+" "+apMate);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityBajaColaborador.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityBajaColaborador.this,"Error de consulta, o el dato no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityBajaColaborador.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void bajaEmpleado(String url) {
        final String idem = edtxtidbaja.getText().toString();
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityBajaColaborador.this, "Empleado dado de baja", Toast.LENGTH_SHORT).show();
                txtnombrebaja.setText("");
                edtxtidbaja.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityBajaColaborador.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idEmpleado",idem);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityBajaColaborador.this);
        requestQueue.add(stringRequest1);
    }

    private void uploadDoc(Uri uridoc) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando archivo");
        progressDialog.setProgress(0);
        progressDialog.show();

        String filename = System.currentTimeMillis()+"";
        StorageReference storageReference = storage.getReference(); //regresa path
        storageReference.child("UploadsBaja").child(filename).putFile(uridoc)
                  .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url =taskSnapshot.getStorage().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(MainActivityBajaColaborador.this,"archivo cargado correctamente",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MainActivityBajaColaborador.this,"archivo no cargado",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivityBajaColaborador.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                progressDialog.setProgress(progress);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocument();
        }
        else{
            Toast.makeText(MainActivityBajaColaborador.this,"por favor acepta los permisos para cargar archivos",Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectDocument() {
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data!=null){
            uridoc=data.getData(); //regresa la uri del archivo seleccionado
            txtfilebaja.setText("Archivo seleccionado:"+data.getData().getLastPathSegment());
        }else{
            Toast.makeText(MainActivityBajaColaborador.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
        }
    }
}