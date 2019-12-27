package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.atm.AtmImpl;
import com.clevercattv.atm.console.AtmConsole;

public class AtmScreenChangePin extends AtmScreenImpl {

    public AtmScreenChangePin(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.printScreenName("Change pin");
        for (int i = 1; i <= Atm.COUNT_ATTEMPTS; i++) {
            if (console.readInt("Enter old pin code : ") == atm.getCurrentCard().getPinCode()) {
                int newPinCode = console.readInt("Enter new pin code : ");
                if (newPinCode == console.readInt("Confirm new pin code : ")) {
                    atm.getCurrentCard().setPinCode(newPinCode);
                    return Screen.MAIN_OPERATIONS;
                } else {
                    console.println(String.format("Wrong confirm pin! " +
                            "Try again. (Attempts %s left)", Atm.COUNT_ATTEMPTS - i));
                }
            } else {
                console.println(String.format("Wrong pin! Try again. (Attempts %s left)", Atm.COUNT_ATTEMPTS - i));
            }
        }
        return Screen.EAT_CARD;
    }

}
