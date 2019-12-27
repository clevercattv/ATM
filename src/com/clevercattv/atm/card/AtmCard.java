package com.clevercattv.atm.card;

public interface AtmCard {

    boolean matchingCardNumber(int cardNumber);

    boolean matchingPinCode(int pinCode);

    double getBalance();

    void setBalance(double balance);

    boolean isAdmin();

    int getPinCode();

    void setPinCode(int pinCode);

    int getDepositBalance();

    void setDepositBalance(int depositBalance);

}
