package view;

import controller.MangaController;
import model.Manga;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class MangaView {
    private MangaController mangaController;
    private Scanner scanner;

    public MangaView(MangaController mangaController) {
        this.mangaController = mangaController;
        scanner = new Scanner(System.in);
    }

    public void showMangaMenu() throws SQLException {
        while (true) {
            System.out.println("1. Add Manga");
            System.out.println("2. View All Mangas");
            System.out.println("3. Delete Manga");
            System.out.println("4. Update Manga");
            System.out.println("5. Search Manga");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addManga();
                    break;
                case 2:
                    viewAllMangas();
                    break;
                case 3:
                    deleteManga();
                    break;
                case 4:
                    updateManga();
                    break;
                case 5:
                    searchMangas();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addManga() throws SQLException {
        System.out.print("Enter manga ISBN: ");
        Integer ISBN = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter manga title: ");
        String title = scanner.nextLine();

        System.out.print("Enter manga mangaka: ");
        String mangaka = scanner.nextLine();

        System.out.print("Enter manga genre: ");
        String  genre = scanner.nextLine();

        System.out.print("Enter manga stock: ");
        Integer stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter manga price: ");
        Integer price = Integer.parseInt(scanner.nextLine());

        if(mangaController.addManga(ISBN, title, mangaka, genre, stock, price)) {
            System.out.println("Manga added successfully");
        } else{

        }
    }

    public void viewAllMangas() throws SQLException {
        List<Manga> mangas = mangaController.getAllMangas();
        System.out.println("List of all mangas:");
        System.out.println("ID\tTitle\tAuthor\tYear\tAvailable");
        for (Manga manga : mangas) {
            System.out.println(manga.getISBN() + "\t" + manga.getTitle() + "\t" + manga.getMangaka() + "\t" +
                    manga.getGenre() + "\t" + manga.getStock());
        }
    }

    private void deleteManga() throws SQLException {
        System.out.print("Enter manga ID: ");
        int id = scanner.nextInt();
        mangaController.deleteManga(id);
        System.out.println("Manga deleted successfully");
    }
    public void searchMangas() throws SQLException {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        mangaController.searchMangas(keyword);
    }
    public void updateManga() {
        System.out.print("Enter manga ID: ");
        int id = scanner.nextInt();
        mangaController.updateManga(id);
    }


    public void displayMessage(String s) {
        System.out.println(s);
    }

    public void displayMangaDetails(Manga manga) {
        System.out.println("ID: " + manga.getISBN());
        System.out.println("Title: " + manga.getTitle());
        System.out.println("Author: " + manga.getMangaka());
        System.out.println("Publication genre: " + manga.getGenre());
        System.out.println("Availability: " + manga.getStock());
        System.out.println("Type: " + manga.getPrice());
    }

    public String[] getMangaUpdates() {
        String[] updates = new String[5];
        System.out.print("Enter new title (press enter to skip): ");
        updates[0] = scanner.nextLine();
        System.out.print("Enter new mangaka (press enter to skip): ");
        updates[1] = scanner.nextLine();
        System.out.print("Enter new genre (press enter to skip): ");
        updates[2] = scanner.nextLine();
        System.out.print("Enter new availability (true/false, press enter to skip): ");
        updates[3] = scanner.nextLine();
        System.out.print("Enter new manga type (press enter to skip): ");
        updates[4] = scanner.nextLine();
        return updates;
    }


}

