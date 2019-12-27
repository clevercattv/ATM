package com.clevercattv.atm.card;

import java.util.Objects;

public class AtmCardImpl implements AtmCard {

    private final int cardNumber;
    private int pinCode;
    private CardType type;
    private double balance; // in real balance need use BigDecimal
    private String fullName;
    private boolean admin;
    private int depositBalance;

    public AtmCardImpl(int cardNumber, int pinCode, CardType type, double balance, String fullName, boolean admin, int depositBalance) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.type = type;
        this.balance = balance;
        this.fullName = fullName;
        this.admin = admin;
        this.depositBalance = depositBalance;
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
    public String toString() {
        return "AtmCardImpl{" +
                "cardNumber=" + cardNumber +
                ", pinCode=" + pinCode +
                ", balance=" + balance +
                '}';
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

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(int depositBalance) {
        this.depositBalance = depositBalance;
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
