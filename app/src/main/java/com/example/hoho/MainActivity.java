package com.example.hoho;
/// перенос кликов в кастомный адаптор
/// повышение функционала списка

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView1;
    private GridView gridView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gridView1 = findViewById(R.id.grid1);
        gridView2 = findViewById(R.id.grid2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // создаем адаптер
        ImageAdapter adapter = new ImageAdapter(this, isList1());
        ImageAdapter adapter2 = new ImageAdapter(this, isList2());
        gridView1.setAdapter(adapter);
        gridView2.setAdapter(adapter2);
    }



    private ArrayList<Integer> isList1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.house);
        list.add(R.drawable.animals);
        list.add(R.drawable.human);
        list.add(R.drawable.colors);
        list.add(R.drawable.plants);
        return list;
    }
    private ArrayList<Integer> isList2(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.house);
        list.add(R.drawable.house);
        list.add(R.drawable.house);
        list.add(R.drawable.house);
        return list;
    }

}