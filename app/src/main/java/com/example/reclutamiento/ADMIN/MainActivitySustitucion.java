package com.example.reclutamiento.ADMIN;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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


public class MainActivitySustitucion extends AppCompatActivity {
    //declaración de variables
    String tag,id_usuario;
   ImageButton imgbtnmenu,imgbtnArchivosustitucion,imgbtnSubirsustitucion;
   TextView txtfilesustitucion;
   FirebaseStorage storage;
   FirebaseDatabase database;
   Uri urisus;
   ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sustitucion);
        //variables que se reviben del activity anterior
        Bundle global = getIntent().getExtras();
        id_usuario = global.getString("id");
        tag = global.getString("tag");

        imgbtnmenu=findViewById(R.id.imgbtnmenu);
        imgbtnArchivosustitucion=findViewById(R.id.imgbtnArchivosustitucion);
        imgbtnSubirsustitucion=findViewById(R.id.imgbtnSubirsustitucion);
        txtfilesustitucion=findViewById(R.id.txtfilesusutitucion);

        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        imgbtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmenu = new Intent(MainActivitySustitucion.this, MainActivityMenuRec.class);
                startActivity(intentmenu);
            }
        });
        //seleccionar archivo
        imgbtnArchivosustitucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivitySustitucion.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    SelectSustitucion();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivitySustitucion.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
                }
            }
        });
        //subir archivo
        imgbtnSubirsustitucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(urisus!=null){
                    uploadSustitucion(urisus);
                }else{
                    Toast.makeText(MainActivitySustitucion.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            SelectSustitucion();
        }
        else{
            Toast.makeText(MainActivitySustitucion.this,"por favor acepta los permisos para cargar archivos",Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectSustitucion() {
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
            urisus=data.getData(); //regresa la uri del archivo seleccionado
            txtfilesustitucion.setText("Archivo seleccionado:"+data.getData().getLastPathSegment());
        }else{
            Toast.makeText(MainActivitySustitucion.this,"por favor selecciona un archivo",Toast.LENGTH_SHORT).show();
        }
    }

    //metodo para subir el archivo a firebase
    private void uploadSustitucion(Uri uridoc) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando archivo");
        progressDialog.setProgress(0);
        progressDialog.show();

        String filename = System.currentTimeMillis()+"";
        StorageReference storageReference = storage.getReference(); //regresa path
        storageReference.child("UploadsSustitucion").child(filename).putFile(uridoc)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url =taskSnapshot.getStorage().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(MainActivitySustitucion.this,"archivo cargado correctamente",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MainActivitySustitucion.this,"archivo no cargado",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivitySustitucion.this,"archivo no cargado",Toast.LENGTH_SHORT).show();
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