package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.server.Server;

import java.util.Optional;

public class AtmScreenTransfer extends AtmScreenImpl {

    public AtmScreenTransfer(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.printScreenName("Transfer");
        int recipientCardNumber = console.readInt("Enter recipient card number : ");
        Optional<AtmCard> card = Server.getCard(recipientCardNumber);
        if (card.isPresent()) {
            if (Server.transfer(atm.getCurrentCard(),card.get(),
                    console.readInt("Enter transfer count : "))){
                console.println("Transfer complete!");
                return Screen.MAIN_OPERATIONS;
            } else {
                console.println("Not enough money! ");
                return Screen.TRANSFER_OPERATION;
            }
        } else {
            console.println("No such card!");
            return Screen.MAIN_OPERATIONS;
        }
    }

}
