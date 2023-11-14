package com.example.reclutamiento.EMPLEADO;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.reclutamiento.MainActivityMenuRec;
import com.example.reclutamiento.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivityDocEmpleado extends AppCompatActivity {

    //declaracion de variables
    String id_usuario,tag;
    EditText edtxtcedula,edtxttitulo,edtxtine,edtxtcurp,edtxtacta,edtxtseguro,edtxtcartilla,edtxtdomicilio,edtxtnopenales;
    ImageButton imgbtnOpencedula,imgbtnOpentitulo,imgbtnOpenine,imgbtnOpencurp,imgbtnOpenacta,imgbtnOpenseguro,imgbtnOpencartilla,imgbtnOpendomicilio,
    imgbtnOpennopenales,imgbtnUpArchivos,imgbtnmenu,imgbtnguardarDocs;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri uriCedula,uriTitulo,uriIne,uriCurp,uriActa,uriSeguro,uriCartilla,uriDom,uriNopenales;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doc_empleado);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = "1"; //global.getString("id");
        tag = global.getString("tag");
        //obtencion de id
        edtxtcedula=findViewById(R.id.edtxtcedula);
        imgbtnOpencedula=findViewById(R.id.imgbtnOpencedula);
        edtxttitulo=findViewById(R.id.edtxttitulo);
        imgbtnOpentitulo=findViewById(R.id.imgbtnOpentitulo);
        edtxtine=findViewById(R.id.edtxtIne);
        imgbtnOpenine=findViewById(R.id.imgbtnOpenIne);
        edtxtcurp=findViewById(R.id.edtxtcurp);
        imgbtnOpencurp=findViewById(R.id.imgbtnOpencurp);
        edtxtacta=findViewById(R.id.edtxtactanac);
        imgbtnOpenacta=findViewById(R.id.imgbtnOpenactanac);
        edtxtseguro=findViewById(R.id.edtxtNoseguro);
        imgbtnOpenseguro=findViewById(R.id.imgbtnOpenNoseguro);
        edtxtcartilla=findViewById(R.id.edtxtcartilla);
        imgbtnOpencartilla=findViewById(R.id.imgbtnOpencartilla);
        edtxtdomicilio=findViewById(R.id.edtxtComprobanteDom);
        imgbtnOpendomicilio=findViewById(R.id.imgbtnOpenComprobanteDom);
        edtxtnopenales=findViewById(R.id.edtxtantecedentes);
        imgbtnOpennopenales=findViewById(R.id.imgbtnOpenantecedentes);
        imgbtnUpArchivos=findViewById(R.id.imgbtnUpArchivos);
        imgbtnguardarDocs=findViewById(R.id.imgbtnguardardocs);
        imgbtnmenu=findViewById(R.id.imgbtnmenu);

        storage = FirebaseStorage.getInstance();//regresa un objeto de firebase storage
        database = FirebaseDatabase.getInstance(); //regresa un objeto de firebase darabase
        //regresa a menu
        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivityDocEmpleado.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });
        //verifica permisos para elegir archivo
        imgbtnOpencedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentCedula();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
            }
        });
        imgbtnOpentitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentTitulo();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},11);
                }
            }
        });
        imgbtnOpenine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentIne();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},12);
                }
            }
        });
        imgbtnOpencurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentCurp();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},13);
                }
            }
        });
        imgbtnOpenacta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentActa();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},14);
                }
            }
        });
        imgbtnOpenseguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentSeguro();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},15);
                }
            }
        });
        imgbtnOpencartilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentCartilla();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},16);
                }
            }
        });
        imgbtnOpendomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentDomicilio();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},17);
                }
            }
        });
        imgbtnOpennopenales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivityDocEmpleado.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectDocumentNopenales();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivityDocEmpleado.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},18);
                }
            }
        });
        //subir archivos
        imgbtnUpArchivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uriCedula!=null && uriTitulo!=null && uriIne!=null && uriCurp!=null && uriActa!=null && uriSeguro!=null && uriCartilla!=null && uriDom!=null && uriNopenales!=null){
                    uploadDocs(uriCedula,uriTitulo,uriIne,uriCurp,uriActa,uriSeguro,uriCartilla,uriDom,uriNopenales);
                }else{
                    Toast.makeText(MainActivityDocEmpleado.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //guardar info en base de datos
        imgbtnguardarDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cedula=edtxtcedula.getText().toString();
                final String titulo=edtxttitulo.getText().toString();
                final String ine=edtxtine.getText().toString();
                final String curp=edtxtcurp.getText().toString();
                final String acta=edtxtacta.getText().toString();
                final String seguro=edtxtseguro.getText().toString();
                final String cartilla=edtxtcartilla.getText().toString();
                final String domicilio=edtxtdomicilio.getText().toString();
                final String nopenales=edtxtnopenales.getText().toString();
                final int idEmpe = Integer.parseInt(id_usuario);

                String insert_empleadodocs_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_empleadoDocs.php";
                //definir elemento response
                StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_empleadodocs_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        edtxtcedula.getText().clear();edtxttitulo.getText().clear(); edtxtine.getText().clear(); edtxtcurp.getText().clear();
                        edtxtacta.getText().clear();edtxtseguro.getText().clear(); edtxtcartilla.getText().clear(); edtxtdomicilio.getText().clear();
                        edtxtnopenales.getText().clear();
                        Toast.makeText(MainActivityDocEmpleado.this, "Documentos guardados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivityDocEmpleado.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String,String>();
                        //pasamos nuevos parametros para la modificacion
                        params.put("cedula", cedula);
                        params.put("titulo",titulo);
                        params.put("ine",ine);
                        params.put("curp",curp);
                        params.put("actaNacimiento",acta); //transformación de entero a cadena de texto
                        params.put("numSeguroSocial",seguro);
                        params.put("cartilla",cartilla);
                        params.put("comprobanteDomicilio", domicilio);
                        params.put("antecedentesNoPenales",nopenales);
                        params.put("idEmpleado",idEmpe+"");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivityDocEmpleado.this);
                requestQueue.add(stringRequest);
            }
        });
    }
    //solicita permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentCedula();
        }else if(requestCode ==11 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentTitulo();
        }else if(requestCode ==12 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentIne();
        }else if(requestCode ==13 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentCurp();
        }
        else if(requestCode ==14 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentActa();
        }
        else if(requestCode ==15 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentSeguro();
        }
        else if(requestCode ==16 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentCartilla();
        }
        else if(requestCode ==17 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentDomicilio();
        }
        else if(requestCode ==18 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectDocumentNopenales();
        }
        else{
            Toast.makeText(MainActivityDocEmpleado.this,"por favor acepta los permisos para cargar archivos",Toast.LENGTH_SHORT).show();
        }
    }
    //abre archivo para seleccionar docs
    private void SelectDocumentCedula() {
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    private void SelectDocumentTitulo() {
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,101);
    }
    private void SelectDocumentIne(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,102);
    }
    private void SelectDocumentCurp(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,103);
    }
    private void SelectDocumentActa(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,104);
    }
    private void SelectDocumentSeguro(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,105);
    }
    private void SelectDocumentCartilla(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,106);
    }
    private void SelectDocumentDomicilio(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,107);
    }
    private void SelectDocumentNopenales(){
        //Intente para cargar archivos
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,108);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK && data!=null){
            uriCedula=data.getData(); //regresa la uri del archivo seleccionado
            edtxtcedula.setText("archivo seleccionado"); //(data.getData().getLastPathSegment());
        }else if(requestCode==101 && resultCode==RESULT_OK && data!=null){
            uriTitulo=data.getData(); //regresa la uri del archivo seleccionado
            edtxttitulo.setText("archivo seleccionado"); //(data.getData().getLastPathSegment());
        }
        else if(requestCode==102 && resultCode==RESULT_OK && data!=null){
            uriIne=data.getData(); //regresa la uri del archivo seleccionado
            edtxtine.setText(data.getData().getLastPathSegment());
        }else if(requestCode==103 && resultCode==RESULT_OK && data!=null){
            uriCurp=data.getData(); //regresa la uri del archivo seleccionado
            edtxtcurp.setText(data.getData().getLastPathSegment());
        }else if(requestCode==104 && resultCode==RESULT_OK && data!=null){
            uriActa=data.getData(); //regresa la uri del archivo seleccionado
            edtxtacta.setText(data.getData().getLastPathSegment());
        }else if(requestCode==105 && resultCode==RESULT_OK && data!=null){
            uriSeguro=data.getData(); //regresa la uri del archivo seleccionado
            edtxtseguro.setText(data.getData().getLastPathSegment());
        }else if(requestCode==106 && resultCode==RESULT_OK && data!=null){
            uriCartilla=data.getData(); //regresa la uri del archivo seleccionado
            edtxtcartilla.setText(data.getData().getLastPathSegment());
        }else if(requestCode==107 && resultCode==RESULT_OK && data!=null){
            uriDom=data.getData(); //regresa la uri del archivo seleccionado
            edtxtdomicilio.setText(data.getData().getLastPathSegment());
        }else if(requestCode==108 && resultCode==RESULT_OK && data!=null){
            uriNopenales=data.getData(); //regresa la uri del archivo seleccionado
            edtxtnopenales.setText(data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(MainActivityDocEmpleado.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
        }
    }
    private void uploadDocs(Uri uriCedula, Uri uriTitulo, Uri uriIne, Uri uriCurp, Uri uriActa, Uri uriSeguro, Uri uriCartilla, Uri uriDom, Uri uriNopenales) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando archivos");
        progressDialog.setProgress(0);
        progressDialog.show();

        if(uriCedula != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriCedula)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                       String fileLinkcedula = task.getResult().toString();
                                       edtxtcedula.setText(fileLinkcedula);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Cedula cargada correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriTitulo != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriTitulo)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinktitulo = task.getResult().toString();
                                    edtxttitulo.setText(fileLinktitulo);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Titulo cargado correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriIne != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriIne)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkINE = task.getResult().toString();
                                    edtxtine.setText(fileLinkINE);
                                    Toast.makeText(MainActivityDocEmpleado.this,"INE cargada correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriCurp != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriCurp)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkcurp = task.getResult().toString();
                                    edtxtcurp.setText(fileLinkcurp);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Curp cargado correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriActa != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriActa)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkacta = task.getResult().toString();
                                    edtxtacta.setText(fileLinkacta);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Acta cargada correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriSeguro != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriSeguro)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkseguro = task.getResult().toString();
                                    edtxtseguro.setText(fileLinkseguro);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Seguro cargado correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriCartilla != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriCartilla)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkcartilla = task.getResult().toString();
                                    edtxtcartilla.setText(fileLinkcartilla);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Cartilla cargada correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriDom != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriDom)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinkdomicilio = task.getResult().toString();
                                    edtxtdomicilio.setText(fileLinkdomicilio);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Comprobante de domicilio cargado correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }
        if(uriNopenales != null){
            String filename = System.currentTimeMillis()+"";
            StorageReference storageReference = storage.getReference(); //regresa path
            storageReference.child("UploadsDocsEmpleado/"+id_usuario).child(filename).putFile(uriNopenales)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLinknopenales = task.getResult().toString();
                                    edtxtnopenales.setText(fileLinknopenales);
                                    Toast.makeText(MainActivityDocEmpleado.this,"Antecedentes no penales cargado correctamente",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivityDocEmpleado.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int progress = (int)(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(progress);
                }
            });
        }


    }
}