package com.valdir.jornadaback.services.exceptions;

public class FileNotSupportedException extends RuntimeException{
    public FileNotSupportedException(String message) {
        super(message);
    }
}
