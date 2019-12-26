package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;

public class AtmScreenCheckBalance extends AtmScreenImpl {

    public AtmScreenCheckBalance(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("\n" + String.format("Your balance : $ %.2f", atm.getCurrentCard().getBalance()));
        return Screen.MAIN_OPERATIONS;
    }

}
