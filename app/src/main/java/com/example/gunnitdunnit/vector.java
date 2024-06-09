package com.example.gunnitdunnit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class vector extends AppCompatActivity {

    private static final String TAG = "vector";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vectorg2);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else {
            Log.e(TAG, "View with ID 'main' not found in layout 'vectorg2'.");
        }

        findViewById(R.id.HubLayout).setOnClickListener(view -> {
            Intent intent = new Intent(vector.this, homescreen.class);
            startActivity(intent);
        });

        findViewById(R.id.searchLayout).setOnClickListener(view -> {
            Intent intent = new Intent(vector.this, searchbar.class);
            startActivity(intent);
        });

        findViewById(R.id.myOrderLayout).setOnClickListener(view -> {
            Intent intent = new Intent(vector.this, myorder.class);
            startActivity(intent);
        });
    }
}