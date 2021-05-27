package com.example.bibliotecaisw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class listarLibrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);
    }
    public  boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);
        // deshabilitar boton menu.findItem(R.id.create).setEnabled(false);
        //menu.findItem(R.id.create).setVisible(false);
        // menu.findItem(R.id.notification).setVisible(false);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.create:
                Intent intent = new Intent(this, crearLibroActivity.class);
                startActivity(intent);
                break;
            case R.id.logIn:
                Intent intentR = new Intent(this, registroActivity.class);
                startActivity(intentR);
                break;
        }
        return  super.onOptionsItemSelected(menuItem);
    }
}