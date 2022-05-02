package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.button1);
        EditText userNameText = findViewById(R.id.username_input);
        EditText passwordText = findViewById(R.id.password_input);
        TextView regButton = findViewById(R.id.goto_register);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameText.getText().toString();
                String password = passwordText.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://49.235.134.191:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WebService appService = retrofit.create(WebService.class);
                appService.gotoLogin(name,password).enqueue(new Callback<HttpResult<Boolean>>() {
                    @Override
                    public void onResponse(Call<HttpResult<Boolean>> call, Response<HttpResult<Boolean>> response) {
                        HttpResult<Boolean> list = response.body();

                        if (list != null){
                            if (list.getCode().equals("200")){
                                Intent intent1 = new Intent(ActivityLogin.this,MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }else{
                                Toast.makeText(ActivityLogin.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<Boolean>> call, Throwable t) {
                        t.printStackTrace();

                    }
                });
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ActivityLogin.this,ActivityRegister.class);
                startActivity(intent2);

            }
        });
    }
}