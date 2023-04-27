package controller;

import dao.MangaDAO;
import dao.MangaDAOImpl;
import factory.MangaFactory;
import model.Manga;
import view.MangaView;

import java.sql.SQLException;
import java.util.*;


public class MangaController {
    private MangaDAO mangaDAO;
    private Scanner scanner;
    private MangaView mangaview;

    public MangaController() throws SQLException {
        this.mangaDAO = new MangaDAOImpl();
        this.scanner = new Scanner(System.in);
        this.mangaview = new MangaView(this);
    }

    public boolean addManga(Integer ISBN, String title, String mangaka, String genre, Integer stock, Integer price) throws SQLException {
        Manga manga = MangaFactory.createManga(ISBN, title, mangaka, genre, stock, price);
        if(manga!=null) {
            this.mangaDAO.addManga(manga);
            return true;
        } else {
            return false;
        }
    }

    public List<Manga> getAllMangas() throws SQLException {
        return mangaDAO.getAllMangas();
    }

    public void deleteManga(int ISBN) throws SQLException {
        mangaDAO.deleteManga(ISBN);
    }

    public void searchMangas(String keyword) throws SQLException {
        try {
            List<Manga> mangas = mangaDAO.searchMangas(keyword);
            if (mangas.isEmpty()) {
                mangaview.displayMessage("No mangas found!");
            } else {
                mangaview.displayMessage("Search Results:");
                mangaview.displayMessage("ISBN\tTitle\tMangaka\tGenre\tStock");
                for (Manga manga : mangas) {
                    mangaview.displayMessage(manga.getISBN() + "\t" + manga.getTitle() + "\t" + manga.getMangaka() + "\t" +
                            manga.getGenre() + "\t" + manga.getStock());
                }
            }
        } catch (SQLException e) {
            mangaview.displayMessage("An error occurred while searching mangas: " + e.getMessage());
        }
        scanner.close();
    }

    public void updateManga(int ISBN) {
        try {
            Manga manga = mangaDAO.getMangaById(ISBN);
            if (manga == null) {
                mangaview.displayMessage("Manga not found!");
                return;
            }
            mangaview.displayMessage("Current manga details:");
            mangaview.displayMangaDetails(manga);
            String[] newValues = mangaview.getMangaUpdates();
            String title = newValues[0].isEmpty() ? manga.getTitle() : newValues[0];
            String mangaka = newValues[1].isEmpty() ? manga.getMangaka() : newValues[1];
            String genre = newValues[2].isEmpty() ? manga.getGenre() : newValues[2];
            Integer stock = newValues[3].isEmpty() ? manga.getStock() : Integer.parseInt(newValues[3]);
            Integer price = newValues[4].isEmpty() ? manga.getPrice() : Integer.parseInt(newValues[4]);
            Manga newManga = MangaFactory.createManga(ISBN, title, mangaka, genre, stock, price);
            mangaDAO.updateManga(newManga);
            mangaview.displayMessage("Manga updated successfully!");
        } catch (SQLException e) {
            mangaview.displayMessage("An error occurred while updating manga: " + e.getMessage());
        }
    }

    public Manga getMangaById(int mangaID) throws SQLException {
        return mangaDAO.getMangaById(mangaID);
    }

}