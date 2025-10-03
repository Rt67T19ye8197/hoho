package com.example.hoho;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;
import android.util.LogPrinter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.hoho.ui.activity.MiniListActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    //адптер для стартового окна. отвечает за выведение картинок в виде плиток
    // с кликом перехода на активность
    private final Context context;
    private final ArrayList<Integer> list;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    private OnItemClickListener listener;

    public ImageAdapter(Context context, ArrayList<Integer> list){
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                context.getResources().getDisplayMetrics());

        if(convertView == null){
            imageView = new ImageView(context);
            //int size = parent.getWidth();
            imageView.setLayoutParams(new ViewGroup.LayoutParams(dp * 80, dp * 80));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 10, 0);

            Log.d("MyLOG", "создаём");
        }else {
            imageView = (ImageView) convertView;
            Log.d("MyLOG", "not");
        }

        imageView.setImageResource(list.get(position));
        /*
        //обработка клика из активности
        imageView.setOnClickListener(v -> {
            if(listener != null){
                listener.OnItemClick(position);
            }
        });
         */

        //обрабодка клика прямо тут
        imageView.setOnClickListener(v -> {
            //оброботка нажатия
            Intent intent = new Intent(context, MiniListActivity.class);
            context.startActivity(intent);
        });
        return imageView;
    }
}
