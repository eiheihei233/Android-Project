package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FragmentNewsAdapter extends ArrayAdapter {

    Activity activity;
    int resourceID;
    List<News> data = new ArrayList<>();


    public FragmentNewsAdapter(@NonNull Activity activity, int resourceID, @NonNull List<News> data) {
        super(activity, resourceID, data);
        this.activity = activity;
        this.resourceID = resourceID;
        this.data.addAll(data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
        TextView title = view.findViewById(R.id.news_title);
        TextView content = view.findViewById(R.id.news_content);
        TextView date = view.findViewById(R.id.news_date);
        ImageView imageView = view.findViewById(R.id.news_img);
        News curItem = (News) getItem(position);
        if(curItem!=null){
            title.setText(curItem.getTitle());
            content.setText(curItem.getDesc());
            date.setText(curItem.getPublishTime());
            Glide.with(getContext()).load(curItem.getImageUrl()).into(imageView);
        }

        return view;
    }
}
