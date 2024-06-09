// MainActivity.java
package com.example.gunnitdunnit.;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class cart extends AppCompatActivity {
    private EditText productIdEditText, productNameEditText, quantityEditText, priceEditText;
    private Button addToCartButton;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        productIdEditText = findViewById(R.id.productIdEditText);
        productNameEditText = findViewById(R.id.productNameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        priceEditText = findViewById(R.id.priceEditText);
        addToCartButton = findViewById(R.id.addToCartButton);
        cartManager = new CartManager(this);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productId = productIdEditText.getText().toString();
                String productName = productNameEditText.getText().toString();
                int quantity = Integer.parseInt(quantityEditText.getText().toString());
                double price = Double.parseDouble(priceEditText.getText().toString());

                CartItem cartItem = new CartItem(0, productId, productName, quantity, price);
                cartManager.addToCart(cartItem);

                Toast.makeText(cart.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

