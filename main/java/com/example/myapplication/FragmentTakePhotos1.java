package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class FragmentTakePhotos1 extends Fragment {

    AMapLocationClient mapLocationClient  = null; // 定位客户端
    AMapLocationClientOption mapLocationClientOption = null; // 定位参数对象
    ImageButton start_position_Button = null; // 定位按钮
    TextView position_message = null;
    String result = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_takephotos1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRb();
        initPosition();
        initEvents();
    }

    private void initRb(){
        start_position_Button = getActivity().findViewById(R.id.get_position);
        position_message = getActivity().findViewById(R.id.show_position);
    }

    private void initEvents(){
        // 获取位置
        start_position_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("position","点击定位");
                startLocation();
            }
        });
    }
    private void startLocation(){
        try {
                mapLocationClient.startLocation();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initPosition(){
        try {
            AMapLocationClient.updatePrivacyShow(getContext(),true,true);
            AMapLocationClient.updatePrivacyAgree(getContext(),true);
                mapLocationClient = new AMapLocationClient(this.getContext());
                mapLocationClientOption = getDefaultOption();
                mapLocationClient.setLocationListener(locationListener);
                mapLocationClient.setLocationOption(mapLocationClientOption);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 定位默认参数
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return  mOption;
    }
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location != null){
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0){
                    sb.append(location.getCountry().trim()); //国
                    sb.append(","+location.getProvince().trim()); //省
                    sb.append(","+location.getCity().trim()); //市
                    sb.append(","+location.getDistrict().trim()); //区
                    sb.append(","+location.getAddress().trim()); // 地址
                }else{
                    sb.append("定位失败".trim());
                    sb.append(",错误码" + location.getErrorCode());
                    sb.append(",错误信息" + location.getErrorInfo().trim());
                    sb.append(", 错误描述" + location.getLocationDetail().trim());
                }

                result = sb.toString();
                position_message.setText(result);
            }

        }
    };
}