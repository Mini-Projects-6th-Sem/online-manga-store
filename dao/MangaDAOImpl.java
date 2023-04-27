package dao;

import factory.MangaFactory;
import model.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAOImpl implements MangaDAO {

    private  String url = "jdbc:mysql://localhost/librarydb";
    private  String user = "root";
    private  String password = "pranav";

    public MangaDAOImpl() throws SQLException {
        super();
    }

    Connection connection = DriverManager.getConnection(url, user, password);

    public Manga parseManga(ResultSet resultSet) throws SQLException {
        int ISBN = resultSet.getInt("ISBN");
        String title = resultSet.getString("title");
        String mangaka = resultSet.getString("mangaka");
        String genre = resultSet.getString("genre");
        Integer getStock = resultSet.getInt("stock");
        Integer price = resultSet.getInt("price");
        return MangaFactory.createManga(ISBN, title, mangaka, genre, getStock, price);
    }

    @Override
    public List<Manga> getAllMangas() throws SQLException {
        List<Manga> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            books.add(parseManga(resultSet));
        }
        return books;
    }
    @Override
    public void addManga(Manga book) throws SQLException {
        String query = "INSERT INTO books (title, mangaka, genre, stock, price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getMangaka());
        statement.setString(3, book.getGenre());
        statement.setInt(4, book.getStock());
        statement.setInt(5, book.getPrice());
        statement.executeUpdate();
    }


    @Override
    public void deleteManga(int ISBN) throws SQLException {
        String query = "DELETE FROM books WHERE ISBN = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, ISBN);
        statement.executeUpdate();
    }

    @Override
    public Manga getMangaById(int ISBN) throws SQLException {
        String query = "SELECT * FROM books WHERE ISBN = ?";
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

    public void updateManga(Manga book) throws SQLException {
        String query = "UPDATE books SET title = ?, mangaka = ?, genre = ?, stock = ?, price = ? WHERE ISBN = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getMangaka());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getStock());
            statement.setInt(5, book.getPrice());
            statement.setInt(6, book.getISBN());
            statement.executeUpdate();
        }
    }



    @Override
    public List<Manga> searchMangas(String keyword) throws SQLException {
        List<Manga> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ? OR mangaka LIKE ? OR genre LIKE ?";
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
            Manga book = MangaFactory.createManga(ISBN, title, mangaka, genre, getStock, price);
            books.add(book);
        }
        return books;
    }
}
