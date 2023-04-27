package model;

public class Shoujo implements Manga {
    private Integer ISBN;
    private String title;
    private String mangaka;
    private String genre;
    private Integer stock;
    private Integer price;

    public Shoujo(Integer ISBN, String title, String mangaka, String genre, Integer stock, Integer price) {
        this.ISBN = ISBN;
        this.title = title;
        this.mangaka = mangaka;
        this.genre = genre;
        this.stock = stock;
        this.price = price;
    }

    @Override
    public Integer getISBN() {
        return ISBN;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMangaka() {
        return mangaka;
    }

    @Override
    public String  getGenre() {
        return genre;
    }

    @Override
    public Integer getStock() {
        return stock;
    }

    @Override
    public Integer getPrice() {
        return price;
    }
}
