package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;

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
