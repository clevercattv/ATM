package com.clevercattv.atm.cards;

public interface AtmCard {

    boolean matchingCardNumber(int cardNumber);

    boolean matchingPinCode(int pinCode);

}
