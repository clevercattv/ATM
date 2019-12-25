package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.consoles.AtmConsole;

public abstract class AtmScreenImpl implements AtmScreen {

    protected final Atm atm;
    protected final AtmConsole console;

    public AtmScreenImpl(AtmConsole console, Atm atm) {
        this.console = console;
        this.atm = atm;
    }

}
