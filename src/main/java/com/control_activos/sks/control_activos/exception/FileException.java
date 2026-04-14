package com.control_activos.sks.control_activos.exception;

import org.springframework.http.HttpStatus;

public class FileException extends ApiException{
    public FileException(String message) {
        super(message, HttpStatus.BAD_GATEWAY);
    }
}
