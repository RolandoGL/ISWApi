package com.example.bibliotecaisw.Interface;

import com.example.bibliotecaisw.Model.Books;
import com.example.bibliotecaisw.Model.userLogin;
import com.example.bibliotecaisw.Model.userRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface miBibliotecaOnlineAPI {
    //accedo a la ruta para obtener los libras
    @GET("manage/libros")
    //aqui extragifo los datos en forma de lista
    Call<List<Books>> getPublicaciones();
    @POST("manage/auth/register")
    @FormUrlEncoded
    Call<userRegister> createUsers( @Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password);
    @POST("manage/auth/login")
    @FormUrlEncoded
    Call<userLogin> login(
            @Field("email") String name,
            @Field("password") String email);

}
