package com.example.bibliotecaisw.Interface;

import com.example.bibliotecaisw.Model.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface miBibliotecaOnlineAPI {
    //accedo a la ruta para obtener los libras
    @GET("manage/libros")
    //aqui extragifo los datos en forma de lista
    Call<List<Books>> getPublicaciones();
}
