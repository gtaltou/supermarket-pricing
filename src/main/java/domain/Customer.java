package domain;

import java.util.LinkedHashMap;

public class Customer {

    private LinkedHashMap cart = new LinkedHashMap<ProductItem, Float>();


    public LinkedHashMap getCart() {
        return cart;
    }

    public void setCart(LinkedHashMap cart) {
        this.cart = cart;
    }
}
