package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenCardEaten extends AtmScreenImpl {

    public AtmScreenCardEaten(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("No attempts left. Card was swallowed. Good luck.");
        atm.eatCard();
        return Screen.CARD_WAIT;
    }

}
