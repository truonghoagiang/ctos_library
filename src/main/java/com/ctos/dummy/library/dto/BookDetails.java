package com.ctos.dummy.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDetails {

    private String aisleName;
    private String libraryName;
    private List<BookDTO> bookDTOList;
}
