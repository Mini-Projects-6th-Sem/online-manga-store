package controller;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import model.Manga;
import model.Order;

public class OrderController {
    private OrderDAO orderDAO = new OrderDAO();

    public void addOrder(Order order) {
        orderDAO.insertOrder(order);
    }

    public Order getOrder(int id) {
        return orderDAO.selectOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderDAO.selectAllOrders();
    }

    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    public List<Manga> getOrdersByUserID(int userID) {
        List<Order> orders = (List<Order>) orderDAO.selectOrder(userID);
        List<Manga> books = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                books.add(order.getManga());
            }
        }
        return books;
    }
    
}
