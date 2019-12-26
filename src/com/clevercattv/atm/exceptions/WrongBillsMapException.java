package com.clevercattv.atm.exceptions;

public class WrongBillsMapException extends RuntimeException {

    public WrongBillsMapException() {
        super("Bills map size != Bills size");
    }

}
