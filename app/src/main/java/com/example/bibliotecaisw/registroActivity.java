package com.example.bibliotecaisw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotecaisw.Config.Config;
import com.example.bibliotecaisw.Interface.miBibliotecaOnlineAPI;
import com.example.bibliotecaisw.Model.userLogin;
import com.example.bibliotecaisw.Model.userRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registroActivity extends AppCompatActivity {
    private  miBibliotecaOnlineAPI servicio = Config.getRetrofit().create(miBibliotecaOnlineAPI.class);
    private TextView name, email, password, password_confirmation, error, userEmail, userPass, errorLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        name = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
        error = findViewById(R.id.n);
        userEmail = findViewById(R.id.userName);
        userPass = findViewById(R.id.userPass);
        errorLog = findViewById(R.id.error);
    }

    public void usersRegister(View v){
        String nombre = name.getText().toString(), correo = email.getText().toString(), contraseña = password.getText().toString(), confirmaP = password_confirmation.getText().toString();
        Call <userRegister> call = servicio.createUsers(nombre, correo, contraseña);
        call.enqueue(new Callback<userRegister>() {
            @Override
            public void onResponse(Call<userRegister> call, Response<userRegister> response) {
                if(response.isSuccessful()){
                    //Config.Mensaje(getApplicationContext(), "Registro exitoso");
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    password_confirmation.setText("");
                    error.setText("Registro exitoso");
                }else{
                    //Config.Mensaje(getApplicationContext(), "Error durante el resgitro");
                    error.setText("error al registrar");
                }
            }
            @Override
            public void onFailure(Call<userRegister> call, Throwable t) {
                //Config.Mensaje(getApplicationContext(), t.getMessage());
                error.setText("error"+t.getMessage());
            }
        });
    }

}