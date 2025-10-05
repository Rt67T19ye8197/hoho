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

import com.example.hoho.ui.entitiles.IntAndString;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //СТАРТОВОЕ ОКНО

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        windowPadding();

        GridView gridView1 = findViewById(R.id.grid1);
        GridView gridView2 = findViewById(R.id.grid2);

        // создаем адаптер
        ImageAdapter adapter = new ImageAdapter(this, isList1());
        //ImageAdapter adapter2 = new ImageAdapter(this, isList2());
        gridView1.setAdapter(adapter);
        //gridView2.setAdapter(adapter2);
    }

    private ArrayList<IntAndString> isList1(){
        ArrayList<IntAndString> list = new ArrayList<>();
        list.add(new IntAndString(R.drawable.house, "house"));
        list.add(new IntAndString(R.drawable.animals, "animals"));
        list.add(new IntAndString(R.drawable.human, "human"));
        list.add(new IntAndString(R.drawable.colors, "colors"));
        list.add(new IntAndString(R.drawable.plants, "plants"));
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

    private void windowPadding(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}