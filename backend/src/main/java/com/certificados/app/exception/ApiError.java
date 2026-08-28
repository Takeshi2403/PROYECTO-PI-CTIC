package com.certificados.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String timestamp;
    private int status;
    private String error;
    private List<String> details;
}
