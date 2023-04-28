import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import controller.MangaController;
import controller.UserController;
import view.MangaView;
import singleton.Customer;
import singleton.ShoppingCart;
import view.UserView;

public class Main {
    public static void main(String[] args) throws ParseException, SQLException {
        UserController userController = new UserController();
        UserView userView = new UserView(userController);
        MangaController bookController = new MangaController();
        MangaView bookView = new MangaView(bookController);
        Customer customer= new Customer("Nikith");
        ShoppingCart shoppingCart = new ShoppingCart(customer);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Manage Mangas");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Orders");
            System.out.println("4. Show Notifications");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookView.showMangaMenu();
                    break;
                case 2:
                    userView.displayUserMenu();
                    break;
                case 3:
                    shoppingCart.displayCart();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }
}
