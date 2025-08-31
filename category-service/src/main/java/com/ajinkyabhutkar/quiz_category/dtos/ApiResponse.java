package com.ajinkyabhutkar.quiz_category.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean Success;
    private HttpStatus httpStatus;

}
