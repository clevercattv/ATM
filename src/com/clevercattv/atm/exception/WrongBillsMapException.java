package com.clevercattv.atm.exception;

public class WrongBillsMapException extends RuntimeException {

    public WrongBillsMapException() {
        super("Bills map size != Bills size");
    }

}
