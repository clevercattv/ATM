package com.clevercattv.atm.screens;

import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenMainOperations implements AtmScreen {

    private final AtmConsole console;

    public AtmScreenMainOperations(AtmConsole console) {
        this.console = console;
    }

    @Override
    public Screen init() {
        console.println("Main operations");
        return Screen.CARD_WAIT;
    }

}
