package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.cards.AtmCard;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.exceptions.NoSuchCardException;
import com.clevercattv.atm.servers.CardServer;

import static com.clevercattv.atm.atms.AtmImpl.COUNT_ATTEMPTS;

public class AtmScreenCardWait extends AtmScreenImpl {

    public AtmScreenCardWait(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    /**
     * In advanced ATM that's can be some think like this : CardReaderDriver.waitForCardInsert()
     */
    @Override
    public Screen init() {
        console.printNewOperation();
        int cardNumber = console.readInt("Insert card : ");
        AtmCard card = CardServer.getCard(cardNumber).orElseThrow(NoSuchCardException::new);
        for (int i = 1; i <= COUNT_ATTEMPTS; i++) {
            int pinCode = console.readInt("Write card pin code : ");
            if (card.matchingPinCode(pinCode)) {
                atm.setCurrentCard(card);
                return Screen.MAIN_OPERATIONS;
            } else {
                console.println(String.format("Wrong pin. Attempts left : %d", COUNT_ATTEMPTS - i));
            }
        }
        return Screen.EAT_CARD;
    }

}
