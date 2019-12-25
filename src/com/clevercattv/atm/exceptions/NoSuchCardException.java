package com.clevercattv.atm.exceptions;

public class NoSuchCardException extends RuntimeException {

    public NoSuchCardException() {
        super("No such card. Try again.");
    }

}
