package com.smartcode.ecommerce.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private Integer status;
    private String path;
    private Map<String, String> errors;

}
