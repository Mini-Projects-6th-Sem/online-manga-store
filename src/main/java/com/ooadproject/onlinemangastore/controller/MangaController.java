package com.ooadproject.onlinemangastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.onlinemangastore.exception.ResourceNotFoundException;
import com.ooadproject.onlinemangastore.model.Manga;
import com.ooadproject.onlinemangastore.repository.MangaRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    /**
     * Get list of all mangas.
     *
     * @return the list
     */
    @GetMapping("/manga")
    public List<Manga> getAllMangas() {
        return mangaRepository.findAll();
    }

    /**
     * Gets manga by id.
     *
     * @param mangaId the manga id
     * @return the mangas by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/manga/{id}")
    public ResponseEntity<Manga> getMangasById(@PathVariable(value = "id") Long mangaId)
            throws ResourceNotFoundException {
        Manga manga = mangaRepository
                .findById(mangaId)
                .orElseThrow(() -> new ResourceNotFoundException("Manga not found on :: " + mangaId));
        return ResponseEntity.ok().body(manga);
    }

    /**
     * Create manga manga.
     *
     * @param manga the manga
     * @return the manga
     */
    @PostMapping("/manga")
    public Manga createManga(@Valid @RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    /**
     * Update manga response entity.
     *
     * @param mangaId      the manga id
     * @param mangaDetails the manga details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/manga/{id}")
    public ResponseEntity<Manga> updateManga(
            @PathVariable(value = "id") Long mangaId, @Valid @RequestBody Manga mangaDetails)
            throws ResourceNotFoundException {

        Manga manga = mangaRepository
                .findById(mangaId)
                .orElseThrow(() -> new ResourceNotFoundException("Manga not found on :: " + mangaId));

        manga.setMangaName(mangaDetails.getMangaName());
        manga.setMangaPrice(mangaDetails.getMangaPrice());
        manga.setAvailableQuantity(mangaDetails.getAvailableQuantity());
        manga.setUpdatedAt(new Date());
        final Manga updatedManga = mangaRepository.save(manga);
        return ResponseEntity.ok(updatedManga);
    }

    /**
     * Delete manga map.
     *
     * @param mangaId the manga id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/manga/{id}")
    public Map<String, Boolean> deleteManga(@PathVariable(value = "id") Long mangaId) throws Exception {
        Manga manga = mangaRepository
                .findById(mangaId)
                .orElseThrow(() -> new ResourceNotFoundException("Manga not found on :: " + mangaId));

        mangaRepository.delete(manga);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
