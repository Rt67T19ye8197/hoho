package com.example.hoho;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hoho.data.contract.DatabaseContract;
import com.example.hoho.data.entities.Item;
import com.example.hoho.data.repository.ItemRepository;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    //ОСНОВНОЕ ОКНО С КАРТОЧКАМИ

    TextView lvl, world, trans, text, textTrans, textProm;
    View buttonCat, buttonLvl, buttonCheck, buttonLng, buttonPlay, buttonMenu;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        windowPadding();

        lvl = findViewById(R.id.textView);
        world = findViewById(R.id.textView2);
        trans = findViewById(R.id.textView3);
        text = findViewById(R.id.textView4);
        textTrans = findViewById(R.id.textView5);
        textProm = findViewById(R.id.textView6);

        buttonCat = findViewById(R.id.cat);
        buttonLvl = findViewById(R.id.lvl);
        buttonCheck = findViewById(R.id.check);
        buttonLng = findViewById(R.id.lng);
        buttonPlay = findViewById(R.id.play);
        buttonMenu = findViewById(R.id.menu);

        imageView = findViewById(R.id.imageView);

        //////TEG\\\\\\
        Bundle arguments = getIntent().getExtras();
        String query = arguments.getString("QUERY");

        //////DATA\\\\\\
        ItemRepository repository = new ItemRepository(this);
        List<Item> list = repository.search(DatabaseContract.WORLD, query);
        Item item = list.get(0);
        Log.d("MyLOG", "DATA" + list.size());
        Log.d("MyLOG", "TEXT" + query);
        /// СПАТЬ охото влепил костыль

        //////TEXT\\\\\\
        assert item != null;
        lvl.setText(item.getLLA());
        world.setText(item.getWorld());
        trans.setText(item.getTrans());
        Log.d("MyLOG", "TEXT");

        //////IMAGE\\\\\\
        //resDrawable(item.getWorld(), imageView);
        imageView.setImageResource(resDrawable(item.getWorld()));
        buttonCat.setBackgroundResource(resDrawable(item.getWorld()));
        buttonLvl.setBackgroundResource(resDrawable(item.getWorld()));
        buttonLng.setBackgroundResource(resDrawable(item.getWorld()));
        Log.d("MyLOG", "TEXT");
        if(item.isCheck()){
            buttonCheck.setBackgroundResource(resDrawable("check"));
        }
        Log.d("MyLOG", "TEXT");
        buttonCat.setOnClickListener((v -> onClick("nnnn")));
        Log.d("MyLOG", "TEXT");
    }

    Item data(){
        ItemRepository repository = new ItemRepository(this);

        // Получаем весь список
        List<Item> items = repository.getItems();
        for (Item item : items) {
            Log.d("DB", item.getLng() + " | flag = ");
        }

        // Получаем одну запись по id
        Item singleItem = (Item) repository.search(DatabaseContract.WORLD,"ddd" );
        if (singleItem != null) {
            Log.d("DB", "Поиск по ID=3: " + singleItem.getLng() + " | flag = ");
        } else {
            Log.d("DB", "Запись с таким ID не найдена");
        }
        return singleItem;
    }

    void resDrawable(String imageName, ImageView imageView){
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        // Проверяем, найден ли ресурс
        if (resId != 0) {
            imageView.setImageResource(resId);
        } else {
            // Если картинки нет — ставим заглушку
            imageView.setImageResource(R.drawable.house);
        }
    }

    public void onClick(String number){

    }

    int resDrawable(String imageName){
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        // Проверяем, найден ли ресурс
        if (resId != 0) {
            return resId;
        } else {
            // Если картинки нет — ставим заглушку
            return R.drawable.house;
        }
    }

    private void windowPadding(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}