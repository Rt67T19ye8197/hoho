package com.example.hoho;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        //////DATA\\\\\\
        ItemRepository repository = new ItemRepository(this);
        Item item = (Item) repository.search("ddd", "ddd");
        //Item tr = repository.search("ffdgf", DatabaseContract.Item.COLUMN_WORLD);

        //////TEXT\\\\\\
        assert item != null;
        lvl.setText(item.getLLA());
        world.setText(item.getWorld());
        trans.setText(item.getTrans());

        //////IMAGE\\\\\\
        //resDrawable(item.getWorld(), imageView);
        imageView.setImageResource(resDrawable(item.getWorld()));
        buttonCat.setBackgroundResource(resDrawable(item.getWorld()));
        buttonLvl.setBackgroundResource(resDrawable(item.getWorld()));
        buttonLng.setBackgroundResource(resDrawable(item.getWorld()));
        if(item.isCheck()){
            buttonCheck.setBackgroundResource(resDrawable("check"));
        }

        buttonCat.setOnClickListener((v -> onClick("nnnn")));

    }

    Item data(){
        ItemRepository repository = new ItemRepository(this);

        // Получаем весь список
        List<Item> items = repository.getItems();
        for (Item item : items) {
            Log.d("DB", item.getLng() + " | flag = ");
        }

        // Получаем одну запись по id
        Item singleItem = (Item) repository.search("ddd", "ddd");
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


}