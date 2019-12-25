package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.consoles.AtmConsole;

public class AtmScreenMainOperations extends AtmScreenImpl {

    public AtmScreenMainOperations(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("***Main operations***");
        console.print("(0) - Withdraw   ");
        console.println("(1) - Check balance");
        console.print("(2) - Some think   ");
        console.println("(3) - Some think");
        console.println("(9) - exit");
        while (true) {
            switch (console.readInt()) {
                case 0:
                    return Screen.WITHDRAW_OPERATION;
                case 1:
                    return Screen.CARD_WAIT;
                case 2:
                    return Screen.CARD_WAIT;
                case 3:
                    return Screen.CARD_WAIT;
                case 9:
                    atm.setCurrentCard(null);
                    return Screen.CARD_WAIT;
                default:
                    console.println("Can't find operation! Try again!");
                    break;
            }
        }
    }

}
