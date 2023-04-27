package com.ooadproject.onlinemangastore.singleton;

import com.ooadproject.onlinemangastore.model.Manga;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart {
    private Map<Manga, Integer> cart;
    private Customer owner;
    private PriceList priceList = PriceList.getInstance();

    public ShoppingCart(Customer owner) {
        cart = new HashMap<Manga, Integer>();
        this.owner = owner;
    }

    public void addManga(Manga manga) {
        cart.put(manga, priceList.get(manga.getMangaName()));
    }

    public void iterations() {
        Iterator<Map.Entry<Manga, Integer>> iterator = cart.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() == null)
                iterator.remove();
        }
    }

    public int getPrice() {
        int price = 0;
        for (Manga manga : cart.keySet()) {
            for (int i = 0; i < manga.getAvailableQuantity(); i++) {
                if (price + cart.get(manga) > owner.getCash())
                    break;
                else
                    price += cart.get(manga);
            }
        }
        return price;
    }

    @Override
    public String toString() {
        if (cart.size() == 0)
            return "No items in " + owner.getName() + "\'s cart";
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("Items in " + owner.getName() + "\'s cart:\n");
            for (Manga manga : cart.keySet()) {
                builder.append(manga + "\n");
            }
            return builder.toString();
        }

    }

    public Map<Manga, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Manga, Integer> cart) {
        this.cart = cart;
    }
}