package com.example.hoho.utils;

import com.example.hoho.R;
import android.content.Context;
import android.widget.ImageView;

public class ResDrawable {
    private final Context context;

    public ResDrawable(Context context){
        this.context = context;
    }
    void resDrawable(String imageName, ImageView imageView){
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Проверяем, найден ли ресурс
        if (resId != 0) {
            imageView.setImageResource(resId);
        } else {
            // Если картинки нет — ставим заглушку
            imageView.setImageResource(R.drawable.house);
        }
    }

    int resDrawable(String imageName){
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        // Проверяем, найден ли ресурс
        if (resId != 0) {
            return resId;
        } else {
            // Если картинки нет — ставим заглушку
            return R.drawable.house;
        }
    }

    private void rtrt(){
        String sr = DayOfWeek.FRIDAY.toString();
    }
}
