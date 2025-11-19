package com.ctos.dummy.library.repository.custom;

import com.ctos.dummy.library.dto.BookDetails;
import com.ctos.dummy.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryCustom {

    /**
     * Another way to find all books by aisle_name and library_name
     * @param aisleName
     * @param libraryName
     * @return
     */
    List<BookDetails> findByAisleNameAndLibraryName(String aisleName, String libraryName);
}
