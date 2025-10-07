package com.example.hoho.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoho.ImageAdapter;
import com.example.hoho.R;
import com.example.hoho.data.entities.Item;
import com.example.hoho.utils.UsingText;

import java.util.ArrayList;

public class MiniListAdapter extends ArrayAdapter<Item> {
    //адаптор для вывода большого списка
    private final LayoutInflater inflater;
    private final int layout;
    private final ArrayList<Item> list;

    public MiniListAdapter(Context context, int resource, ArrayList<Item> list) {
        super(context, resource, list);
        this.list = list;
        this.layout=resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = list.get(position);
        // Безопасная установка текста
        if (item != null) {
            String[] texts = {item.getWorld() + " ", item.getLvl() + " ", item.getLng()};
            int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
            UsingText.setColoredText(viewHolder.textView1, texts, colors);
            viewHolder.textView2.setText(item.getTrans() != null ? item.getTrans() : "");
        } else {
            viewHolder.textView1.setText("Null");
            viewHolder.textView2.setText("Null");
        }

        return convertView;
    }
    private static class ViewHolder {
        final TextView textView1, textView2;
        ViewHolder(View view){
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            if (textView1 == null || textView2 == null) {
                throw new IllegalStateException("TextView not found in layout");
            }
        }
    }
}
