package com.clevercattv.atm.screens;

import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenCardSlotFull implements AtmScreen {

    private final AtmConsole console;

    public AtmScreenCardSlotFull(AtmConsole console) {
        this.console = console;
    }

    @Override
    public void init() {
        console.println("Cart slot full. Master called!");
    }
}
