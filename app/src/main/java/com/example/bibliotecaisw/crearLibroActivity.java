package com.example.bibliotecaisw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class crearLibroActivity extends AppCompatActivity {
    private Button chooseImage, chooseBook;
    private TextView imageSelected, bookSelected, verBook;
    private ImageView showImage;
    int a=0;
    public  String actualFilePath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libro);

        this.setTitle(R.string.create_book);
        verBook = findViewById(R.id.books);
        chooseImage = findViewById(R.id.imgBook);
        chooseBook = findViewById(R.id.selectBook);
        imageSelected = findViewById(R.id.sImg);
        bookSelected = findViewById(R.id.sBook);
        showImage = findViewById(R.id.showImgBook);
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=0;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(crearLibroActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        getImage();
                    }else{
                        ActivityCompat.requestPermissions(crearLibroActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    }
                }else{
                    getImage();
                }
            }
        });
        chooseBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=1;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(crearLibroActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        getBook();
                    }else{
                        ActivityCompat.requestPermissions(crearLibroActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    }
                }else{
                    getBook();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 101){
            if(resultCode == Activity.RESULT_OK && data != null){
                if (a==0){
                    Uri image = data.getData();
                    InputStream stream =null;
                    String tempID="", id = "";
                    Uri uri = data.getData();
                    if (uri.getAuthority().equals("media")){
                    showImage.setImageURI(image);
                    }
                }else if (a==1){
                    Uri bookFile = data.getData();
                    InputStream stream =null;
                    String tempID="", id = "";
                    Uri uri = data.getData();
                    //verBook.setText(uri.getAuthority());
                    if(uri.getAuthority().equals("com.android.externalstorage.documents")){
                        tempID = DocumentsContract.getDocumentId(bookFile); //bookFile.toString();
                        String[] split = tempID.split(":");
                        String type = split[0];
                        //tempID = tempID.substring(tempID.lastIndexOf("/"+1));
                        id = split[1];
                        Uri contentUriBook = null;
                        //bookSelected.setText("tipo: "+tempID.split(":"));
                        //actualFilePath =  Environment.getExternalStorageDirectory()+"/"+id;

                        actualFilePath =  Environment.getExternalStorageDirectory().getPath()+File.separator+id;
                        bookSelected.setText(actualFilePath);

                        File file = new File(actualFilePath);

                        StringBuilder builder = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = br.readLine())!=null){
                                //Toast.makeText(this,"Hola", Toast.LENGTH_LONG).show();
                                builder.append(line);
                                builder.append("\n");
                            }
                            verBook.setText(builder.toString());
                            br.close();
                        }catch (Exception e){
                           verBook.setText("error: "+e);
                        }

                        verBook.setText(builder.toString());
                    }else {
                        bookSelected.setText("error no se ha podido abrir documento");
                    }

                    //bookSelected.setText(actualFilePath);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100){
            if(permissions.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (a==0){
                    getImage();
                }else if (a==1){
                    getBook();
                }
            }else{
                Toast.makeText(this, "Permitir acceso para continuar", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void getImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }
    public void getBook(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        startActivityForResult(intent, 101);
    }
}