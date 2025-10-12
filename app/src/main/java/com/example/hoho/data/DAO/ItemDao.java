package com.example.hoho.data.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(DatabaseContract.TABLE_NAME, null, null, null, null, null, null)) {

            while (cursor.moveToNext()) {
                list.add(parseItem(cursor));
            }
        }
        return  list;
    }

    // Поиск по одному условию
    public List<Item> searchItems(DatabaseContract selection, String query) {
        List<Item> list = new ArrayList<>();

        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(DatabaseContract.TABLE_NAME, null, selection + " like ?",
                     new String[]{"%" + query + "%"}, null, null, null)) {

            while (cursor.moveToNext()) {
                list.add(parseItem(cursor));
            }
        }
        return  list;
    }

    /**
     * 🔹 Универсальный поиск по нескольким условиям
     * Пример: filters.put("name", "cat"); filters.put("flag", "1");
     */
    public List<Item> searchItems(Map<DatabaseContract, String> filters) {
        // Собираем WHERE-условие
        StringBuilder whereClause = new StringBuilder();
        List<String> whereArgsList = new ArrayList<>();
        for (Map.Entry<DatabaseContract, String> entry : filters.entrySet()) {
            if (whereClause.length() > 0) {
                whereClause.append(" OR ");
            }
            whereClause.append(entry.getKey().getColumnName()).append(" LIKE ?");
            whereArgsList.add("%" + entry.getValue() + "%");
        }
        String[] whereArgs = whereArgsList.toArray(new String[0]);
        List<Item> list = new ArrayList<>();
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query( DatabaseContract.TABLE_NAME, null, whereClause.length() > 0 ? whereClause.toString() : null,
                     whereArgs, null, null, null)) {
            while (cursor.moveToNext()) {
                list.add(parseItem(cursor));
            }
        }
        return  list;
    }

    // Парсинг объекта из курсора
    private Item parseItem(Cursor cursor) {
        return new Item(
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ID.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.LNG.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.LVL.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ARTICLE.getColumnName())),
                true,
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.WORLD.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TRANS.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PROM.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TEXT.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TEXT_TRANS.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TEXT_PRON.getColumnName())),
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TEG.getColumnName()))
        );
    }
}
