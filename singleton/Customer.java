package singleton;

import model.Manga;

import java.util.HashMap;

public class Customer {
    private ShoppingCart shoppingCart;
    private String name;
    private int money;

    public Customer(String name, int money) {
        shoppingCart = new ShoppingCart(this);
        this.name = name;
        this.money = money;
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
        money -= shoppingCart.getPrice();
    }

    public int getCash() {
        return money;
    }

    public void pack(Box box) {
        box.setCart(shoppingCart.getCart());
        shoppingCart.setCart(new HashMap<Manga, Integer>());
    }
}