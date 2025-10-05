package com.example.hoho.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hoho.R;
import com.example.hoho.data.contract.DatabaseContract;
import com.example.hoho.data.entities.Item;
import com.example.hoho.data.repository.ItemRepository;
import com.example.hoho.ui.adapter.MiniListAdapter;

import java.util.ArrayList;

public class MiniListActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mini_list);
        windowPadding();
        Bundle arguments = getIntent().getExtras();
        String teg = arguments.get("TEG").toString();
        // начальная инициализация списка
        //setInitialData();
        ItemRepository repository = new ItemRepository(this);
        items = (ArrayList<Item>) repository.search(DatabaseContract.TEG, teg);
        // получаем элемент ListView
        countriesList = findViewById(R.id.list);
        // создаем адаптер
        MiniListAdapter stateAdapter = new MiniListAdapter(this, R.layout.grid_mini_list, items);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                Item item = (Item) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + item.getWorld(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
        ItemRepository repository = new ItemRepository(this);
        items = (ArrayList<Item>) repository.getItems();
        items.add(new Item ("Бразилия", "Бразилиа"));
        items.add(new Item ("Аргентина", "Буэнос-Айрес"));
        items.add(new Item ("Колумбия", "Богота"));
        items.add(new Item ("Уругвай", "Монтевидео"));
        items.add(new Item ("Чили", "Сантьяго"));
    }

    private void windowPadding(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}