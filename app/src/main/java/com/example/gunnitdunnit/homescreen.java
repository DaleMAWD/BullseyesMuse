package com.example.gunnitdunnit;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        CardView cardView1 = findViewById(R.id.vectorCardview);
        CardView cardView2 = findViewById(R.id.czpistolCardview);
        CardView cardView3 = findViewById(R.id.mpfiveCardview);
        CardView cardView4 = findViewById(R.id.vhsCardview);


        cardView1.setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, vector.class);
            startActivity(intent);
        });


        cardView2.setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, czpistol.class);
            startActivity(intent);
        });


        cardView3.setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, mpfive.class);
            startActivity(intent);
        });


        cardView4.setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, vhs.class);
            startActivity(intent);
        });


        findViewById(R.id.HubLayout).setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, homescreen.class);
            startActivity(intent);
        });

        findViewById(R.id.searchLayout).setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, searchbar.class);
            startActivity(intent);
        });

        findViewById(R.id.myOrderLayout).setOnClickListener(view -> {
            Intent intent = new Intent(homescreen.this, myorder.class);
            startActivity(intent);
        });


    }
}