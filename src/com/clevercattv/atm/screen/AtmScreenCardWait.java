package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.exception.NoSuchCardException;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.server.Server;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import static com.clevercattv.atm.atm.AtmImpl.COUNT_ATTEMPTS;
import static com.clevercattv.atm.util.Constants.BAD_OPERATION;
import static com.clevercattv.atm.util.Constants.CHOOSE_OPERATION;

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
        for (int i = 1; i <= COUNT_ATTEMPTS; i++) {
            int pinCode = console.readInt("Enter card pin code : ");
            if (card.matchingPinCode(pinCode)) {
                return Screen.MAIN_OPERATIONS;
            } else {
                console.println(String.format("Wrong pin. Attempts left : %d", COUNT_ATTEMPTS - i));
            }
        }
        return Screen.EAT_CARD;
    }



}
