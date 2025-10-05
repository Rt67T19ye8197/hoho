package com.example.hoho.data.contract;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {}
    public static final String TABLE_NAME = "EN";
    public static class Item implements BaseColumns {

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEG = "teg";
        public static final String COLUMN_LNG = "lng";
        public static final String COLUMN_LVL = "lvl";
        public static final String COLUMN_ARTICLE = "article";
        public static final String COLUMN_CHECK = "check";
        public static final String COLUMN_WORLD = "world";
        public static final String COLUMN_TRANS = "trans";
        public static final String COLUMN_PROM = "prom";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_TEXT_TRANS = "textTrans";
        public static final String COLUMN_TEXT_PRON = "textPron";
    }
}
