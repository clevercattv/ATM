package com.clevercattv.atm;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.atm.AtmImpl;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.console.AtmConsoleImpl;
import com.clevercattv.atm.model.enums.Bill;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        AtmConsole console = new AtmConsoleImpl();
        Atm atm = new AtmImpl(console);
        Arrays.stream(Bill.values())
                .forEach(bill -> atm.getBills().put(bill,10));
        atm.init();
    }

}
