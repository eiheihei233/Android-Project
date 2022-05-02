package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //声明四个布局文件
    LinearLayout newsTab = null;
    LinearLayout homeTab = null;
    LinearLayout phoneTab1 = null;
    LinearLayout phoneTab2 = null;

    //声明四个Tab所对应的Fragment
    Fragment fragmentNewList = null; // 0
    Fragment fragmentPhotos1 = null; // 1
    Fragment fragmentHome = null;    // 2
    Fragment fragmentPhotos2 = null; // 3
    FragmentManager manager = getSupportFragmentManager();

    private void initViews(){
        newsTab = (LinearLayout) findViewById(R.id.news_tab);
        phoneTab1 = (LinearLayout) findViewById(R.id.photo_tab1);
        phoneTab2 = (LinearLayout) findViewById(R.id.photo_tab2);
        homeTab = (LinearLayout) findViewById(R.id.home_tab);
    }

    private void initEvent(){
        //初始化点击事件
        newsTab.setOnClickListener(this);
        homeTab.setOnClickListener(this);
        phoneTab1.setOnClickListener(this);
        phoneTab2.setOnClickListener(this);
    }


    // 隐藏所有的Fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if (fragmentHome != null){
            transaction.hide(fragmentHome);
        }
        if (fragmentNewList != null){
            transaction.hide(fragmentNewList);
        }
        if (fragmentPhotos1 != null){
            transaction.hide(fragmentPhotos1);
        }
        if (fragmentPhotos2 != null){
            transaction.hide(fragmentPhotos2);
        }
    }

        //对选中的Tab进行处理
    private void selectTab(int i){
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideAllFragment(transaction);
        //判断点击事件
        if (i == 0){
            if(fragmentNewList == null){
                fragmentNewList = new FragmentNews();
                transaction.add(R.id.newsList_fragment,fragmentNewList);
            }else{
                transaction.show(fragmentNewList);
            }
        }else if(i == 1){
            if (fragmentPhotos1 == null){
                fragmentPhotos1 = new FragmentTakePhotos1();
                transaction.add(R.id.newsList_fragment,fragmentPhotos1);
            }else{
                transaction.show(fragmentPhotos1);
            }
        }else if(i == 2){
            if (fragmentHome == null){
                fragmentHome = new FragmentHome();
                transaction.add(R.id.newsList_fragment,fragmentHome);
            }else{
                transaction.show(fragmentHome);
            }
        }else if(i == 3){
            if (fragmentPhotos2 == null){
                fragmentPhotos2 = new FragmentTakePhotos2();
                transaction.add(R.id.newsList_fragment,fragmentPhotos2);
            }else{
                transaction.show(fragmentPhotos2);
            }
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
        selectTab(2);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.news_tab : selectTab(0); break;
            case R.id.photo_tab1 : selectTab(1); break;
            case R.id.home_tab : selectTab(2); break;
            case R.id.photo_tab2 : selectTab(3); break;
            default: selectTab(0);
        }
    }

}