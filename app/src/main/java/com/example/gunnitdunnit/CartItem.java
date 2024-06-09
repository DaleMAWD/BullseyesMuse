// CartItem.java
package com.example.gunnitdunnit.;

public class CartItem {
    private int id;
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public CartItem(int id, String productId, String productName, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public byte[] getProductId() {
        return new byte[0];
    }

    public byte[] getProductName() {
        return new byte[0];
    }

    public byte[] getQuantity() {
        return new byte[0];
    }

    public byte[] getPrice() {
        return new byte[0];
    }


    // Getters and Setters
}
