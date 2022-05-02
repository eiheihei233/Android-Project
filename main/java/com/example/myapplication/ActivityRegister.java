package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_register);

        Button button = findViewById(R.id.reg_button);
        EditText userName = findViewById(R.id.username_reg_input);
        EditText password = findViewById(R.id.password_reg_input);
        EditText confirm = findViewById(R.id.password_reg_confirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cf = confirm.getText().toString().trim();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://49.235.134.191:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WebService appService = retrofit.create(WebService.class);
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(ActivityRegister.this,"内容不可为空",Toast.LENGTH_SHORT).show();
                }else if(!cf.equals(pass)){
                    Toast.makeText(ActivityRegister.this,"两次密码输入不一致" + cf + pass,Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirm.setText("");
                }else{
                    appService.gotoRegister(name,pass).enqueue(new Callback<HttpResult<Boolean>>() {
                        @Override
                        public void onResponse(Call<HttpResult<Boolean>> call, Response<HttpResult<Boolean>> response) {

                            HttpResult<Boolean> list = response.body();

                            if (list != null){
                                Log.d("ActivityRegister",list.getCode());
                                Log.d("ActivityRegister",list.getMessage());
                                Log.d("ActivityRegister",list.getData().toString());
                                if (list.getCode().equals("200")){
                                    Intent intent1 = new Intent(ActivityRegister.this,ActivityLogin.class);
                                    startActivity(intent1);
                                    finish();
                                }else{
                                    Toast.makeText(ActivityRegister.this,"错误" + list.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<HttpResult<Boolean>> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });
                }
            }
        });
    }
}