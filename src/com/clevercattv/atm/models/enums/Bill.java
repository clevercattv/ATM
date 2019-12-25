package com.clevercattv.atm.models.enums;

public enum Bill {
    ONE_HUNDRED_DOLLAR(100),
    FIFTY_DOLLAR(50),
    TWENTY_DOLLAR(20),
    TEN_DOLLAR(10),
    FIVE_DOLLAR(5),
    TWO_DOLLAR(2),
    ONE_DOLLAR(1);

    int money;

    Bill(int money){
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
