package com.test.microwarehouse.dto;

import lombok.*;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TopCuisineDto implements Serializable {
    private String cuisine_type;
    private long count;
}
