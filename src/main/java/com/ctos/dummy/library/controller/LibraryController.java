package com.ctos.dummy.library.controller;

import com.ctos.dummy.library.dto.BookDTO;
import com.ctos.dummy.library.dto.LibraryDTO;
import com.ctos.dummy.library.entity.Aisle;
import com.ctos.dummy.library.payload.request.LibraryRequest;
import com.ctos.dummy.library.payload.response.BookResponse;
import com.ctos.dummy.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/{liabraryName/}aisle/{aisleName}")
    public ResponseEntity<List<BookDTO>> getBooks(@RequestParam String libraryName, @RequestParam String aisleName) {
        List<BookDTO> bookDTO = libraryService.findByAisleNameAndLibraryName(aisleName, libraryName);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/details")
    public ResponseEntity<BookResponse> getBookDetails(@RequestParam String aisleName, @RequestParam String libraryName) {
        BookResponse bookResponse = libraryService.findBooksWithDetails(aisleName, libraryName);
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveNewLibrary(@RequestBody LibraryRequest libraryRequest) throws Exception {
        return libraryService.saveLibrary(libraryRequest);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateLibrary(@RequestBody LibraryDTO libraryDTO) throws Exception {
        return libraryService.updateLibrary(libraryDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllLibrary() {
        return libraryService.getAllLibrary();
    }

}
