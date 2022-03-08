package com.rodraf83.backend.model.repository;

import com.rodraf83.backend.model.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
