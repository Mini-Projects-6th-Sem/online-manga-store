package com.ooadproject.onlinemangastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ooadproject.onlinemangastore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
