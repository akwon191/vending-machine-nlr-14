package com.techelevator.util;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;
private String message = setMessage();

    public String getMessage() {
        return message;
    }

    public String setMessage() {
        if (getClass().equals(Gum.class)){
            return "Chew Chew, Yum!";
        } else if (getClass().equals(Drink.class)) {
            return "Glug Glug, Yum!";
        } else if (getClass().equals(Candy.class)) {
            return "Munch Munch, Yum!";
        } else if (getClass().equals(Chip.class)) {
            return "Crunch Crunch, Yum!";
        } else {return "Yummy Product";}
    }

    public Product(){}
    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
