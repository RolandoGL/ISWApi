package com.example.bibliotecaisw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotecaisw.Config.Config;
import com.example.bibliotecaisw.Interface.miBibliotecaOnlineAPI;
import com.example.bibliotecaisw.Model.Books;

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

    private void getPublicaciones(){
        Call<List<Books>> listCall = servicio.getPublicaciones();
        listCall.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {

                if(response.isSuccessful()){
                    Log.d("ERROR",response.body().toString());
                    adapter = new AdapterPublicaciones(getApplicationContext(),response.body());
                    listView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
                Toast.makeText(getApplicationContext(), "hola"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
class AdapterPublicaciones extends ArrayAdapter<Books> {
    List<Books> listapublicacion;
    public AdapterPublicaciones(Context context, List<Books> lista) {
        super(context, R.layout.lista, lista);
        listapublicacion = lista;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.lista,null);
        TextView title= view.findViewById(R.id.tileBook);
        TextView author= view.findViewById(R.id.author);
        TextView genero= view.findViewById(R.id.genero);
        TextView description= view.findViewById(R.id.description);
        title.setText(listapublicacion.get(position).getTitle());
        author.setText(listapublicacion.get(position).getAutor());
        genero.setText(listapublicacion.get(position).getGenero());
        description.setText(listapublicacion.get(position).getDescription());
        return view;
    }
}