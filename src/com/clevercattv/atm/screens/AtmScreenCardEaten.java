package com.clevercattv.atm.screens;

import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenCardEaten implements AtmScreen {

    private final AtmConsole console;

    public AtmScreenCardEaten(AtmConsole console) {
        this.console = console;
    }

    @Override
    public Screen init() {
        console.println("Cart slot full. Master called!");
        return Screen.CARD_WAIT;
    }

}
