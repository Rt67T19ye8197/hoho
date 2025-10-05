package com.example.hoho.data.contract;

import android.provider.BaseColumns;

public enum DatabaseContract {

    ID("id"),
    LNG("lng"),
    LVL("lvl"),
    ARTICLE("article"),
    CHECK("check"),
    WORLD("world"),
    TRANS("trans"),
    PROM("prom"),
    TEXT("text"),
    TEXT_TRANS("textTrans"),
    TEXT_PRON("textPron");

    private final String columnName;

    DatabaseContract(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
    public static final String TABLE_NAME = "EN";
}
