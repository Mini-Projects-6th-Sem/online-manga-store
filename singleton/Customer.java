package singleton;

import model.Manga;

import java.util.HashMap;

public class Customer {
    private ShoppingCart shoppingCart;
    private String name;

    public Customer(String name) {
        shoppingCart = new ShoppingCart(this);
        this.name = name;
    }

    public void get(Manga manga) {
        shoppingCart.addManga(manga);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getName() {
        return name;
    }

    public void pay() {
        shoppingCart.iterations();
    }

    public void pack(Box box) {
        box.setCart(shoppingCart.getCart());
        shoppingCart.setCart(new HashMap<Manga, Integer>());
    }
}