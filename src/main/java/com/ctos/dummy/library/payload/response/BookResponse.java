package com.ctos.dummy.library.payload.response;

import com.ctos.dummy.library.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private int responseCode;
    private String responseMessage;
    private String aisleName;
    private String libraryName;
    private List<BookDTO> books;
}
