package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentNews extends ListFragment {

    private List<News> newsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_newslist, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://49.235.134.191:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebService appService = retrofit.create(WebService.class);
        appService.getNews().enqueue(new Callback<HttpResult<List<News>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<News>>> call, Response<HttpResult<List<News>>> response) {
                HttpResult<List<News>> list = response.body();
                if (list != null){
                    newsList.addAll(list.getData());
                    if (list.getCode().equals("200")){
                        FragmentActivity activity = getActivity();
                        Toast.makeText(activity,"刷新成功",Toast.LENGTH_SHORT).show();
                    }
                    FragmentActivity activity = getActivity();
                    FragmentNewsAdapter fragmentNewsAdapter = new FragmentNewsAdapter(activity,R.layout.newslist_item,newsList);
                    setListAdapter(fragmentNewsAdapter);
                }

            }

            @Override
            public void onFailure(Call<HttpResult<List<News>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }
}
