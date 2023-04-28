package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {

    // SQL queries
    private static final String INSERT_USER_SQL = "INSERT INTO customer (c_name, c_address, c_phone, c_mail) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM customer WHERE c_id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM customer";
    private static final String DELETE_USER_SQL = "DELETE FROM customer WHERE c_id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE customer SET c_name = ?, c_address = ?, c_phone = ?, c_mail = ? WHERE c_id = ?";
    private static final String FETCH_SUBS = "Select * from customer where msgSub = 1";

    // Connect to MySQL database
    private static final String url = "jdbc:mysql://localhost:3306/manga_store";
    private static final String username = "root";
    private static final String password = "@root123";
    private Connection connection;

    public UserDAO() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database");
            e.printStackTrace();
        }
    }

    // Insert user into database
    public void insertUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            System.out.println("DB inserting user");
        } catch (SQLException e) {
            System.out.println("Error inserting user");
            e.printStackTrace();
        }
    }

    // Select user by ID
    public User selectUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                user = new User(id, name, address, phoneNumber, email);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting user");
            e.printStackTrace();
        }
        return user;
    }

    // Select all customer from database
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("c_id");
                String name = resultSet.getString("c_name");
                String address = resultSet.getString("c_address");
                String phoneNumber = resultSet.getString("c_phone");
                String email = resultSet.getString("c_mail");
                users.add(new User(id, name, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all users");
            e.printStackTrace();
        }
        return users;
    }


    // Select all Subscribers from database
    public List<User> FetchSubs() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FETCH_SUBS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                users.add(new User(id, name, address, phoneNumber, email));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users");
            e.printStackTrace();
        }
        return users;
    }


    // Delete user from database
    public boolean deleteUser(User user) {
        boolean rowDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, user.getID());
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user");
            e.printStackTrace();
        }
        return rowDeleted;
    }

    // Update user in database
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user");
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection");
            e.printStackTrace();
        }
    }
}
