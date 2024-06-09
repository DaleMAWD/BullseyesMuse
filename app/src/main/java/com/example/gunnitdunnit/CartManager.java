// CartManager.java
package com.example.gunnitdunnit;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private DBHelper dbHelper;

    public CartManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addToCart(CartItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_PRODUCT_ID, item.getProductId());
        values.put(DBHelper.COLUMN_PRODUCT_NAME, item.getProductName());
        values.put(DBHelper.COLUMN_QUANTITY, item.getQuantity());
        values.put(DBHelper.COLUMN_PRICE, item.getPrice());
        db.insert(DBHelper.TABLE_CART, null, values);
        db.close();
    }

    public List<CartItem> getAllCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CART, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                @SuppressLint("Range") String productId = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PRODUCT_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_QUANTITY));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_PRICE));

                CartItem item = new CartItem(id, productId, productName, quantity, price);
                cartItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartItems;
    }

    // Add more methods as needed (e.g., remove item, update quantity, etc.)
}

