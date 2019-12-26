package com.clevercattv.atm.cards;

import java.math.BigDecimal;
import java.util.Objects;

public class AtmCardImpl implements AtmCard {

    private final int cardNumber;
    int pinCode;
    CardType type;
    double balance;
    String fullName;

    public AtmCardImpl(int cardNumber, int pinCode, CardType type, double balance, String fullName) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.type = type;
        this.balance = balance;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmCardImpl atmCard = (AtmCardImpl) o;
        return cardNumber == atmCard.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public boolean matchingCardNumber(int cardNumber) {
        return this.cardNumber == cardNumber;
    }

    @Override
    public boolean matchingPinCode(int pinCode) {
        return this.pinCode == pinCode;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
