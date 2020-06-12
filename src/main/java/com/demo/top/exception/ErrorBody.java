package com.demo.top.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBody {
    private String message;
}
