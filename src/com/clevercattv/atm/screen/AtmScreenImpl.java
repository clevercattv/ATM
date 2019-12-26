package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;

public abstract class AtmScreenImpl implements AtmScreen {

    protected final Atm atm;
    protected final AtmConsole console;

    public AtmScreenImpl(AtmConsole console, Atm atm) {
        this.console = console;
        this.atm = atm;
    }

}
