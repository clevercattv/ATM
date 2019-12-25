package com.clevercattv.atm.cards;

import java.math.BigDecimal;

public class AtmCardImpl implements AtmCard {

    int cardNumber;
    int pinCode;
    CardType type;
    BigDecimal balance;
    String fullName;

    public AtmCardImpl(int cardNumber, int pinCode, CardType type, BigDecimal balance, String fullName) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.type = type;
        this.balance = balance;
        this.fullName = fullName;
    }

    @Override
    public boolean matchingCardNumber(int cardNumber) {
        return this.cardNumber == cardNumber;
    }

    @Override
    public boolean matchingPinCode(int pinCode) {
        return this.pinCode == pinCode;
    }

}
