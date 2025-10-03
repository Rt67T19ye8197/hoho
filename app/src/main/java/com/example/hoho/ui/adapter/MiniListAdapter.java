package com.example.hoho.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoho.R;
import com.example.hoho.data.entities.Item;

import java.util.ArrayList;

public class MiniListAdapter extends ArrayAdapter<Item> {
    //адаптор для вывода большлго списка
    //!!!требует оптимитизации
    private LayoutInflater inflater;
    private int layout;
    private final Context context;
    private final ArrayList<Item> list;

    public MiniListAdapter(Context context, int resource, ArrayList<Item> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
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

        viewHolder.textView1.setText(item.getWorld());
        viewHolder.textView2.setText(item.getTrans());

        return convertView;
    }

    private static class ViewHolder {
        final TextView textView1, textView2;
        ViewHolder(View view){
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
        }
    }

}
