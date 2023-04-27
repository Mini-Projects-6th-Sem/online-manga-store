package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Manga;
import model.Order;
import model.User;

public class OrderDAO {
    // SQL queries
    private static final String INSERT_BORROWING_SQL = "INSERT INTO borrowing (user_id, book_id, borrowing_date, return_date) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BORROWING_BY_ID = "SELECT * FROM borrowing WHERE id = ?";
    private static final String SELECT_ALL_BORROWINGS = "SELECT * FROM borrowing";
    private static final String DELETE_BORROWING_SQL = "DELETE FROM borrowing WHERE id = ?";
    private static final String UPDATE_BORROWING_SQL = "UPDATE borrowing SET user_id = ?, book_id = ?, borrowing_date = ?, return_date = ? WHERE id = ?";

    // Connect to MySQL database
    private static final String url = "jdbc:mysql://localhost:3306/librarydb";
    private static final String username = "root";
    private static final String password = "pranav";
    private Connection connection;

    public OrderDAO() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database");
            e.printStackTrace();
        }
    }

    // Insert borrowing record into database
    public void insertOrder(Order borrowing) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROWING_SQL);
            preparedStatement.setInt(1, borrowing.getUser().getID());
            preparedStatement.setInt(2, borrowing.getManga().getISBN());
            preparedStatement.setDate(3, new Date(borrowing.getOrderDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting borrowing record");
            e.printStackTrace();
        }
    }

    // Select borrowing record by ID
    public Order selectOrder(int id) {
        Order borrowing = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWING_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = selectUser(resultSet.getInt("user_id"));
                Manga book = selectManga(resultSet.getInt("book_id"));
                Date borrowingDate = resultSet.getDate("borrowing_date");
                borrowing = new Order(id, user, book, borrowingDate);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting borrowing record");
            e.printStackTrace();
        }
        return borrowing;
    }

    // Select all borrowing records
    public List<Order> selectAllOrders() {
        List<Order> borrowings = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BORROWINGS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User user = selectUser(resultSet.getInt("user_id"));
                Manga book = selectManga(resultSet.getInt("book_id"));
                Date borrowingDate = resultSet.getDate("borrowing_date");
                borrowings.add(new Order(id, user, book, borrowingDate));
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all borrowing records");
            e.printStackTrace();
        }
        return borrowings;
    }

    // Delete borrowing record by ID
    public boolean deleteOrder(int id) {
        boolean rowDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BORROWING_SQL);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting borrowing record");
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update borrowing record
    public boolean updateOrder(Order borrowing) {
        boolean rowUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BORROWING_SQL);
            preparedStatement.setInt(1, borrowing.getUser().getID());
            preparedStatement.setInt(2, borrowing.getManga().getISBN());
            preparedStatement.setDate(3, new Date(borrowing.getOrderDate().getTime()));
            preparedStatement.setInt(5, borrowing.getID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating borrowing record");
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Select user record by ID
    private User selectUser(int id) {
        UserDAO userDAO = new UserDAO();
        return userDAO.selectUser(id);
    }

    // Select book record by ID
    private Manga selectManga(int id) throws SQLException {
        MangaDAO bookDAO = new MangaDAOImpl();
        return bookDAO.getMangaById(id);
    }
}