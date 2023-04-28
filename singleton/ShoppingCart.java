package singleton;

import model.Manga;
import view.MangaView;

import dao.MangaDAOImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import controller.MangaController;

public class ShoppingCart {
    private Map<Manga, Integer> cart;

    private Customer owner;
    private PriceList priceList = PriceList.getInstance();

    Scanner scanner = new Scanner(System.in);

    public void displayCart() throws SQLException {
        MangaDAOImpl mangaDAOImpl = new MangaDAOImpl();
        MangaController mangaController = new MangaController();
        MangaView mangaView = new MangaView(mangaController);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. View all manga");
            System.out.println("2. View cart");
            System.out.println("3. add to cart");
            System.out.println("3. Remove from cart");
            System.out.println("5. Generate bill and checkout");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    mangaView.viewAllMangas();
                    break;
                case 2:
                    System.out.printf("%s\n\n", toString());
                    break;
                case 3:
                    int ID = scanner.nextInt();
                    addManga(mangaDAOImpl.getMangaById(ID));
                    System.out.printf("%s\n\n", toString());
                case 6:
                    return;

                default:
                    break;
            }
        }
    }

    public ShoppingCart(Customer owner) {
        cart = new HashMap<Manga, Integer>();
        this.owner = owner;
    }

    public void addManga(Manga manga) {
        cart.put(manga, priceList.get(manga.getTitle()));
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
            for (int i = 0; i < manga.getStock(); i++) {
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