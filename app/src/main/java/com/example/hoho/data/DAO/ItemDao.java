package com.example.hoho.data.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hoho.data.contract.DatabaseContract;
import com.example.hoho.data.db.AppDatabaseHelper;
import com.example.hoho.data.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDao {

    private AppDatabaseHelper dbHelper;

    public ItemDao(Context context) {
        dbHelper = new AppDatabaseHelper(context);
    }

    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("EN", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_LVL));
            //boolean flag = cursor.getInt(cursor.getColumnIndexOrThrow("flag")) == 1;
            list.add(new Item(id, name));
        }
        cursor.close();
        db.close();
        return list;
    }

    // поиск по имени
    public List<Item> searchItems(String query, String selection) {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "EN",
                null,
                selection + " = ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            list.add(parseItem(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Item> searchItems(DatabaseContract.Item selection, String query) {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "EN",
                null,
                selection + " = ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            list.add(parseItem(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 🔹 Универсальный поиск по нескольким условиям
     * Пример: filters.put("name", "cat"); filters.put("flag", "1");
     */
    public List<Item> searchItems(Map<String, String> filters) {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Собираем WHERE-условие
        StringBuilder whereClause = new StringBuilder();
        List<String> whereArgsList = new ArrayList<>();

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND ");
            }
            // для текстовых полей используем LIKE, для числовых - "="
            if (entry.getKey().equals(DatabaseContract.Item.COLUMN_CHECK)) {
                whereClause.append(entry.getKey()).append(" = ?");
                whereArgsList.add(entry.getValue());
            } else {
                whereClause.append(entry.getKey()).append(" LIKE ?");
                whereArgsList.add("%" + entry.getValue() + "%");
            }
        }

        String[] whereArgs = whereArgsList.toArray(new String[0]);

        Cursor cursor = db.query(
                DatabaseContract.TABLE_NAME,
                null,
                whereClause.length() > 0 ? whereClause.toString() : null,
                whereArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            list.add(parseItem(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    // Парсинг объекта из курсора
    private Item parseItem(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_ID));
        String lng = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_LNG));
        String lvl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_LVL));
        String article = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_ARTICLE));
        String world = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_WORLD));
        String trans = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Item.COLUMN_TRANS));
        return new Item(id, lng, lvl, article, world, trans);
    }
}
