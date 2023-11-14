 package com.example.reclutamiento.ADMIN;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import com.example.reclutamiento.INCLUDES.RenovacionRequest;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivityRenovacionContrato extends AppCompatActivity implements View.OnClickListener {
    //declaracion de variables
    String id_usuario,tag;
    EditText edtxtidrenovacion,edtxtvacaciones;
    ImageButton imgbtnbuscarenovacion,imgbtnvercontrato,imgbtnModificacontrato,imgbtnmenu,imgbtnarchivocontrato,imgbtnsubircontrato;
    TextView txtnombrerenovacion,txtcaracteristicarenovacion;
    Spinner spcaracteristicas;
    Button btnrenovar;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri uriContrato;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_renovacion_contrato);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        //obtencion de id
        edtxtidrenovacion=findViewById(R.id.edtxtidrenovacion);
        imgbtnbuscarenovacion=findViewById(R.id.imgbtnbuscarenovacion);
        txtnombrerenovacion=findViewById(R.id.txtnombrerenovacion);
        spcaracteristicas=findViewById(R.id.spCaracteristicas);
        txtcaracteristicarenovacion = findViewById(R.id.txtcaracteristicarenovacion);
        imgbtnvercontrato=findViewById(R.id.imgbtnvercontrato);
        btnrenovar=findViewById(R.id.btnrenovar);
        imgbtnModificacontrato = findViewById(R.id.imgbtnModificacontrato);
        edtxtvacaciones= findViewById(R.id.edtxtvacaciones);
        imgbtnarchivocontrato= findViewById(R.id.imgbtnarchivocontrato);
        imgbtnsubircontrato=findViewById(R.id.imgbtnSubircontrato);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);

        storage = FirebaseStorage.getInstance();//regresa un objeto de firebase storage
        database = FirebaseDatabase.getInstance(); //regresa un objeto de firebase darabase

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityRenovacionContrato.this, MainActivityMenuRec.class);
                startActivity(intentmenu);

            }
        });
        //validaciones copy and paste
        edtxtidrenovacion.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRenovacionContrato.this);
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


        //validaciones copy and paste
        edtxtvacaciones.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRenovacionContrato.this);
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


        //busca empleado
        imgbtnbuscarenovacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaEmple("http://45.40.160.177/APPRH/reclutamiento/buscaEmpleadoDetalle.php?idEmpleado="+edtxtidrenovacion.getText()+"");
            }
        });
        //busca tipo  de contrato de acuerdo al empleado seleccionado
        imgbtnvercontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verContrato("http://45.40.160.177/APPRH/reclutamiento/buscatipocontrato.php?idEmpleado="+edtxtidrenovacion.getText()+"");
                verVacaciones("http://45.40.160.177/APPRH/reclutamiento/verVacaciones.php?idEmpleado="+edtxtidrenovacion.getText()+"");
            }
        });
        //realiza la renovacion del contrato
        btnrenovar.setOnClickListener(this);
        //modificar contrato
        imgbtnModificacontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se prepara la alerta creando nueva instancia
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRenovacionContrato.this);
                //seleccionamos la cadena a mostrar
                dialogBuilder.setMessage("¿Seguro que desea modificar el contrato del empleado seleccionado?");
                //elegimos un titulo y confirmamos para realizar la accion correspondiente
                dialogBuilder.setCancelable(true).setTitle("Confirmación").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModificaContrato("http://45.40.160.177/APPRH/reclutamiento/editaRenovacion.php");
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
        
        //seleccionar archivo
        imgbtnarchivocontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityRenovacionContrato.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocument();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityRenovacionContrato.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},18);
                }
            }
        });
        //subir archivos
        imgbtnsubircontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uriContrato!=null){
                    uploadDocs(uriContrato);
                }else{
                    Toast.makeText(MainActivityRenovacionContrato.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //solicita permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocument();
        }
        else{
            Toast.makeText(MainActivityRenovacionContrato.this,"por favor acepta los permisos para cargar archivos",Toast.LENGTH_SHORT).show();
        }
    }
    //abre archivo para seleccionar docs
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
            uriContrato=data.getData(); //regresa la uri del archivo seleccionado
            txtcaracteristicarenovacion.setText(data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(MainActivityRenovacionContrato.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
        }
    }
    private void uploadDocs(Uri uriContrato) {
        final String emple = txtnombrerenovacion.getText().toString();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando archivos");
        progressDialog.setProgress(0);
        progressDialog.show();

            String filename = System.currentTimeMillis() + "";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsContarto/"+emple).child(filename).putFile(uriContrato)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                            DatabaseReference reference = database.getReference();
                            reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                        Toast.makeText(MainActivityRenovacionContrato.this, "Archivo cargado correctamente", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(MainActivityRenovacionContrato.this, "archivo no cargado", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityRenovacionContrato.this, "archivo no cargado", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int) (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                    txtcaracteristicarenovacion.setText("");
                }
            });

    }

    private void buscaEmple(String url) {
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
                        txtnombrerenovacion.setText(nom+" "+apP+" "+apM);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityRenovacionContrato.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRenovacionContrato.this,"Error de consulta, o el dato no existe",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRenovacionContrato.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void verContrato(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        txtcaracteristicarenovacion.setText(jsonObject.getString("tipocontrato"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityRenovacionContrato.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRenovacionContrato.this,"Asigna un tipo de contrato",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRenovacionContrato.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void verVacaciones(String url) {
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int b = 0; b < response.length(); b++) {
                    try {
                        jsonObject = response.getJSONObject(b);
                        edtxtvacaciones.setText(jsonObject.getString("vacaciones"));
                    } catch (JSONException e) {
                        Toast.makeText(MainActivityRenovacionContrato.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRenovacionContrato.this,"Asigna un tipo de contrato",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRenovacionContrato.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View v) {
        final int idemple = Integer.parseInt(edtxtidrenovacion.getText().toString());
        final String tipoC =spcaracteristicas.getSelectedItem().toString();
        edtxtvacaciones.setError(null);
        final int vacaciones = Integer.parseInt(edtxtvacaciones.getText().toString());
        if("".equals(vacaciones)){
            edtxtvacaciones.setError("Introduce días de vacaciones");
            edtxtvacaciones.requestFocus();
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
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRenovacionContrato.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("Renovación exitosa");
                        //elegimo un titulo y configuramos para que se pueda quitar
                        dialogBuilder.setCancelable(true).setTitle("Mensaje");
                        //mostramos el dialogBuilder
                        dialogBuilder.create().show();
                        edtxtidrenovacion.getText().clear(); txtnombrerenovacion.setText(""); txtcaracteristicarenovacion.setText("");
                        edtxtvacaciones.getText().clear();


                        //Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    }else{
                        //se prepara la alerta creando nueva instancia
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivityRenovacionContrato.this);
                        //seleccionamos la cadena a mostrar
                        dialogBuilder.setMessage("No se realizo la renovación, verifica la información ingresada");
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
        RenovacionRequest vacanteRequest = new RenovacionRequest(idemple,tipoC,vacaciones, responseListener);
        //definición clase requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRenovacionContrato.this);
        requestQueue.add(vacanteRequest);
    }

    private void ModificaContrato(String url) {
        final int id = Integer.parseInt(edtxtidrenovacion.getText().toString());
        final String tipoCon = spcaracteristicas.getSelectedItem().toString();
        final int vacaciones = Integer.parseInt(edtxtvacaciones.getText().toString());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityRenovacionContrato.this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRenovacionContrato.this, "Elige un empleado", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("idEmpleado",id+"");
                param.put("tipocontrato",tipoCon);
                param.put("vacaciones",vacaciones+"");
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityRenovacionContrato.this);
        requestQueue.add(stringRequest);
    }
}