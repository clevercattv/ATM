package com.clevercattv.atm.exception;

public class NoSuchCardException extends RuntimeException {

    public NoSuchCardException() {
        super("No such card. Try again.");
    }

}
