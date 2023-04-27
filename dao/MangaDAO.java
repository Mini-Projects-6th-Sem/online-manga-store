package dao;

import model.Manga;

import java.sql.SQLException;
import java.util.List;

public interface MangaDAO {
    List<Manga> getAllMangas() throws SQLException;
    void addManga(Manga manga) throws SQLException;
    void deleteManga(int id) throws SQLException;

    void updateManga(Manga manga) throws SQLException;
    List<Manga> searchMangas(String keyword) throws SQLException;

    Manga getMangaById(int id) throws SQLException;
}
