package factory;

import model.*;

public class MangaFactory {
    public static Manga createManga(Integer id, String title, String mangaka, String genre, Integer stock, Integer price) {
        switch (genre) {
            case "Seinin":
                return new Seinin(id, title, mangaka, genre, stock, price);
            case "Shonen":
                return new Shonen(id, title, mangaka, genre, stock, price);
            case "Shoujo":
                return new Shoujo(id, title, mangaka, genre, stock, price);
            default:
                return null;
        }
    }

    public static class MangaBuilder {
        private Integer ISBN;
        private String title;
        private String mangaka;
        private String genre;
        private Integer stock;
        private Integer price;

        public MangaBuilder setId(Integer ISBN) {
            this.ISBN = ISBN;
            return this;
        }

        public MangaBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MangaBuilder setAuthor(String mangaka) {
            this.mangaka = mangaka;
            return this;
        }

        public MangaBuilder setYear(String genre) {
            this.genre = genre;
            return this;
        }

        public MangaBuilder setAvailable(Integer stock) {
            this.stock = stock;
            return this;
        }

        public MangaBuilder setType(Integer price) {
            this.price = price;
            return this;
        }

        public Manga build() {
            return MangaFactory.createManga(ISBN, title, mangaka, genre, stock, price);
        }
    }
}



