package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.server.Server;

public class AtmScreenBillsStorageEmpty extends AtmScreenImpl {

    public AtmScreenBillsStorageEmpty(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.printScreenName("Bills storage empty");
        console.println("Atm shutdown");
        Server.callMaster();
        atm.setActive(false);
        return null;
    }

}
