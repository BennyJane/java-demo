package com.benny.jane.exception;

public class MessageIdException extends Exception{

    public MessageIdException(String message) {
        super(message);
    }

    public MessageIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
