package com.example.hoho.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AppDatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "db.db"; // имя файла в assets
    private static String DB_PATH = "";       // путь куда скопировать
    private Context context;

    public AppDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = context.getDatabasePath(DB_NAME).getPath();
        copyDatabaseIfNeeded();
    }

    private void copyDatabaseIfNeeded() {
        File dbFile = new File(DB_PATH);
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            copyDatabaseFromAssets();
        }
    }

    private void copyDatabaseFromAssets() {
        //получаем локальную бд как поток
        try (InputStream input = context.getAssets().open(DB_NAME);
             // Открываем пустую бд
             OutputStream output = new FileOutputStream(DB_PATH)) {

            // побайтово копируем данные
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при копировании базы данных", e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Не нужен, т.к. база уже готова
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Здесь можно реализовать обновление базы при необходимости
    }
}

