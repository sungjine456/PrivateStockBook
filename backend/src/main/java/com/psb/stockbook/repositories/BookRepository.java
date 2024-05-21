package com.psb.stockbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psb.stockbook.domains.books.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
