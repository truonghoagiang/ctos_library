package com.ctos.dummy.library.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicResponse {

    private int responseCode;
    private String responseMessage;
    private Object data;
}
