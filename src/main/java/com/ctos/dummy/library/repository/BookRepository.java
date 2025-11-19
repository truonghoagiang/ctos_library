package com.ctos.dummy.library.repository;

import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.repository.custom.BookRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

    List<Book> findAllByAisleId(int aisleId);
}
