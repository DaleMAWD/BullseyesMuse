package com.example.gunnitdunnit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.activity.EdgeToEdge;

public class searchbar extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText searchEditText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> itemList;
    private Map<String, Class<?>> itemToActivityMap;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_bar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Initialize itemList and itemToActivityMap
        itemList = new ArrayList<>();
        itemToActivityMap = new HashMap<>();

        // Populate the item list and map from the database
        loadItemsFromDatabase();

        searchEditText = findViewById(R.id.Searchbar);
        listView = findViewById(R.id.Searchconfirm);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String itemName = adapter.getItem(position);
            Class<?> activityClass = itemToActivityMap.get(itemName);
            if (activityClass != null) {
                Intent intent = new Intent(searchbar.this, activityClass);
                startActivity(intent);
            }
        });

        // Example insert (for testing purposes)
        insertSampleData();

        dbManager.close();

        setupUIComponents();
    }

    private void loadItemsFromDatabase() {
        Cursor cursor = dbManager.getAllRecords();
        if (cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(MyDatabaseContract.MyTable.COLUMN_NAME);
                int layoutNameIndex = cursor.getColumnIndex(MyDatabaseContract.MyTable.COLUMN_LAYOUT_NAME);

                if (nameIndex != -1 && layoutNameIndex != -1) {
                    String name = cursor.getString(nameIndex);
                    String layoutName = cursor.getString(layoutNameIndex);

                    itemList.add(name);
                    try {
                        Class<?> activityClass = Class.forName("com.example.challichalli." + layoutName);
                        itemToActivityMap.put(name, activityClass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void insertSampleData() {

        String[][] items = {
                {"CZ Pistol", "czpistol"},
                {"MP5K", "mp5k"},
                {"Vector G2", "vectorg2"},
                {"Mini-14", "mini14"},
                {"M16A1", "m16a1"},
                {"AK47", "ak47"},
                {"VHS", "activity_red_velvet_cake"},
                {"Grenade", "grenade"},
                {"Ariana Granade", "ariana"},
                {"Smoke", "smoke"},
                {"JPC Vest", "jpcvest"},
                {"CPC Vest", "cpcvest"},
                {"Benelli", "benelli"},
        };

        for (String[] item : items) {
            dbManager.insert(item[0], 0, item[1]);
        }

        dbManager.close();
    }

    private void setupUIComponents() {
        ImageView validateButton = findViewById(R.id.searchbutton);
        validateButton.setOnClickListener(v -> {
            String enteredPassword = searchEditText.getText().toString();
            Class<?> activityClass = itemToActivityMap.get(enteredPassword);
            if (activityClass != null) {
                Intent intent = new Intent(searchbar.this, activityClass);
                startActivity(intent);
            } else {
                Toast.makeText(searchbar.this, "Item not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


