package com.clevercattv.atm;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.atms.AtmImpl;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.consoles.AtmConsoleImpl;

public class Application {

    public static void main(String[] args) {
        AtmConsole console = new AtmConsoleImpl();
        Atm atm = new AtmImpl(console);
        atm.init();
    }

}
