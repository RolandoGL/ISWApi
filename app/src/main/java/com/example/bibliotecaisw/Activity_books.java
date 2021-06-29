package com.example.bibliotecaisw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotecaisw.Config.Config;
import com.example.bibliotecaisw.Interface.miBibliotecaOnlineAPI;
import com.example.bibliotecaisw.Model.Books;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_books extends AppCompatActivity {
    private ListView listView;
    private AdapterPublicaciones adapter;
    private miBibliotecaOnlineAPI servicio= Config.getRetrofit().create(miBibliotecaOnlineAPI.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        listView = findViewById(R.id.lista);
        getPublicaciones();
    }
    class AdapterPublicaciones extends ArrayAdapter<Books> {
        List<Books> listapublicacion;
        public AdapterPublicaciones(Context context, List<Books> lista) {
            super(context, R.layout.lista, lista);
            listapublicacion = lista;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view= LayoutInflater.from(getContext()).inflate(R.layout.lista, null);
            TextView title= view.findViewById(R.id.titleL);
            TextView languaje= view.findViewById(R.id.languajeL);
            TextView genero= view.findViewById(R.id.generoL);
            TextView description= view.findViewById(R.id.descriptionL);
            TextView dateP= view.findViewById(R.id.date_publishL);
            ImageView imageBook = view.findViewById(R.id.imgBookL);

            title.setText(listapublicacion.get(position).getTitle());
            languaje.setText(listapublicacion.get(position).getIdioma());
            genero.setText(listapublicacion.get(position).getGenero());
            description.setText(listapublicacion.get(position).getDescription());
            dateP.setText(listapublicacion.get(position).getCreate_at());
            Picasso.get().load("http://192.168.56.1/bibliotecaISW/public/"+listapublicacion.get(position).getArchivo()).into(imageBook);
            return view;
        }
    }
    private void getPublicaciones(){
        Call<List<Books>> listCall = servicio.getPublicaciones();
        listCall.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                if(response.isSuccessful()){
                    //Log.d("ERROR",response.body().toString());
                    adapter = new AdapterPublicaciones(getApplicationContext(), response.body());
                    listView.setAdapter(adapter);
                    /*
                    List<Books> b = response.body();
                    for ( Books book: b){
                        Toast.makeText(getApplicationContext(), book.getTitle(),Toast.LENGTH_SHORT).show();
                    }*/
                }else{
                    Toast.makeText(getApplicationContext(), "nada que nostrar",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
                Toast.makeText(getApplicationContext(), "hola"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
