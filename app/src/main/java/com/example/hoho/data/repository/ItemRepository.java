package com.example.hoho.data.repository;

import android.content.Context;

import com.example.hoho.data.DAO.ItemDao;
import com.example.hoho.data.contract.DatabaseContract;
import com.example.hoho.data.entities.Item;

import java.util.List;

public class ItemRepository {
    private final ItemDao itemDao;

    public ItemRepository(Context context) {
        itemDao = new ItemDao(context);
    }

    public List<Item> getItems() {
        return itemDao.getAllItems();
    }

    /*public Item getItem(int id) {
        return itemDao.getItemById(id);
    }*/
    public List<Item> search(DatabaseContract selection, String query) {
        return itemDao.searchItems(selection, query);
    }
}
