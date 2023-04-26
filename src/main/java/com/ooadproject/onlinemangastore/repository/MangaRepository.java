package com.ooadproject.onlinemangastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ooadproject.onlinemangastore.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {}