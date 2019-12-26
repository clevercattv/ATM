package com.clevercattv.atm.cards;

import java.math.BigDecimal;

public interface AtmCard {

    boolean matchingCardNumber(int cardNumber);

    boolean matchingPinCode(int pinCode);

    double getBalance();

}
