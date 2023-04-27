package com.ooadproject.onlinemangastore.singleton;

import com.ooadproject.onlinemangastore.model.Manga;

import java.util.Map;

public class Box {
    private Map<Manga, Integer> cart;
    private Customer customer;

    public Box(Customer customer) {
        this.customer = customer;
        cart = customer.getShoppingCart().getCart();
    }

    public void setCart(Map<Manga, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        if (cart.size() == 0)
            return "No items in " + customer.getName() + "\'s cart";
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("Items in " + customer.getName() + "\'s cart:\n");
            for (Manga manga : cart.keySet()) {
                builder.append(manga + "\n");
            }
            return builder.toString();
        }

    }
}