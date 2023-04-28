package dao;

import factory.MangaFactory;
import model.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAOImpl implements MangaDAO {

    private  String url = "jdbc:mysql://localhost/manga_store";
    private  String user = "root";
    private  String password = "@root123";

    public MangaDAOImpl() throws SQLException {
        super();
    }

    Connection connection = DriverManager.getConnection(url, user, password);

    public Manga parseManga(ResultSet resultSet) throws SQLException {
        Integer ISBN = resultSet.getInt("ISBN");
        String title = resultSet.getString("title");
        String mangaka = resultSet.getString("mangaka");
        String genre = resultSet.getString("genre");
        Integer getStock = resultSet.getInt("stock");
        Integer price = resultSet.getInt("price");
        return MangaFactory.createManga(ISBN, title, mangaka, genre, getStock, price);
    }

    @Override
    public List<Manga> getAllMangas() throws SQLException {
        List<Manga> mangas = new ArrayList<>();
        String query = "SELECT * FROM manga";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            mangas.add(parseManga(resultSet));
        }
        return mangas;
    }
    @Override
    public void addManga(Manga manga) throws SQLException {
        String query = "INSERT INTO manga (ISBN, title, mangaka, genre, stock, price) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, manga.getISBN());
        statement.setString(2, manga.getTitle());
        statement.setString(3, manga.getMangaka());
        statement.setString(4, manga.getGenre());
        statement.setInt(5, manga.getStock());
        statement.setInt(6, manga.getPrice());
        statement.executeUpdate();
    }


    @Override
    public void deleteManga(int ISBN) throws SQLException {
        String query = "DELETE FROM manga WHERE ISBN = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ISBN);
        statement.executeUpdate();
    }

    @Override
    public Manga getMangaById(int ISBN) throws SQLException {
        String query = "SELECT * FROM manga WHERE ISBN = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ISBN);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseManga(resultSet);
            } else {
                return null;
            }
        }
    }

    public void updateManga(Manga manga) throws SQLException {
        String query = "UPDATE manga SET title = ?, mangaka = ?, genre = ?, stock = ?, price = ? WHERE ISBN = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manga.getTitle());
            statement.setString(2, manga.getMangaka());
            statement.setString(3, manga.getGenre());
            statement.setInt(4, manga.getStock());
            statement.setInt(5, manga.getPrice());
            statement.setInt(6, manga.getISBN());
            statement.executeUpdate();
        }
    }



    @Override
    public List<Manga> searchMangas(String keyword) throws SQLException {
        List<Manga> mangas = new ArrayList<>();
        String query = "SELECT * FROM manga WHERE title LIKE ? OR mangaka LIKE ? OR genre LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + keyword + "%");
        statement.setString(2, "%" + keyword + "%");
        statement.setString(3, "%" + keyword + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int ISBN = resultSet.getInt("ISBN");
            String title = resultSet.getString("title");
            String mangaka = resultSet.getString("mangaka");
            String genre = resultSet.getString("genre");
            Integer getStock = resultSet.getInt("stock");
            Integer price = resultSet.getInt("price");
            Manga manga = MangaFactory.createManga(ISBN, title, mangaka, genre, getStock, price);
            mangas.add(manga);
        }
        return mangas;
    }
}
