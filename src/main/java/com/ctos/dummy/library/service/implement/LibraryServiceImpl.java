package com.ctos.dummy.library.service.implement;

import com.ctos.dummy.library.dto.BookDTO;
import com.ctos.dummy.library.dto.LibraryDTO;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.entity.Library;
import com.ctos.dummy.library.payload.request.LibraryRequest;
import com.ctos.dummy.library.payload.response.BasicResponse;
import com.ctos.dummy.library.payload.response.BookResponse;
import com.ctos.dummy.library.repository.AisleRepository;
import com.ctos.dummy.library.repository.BookRepository;
import com.ctos.dummy.library.repository.LibraryRepository;
import com.ctos.dummy.library.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
    @Autowired
    private AisleRepository aisleRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> saveLibrary(LibraryRequest libraryRequest) throws Exception {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            Library library = new Library();
            library.setLibraryName(libraryRequest.getLibraryName());
            libraryRepository.save(library);

            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResponseCode(HttpStatus.CREATED.value());
            basicResponse.setResponseMessage("Save library success");
            basicResponse.setData(library);

            return new ResponseEntity<>(basicResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error saving library: " + e.getLocalizedMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<?> updateLibrary(LibraryDTO libraryDTO) throws Exception {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "---libraryId={}", libraryDTO.getLibraryId());
        try {
            Library lib = libraryRepository.findById(libraryDTO.getLibraryId()).orElseThrow(() -> new NoSuchElementException("Library not found "));
            lib.setLibraryName(lib.getLibraryName());
            libraryRepository.save(lib);

            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResponseCode(HttpStatus.OK.value());
            basicResponse.setResponseMessage("Update library success");
            basicResponse.setData(lib);
            return new ResponseEntity<>(basicResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error updating library: " + e.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> getAllBookByAisleId(int aisleId) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "---aisleId={}", aisleId);
        try {
            return bookRepository.findAllByAisleId(aisleId)
                    .stream()
                    .filter(b -> !b.getBookName().isEmpty())
                    .sorted(Comparator.comparing(Book::getBookName))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error getting Book list: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<BookDTO> findByAisleNameAndLibraryName(String aisleName, String libraryName) {
        logger.info(Thread.currentThread().getStackTrace()[0].getMethodName() + "---getBooks");
        Aisle aisleEntity = aisleRepository.findBookByAisleNameAndLibraryName(aisleName, libraryName)
                .orElseThrow(() -> new NoSuchElementException(aisleName + " " + libraryName + " not found"));

        return aisleEntity.getAisBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        .BookId(book.getBookId())
                        .BookName(book.getBookName()).build())
                .sorted(Comparator.comparing(BookDTO::getBookName))
                .collect(Collectors.toList());
    }

    public BookResponse findBooksWithDetails(String aisleName, String libraryName) {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "---getBooksWithDetails");
        Aisle aisle = aisleRepository.findBookByAisleNameAndLibraryName(aisleName, libraryName)
                .orElseThrow(()-> new NoSuchElementException(aisleName + " " + libraryName + " not found"));

        List<BookDTO> listBook = aisle.getAisBooks().stream()
                .map(this::convertToDTO)
                .sorted(Comparator.comparing(BookDTO::getBookName))
                .toList();

        return BookResponse.builder()
                .responseCode(201)
                .responseMessage("Success")
                .aisleName(aisleName)
                .libraryName(libraryName)
                .books(listBook).build();
    }

    private BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .BookId(book.getBookId())
                .BookName(book.getBookName()).build();
    }

    @Override
    public ResponseEntity<?> getAllLibrary() {
        List<Library> libraryList = libraryRepository.findAll().stream().toList();
        List<LibraryDTO> libraryDTOList = new ArrayList<>();
        for (Library library: libraryList) {
            LibraryDTO libraryDTO = new LibraryDTO();
            libraryDTO.setLibraryId(library.getLibraryId());
            libraryDTO.setLibraryName(library.getLibraryName());
            libraryDTOList.add(libraryDTO);
        }

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setResponseCode(HttpStatus.OK.value());
        basicResponse.setResponseMessage("Get all library");
        basicResponse.setData(libraryDTOList);
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

}
