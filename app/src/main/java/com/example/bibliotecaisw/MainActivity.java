package com.example.bibliotecaisw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bibliotecaisw.Config.Config;
import com.example.bibliotecaisw.Interface.miBibliotecaOnlineAPI;
import com.example.bibliotecaisw.Model.userLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private miBibliotecaOnlineAPI servicio = Config.getRetrofit().create(miBibliotecaOnlineAPI.class);
    private TextView name, email,txt, password, password_confirmation, error, userEmail, userPass, errorLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEmail = findViewById(R.id.userName);
        userPass = findViewById(R.id.userPass);
        errorLog = findViewById(R.id.error);
        txt = findViewById(R.id.txt);
    }
    public void crear(View view){
        Intent intent = new Intent(this, Activity_books.class);
        startActivity(intent);

    }
    public void login(View view){
        Call<userLogin> call = servicio.login(userEmail.getText().toString(), userPass.getText().toString());
        call.enqueue(new Callback<userLogin>() {
            @Override
            public void onResponse(Call<userLogin> call, Response<userLogin> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), Activity_books.class);
                    startActivity(intent);
                    txt.setText("Bienvenido "+userEmail.getText());
                }else {
                    errorLog.setText(response.message());
                }
            }

            @Override
            public void onFailure(Call<userLogin> call, Throwable t) {

            }
        });
    }
}