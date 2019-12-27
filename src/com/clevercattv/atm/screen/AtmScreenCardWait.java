package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.exception.NoSuchCardException;
import com.clevercattv.atm.server.Server;

public class AtmScreenCardWait extends AtmScreenImpl {

    public AtmScreenCardWait(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        atm.setCurrentCard(null);
        console.printScreenName("ATM");
        int cardNumber = console.readInt("Please enter your card number : ");
        AtmCard card = Server.getCard(cardNumber)
                .orElseThrow(NoSuchCardException::new);
        atm.setCurrentCard(card);
        for (int i = 1; i <= Atm.COUNT_ATTEMPTS; i++) {
            int pinCode = console.readInt("Enter card pin code : ");
            if (card.matchingPinCode(pinCode)) {
                return Screen.MAIN_OPERATIONS;
            } else {
                console.println(String.format("Wrong pin. Attempts left : %d", Atm.COUNT_ATTEMPTS - i));
            }
        }
        return Screen.EAT_CARD;
    }



}
