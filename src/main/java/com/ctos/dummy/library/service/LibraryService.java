package com.ctos.dummy.library.service;

import com.ctos.dummy.library.dto.BookDTO;
import com.ctos.dummy.library.dto.LibraryDTO;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.payload.request.LibraryRequest;
import com.ctos.dummy.library.payload.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {

    ResponseEntity<?> saveLibrary(LibraryRequest libraryRequest) throws Exception;

    ResponseEntity<?> updateLibrary(LibraryDTO libraryDTO) throws Exception;

    List<Book> getAllBookByAisleId(int aisleId);

    List<BookDTO> findByAisleNameAndLibraryName(String aisleName, String libraryName);

    BookResponse findBooksWithDetails(String aisleName, String libraryName);

    ResponseEntity<?> getAllLibrary();

}
