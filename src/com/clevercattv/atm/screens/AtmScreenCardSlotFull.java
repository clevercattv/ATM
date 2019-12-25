package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenCardSlotFull extends AtmScreenImpl {


    public AtmScreenCardSlotFull(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("Cart slot full. Master called!");
        return Screen.CARD_WAIT;
    }
}
