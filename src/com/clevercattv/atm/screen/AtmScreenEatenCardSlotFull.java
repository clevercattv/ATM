package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.server.Server;

public class AtmScreenEatenCardSlotFull extends AtmScreenImpl {

    public AtmScreenEatenCardSlotFull(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.printScreenName("Cart slot full");
        console.println("Master called! ATM shutdown");
        Server.callMaster();
        atm.setActive(false);
        return Screen.CARD_WAIT;
    }

}
