package model;
import java.util.Date;

public class Order {
    private int ID;
    private User user;
    private Manga manga;
    private Date orderDate;
    
    public Order(int ID, User user, Manga manga, Date orderDate) {
        this.ID = ID;
        this.user = user;
        this.manga = manga;
        this.orderDate = orderDate;
    }

    public Order(User user, Manga manga, Date orderDate) {
        this.user = user;
        this.manga = manga;
        this.orderDate = orderDate;
    }

    // Getter and setter methods
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Manga getManga() {
        return manga;
    }
    
    public void setManga(Manga manga) {
        this.manga = manga;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}