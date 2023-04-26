package com.ooadproject.onlinemangastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ooadproject.onlinemangastore.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
