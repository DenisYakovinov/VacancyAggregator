package edu.job4j.exception;

public class ReadFileException extends RuntimeException {

    public ReadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadFileException(String message) {
        super(message);
    }
}