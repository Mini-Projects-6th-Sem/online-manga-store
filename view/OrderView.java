package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.MangaController;
import controller.OrderController;
import controller.UserController;
import model.Manga;
import model.Order;
import model.User;

public class OrderView {
    private OrderController borrowingController;

    public OrderView() {
        borrowingController = new OrderController();
    }

    public void displayOrders() throws ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Orders ====");
        System.out.println("1. View All Orders");
        System.out.println("2. Add New Order");
        System.out.println("3. Return a Borrowed Manga");
        System.out.println("4. View Orders by User ID");
        System.out.println("5. View Orders by Manga ID");
        System.out.println("6. Exit");

        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View all borrowings
                    List<Order> borrowings = borrowingController.getAllOrders();
                    displayOrderList(borrowings);
                    break;
                case 2:
                    // Add new borrowing
                    Order newOrder = readOrderData();
                    borrowingController.addOrder(newOrder);
                    System.out.println("New borrowing added successfully!");
                    break;
                case 3:
                    // Return a borrowed book
                    System.out.print("Enter the ID of the borrowing to return: ");
                    int borrowingID = scanner.nextInt();
                    borrowingController.deleteOrder(borrowingID);
                    System.out.println("Borrowed book returned successfully!");
                    break;
                case 4:
                    // View borrowings by user ID
                    System.out.print("Enter the ID of the user to view borrowings: ");
                    int userID = scanner.nextInt();
                    List<Manga> userOrders = borrowingController.getOrdersByUserID(userID);
                    displayMangasList(userOrders);
                    break;
                case 5:
                    // View borrowings by book ID
                    System.out.print("Enter the ID of the book to view borrowings: ");
                    // int bookID = scanner.nextInt();
                    // List<Order> bookOrders = borrowingController.getOrdersByMangaID(bookID);
                    // displayOrderList(bookOrders);
                    break;
                case 6:
                    // Exit
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        }
    }

    private Order readOrderData() throws ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);

        UserController userController = new UserController();
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        User user = userController.getUser(userID);

        MangaController bookController = new MangaController();
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        Manga book = bookController.getMangaById(bookID);


        System.out.print("Enter borrowing date (yyyy-mm-dd): ");
        String borrowingDateString = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat(borrowingDateString);
        Date borrowingDate = dateFormat.parse(borrowingDateString);

        scanner.close();
        return new Order(user, book, borrowingDate);
    }

    private void displayOrderList(List<Order> borrowings) {
        System.out.println("==== Orders ====");
        System.out.printf("%-5s %-20s %-20s %-20s %-20s\n", "ID", "User", "Manga", "Order Date");
        for (Order borrowing : borrowings) {
            System.out.printf("%-5d %-20s %-20s %-20s %-20s\n",
                    borrowing.getID(), borrowing.getUser().getName(), borrowing.getManga().getTitle(),
                    borrowing.getOrderDate());
        }
    }
    
    private void displayMangasList(List<Manga> books) {
        System.out.println("==== Orders ====");
        System.out.printf("%-5s %-20s\n", "ID", "Title");
        for (Manga book : books) {
            System.out.printf("%-5d %-20s\n", book.getISBN(), book.getTitle());
        }
    }
}
