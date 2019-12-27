package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.server.Server;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import static com.clevercattv.atm.util.Constants.*;

public class AtmScreenMainOperations extends AtmScreenImpl {

    public AtmScreenMainOperations(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.printScreenName("Main operations");
        console.print("(0) - Withdraw     ");
        console.println("(1) - Deposit");
        console.println("(2) - Check balance");
        boolean isAdmin = atm.getCurrentCard().isAdmin();
        if (isAdmin) {
            printAdminOperations();
        }
        console.print("(7) - Transfer     ");
        console.println("(8) - Change pin");
        console.println(END_SESSION);
        while (true) {
            int operation = console.readInt(CHOOSE_OPERATION);
            if (operation > 2 && operation < 7 && !isAdmin) {
                console.println(BAD_OPERATION);
                continue;
            }
            switch (operation) {
                case 0:
                    return Screen.WITHDRAW_OPERATION;
                case 1:
                    return Screen.DEPOSIT_OPERATION;
                case 2:
                    return Screen.CHECK_BALANCE;
                case 3:
                    atm.getBills().forEach((key, value) ->
                            console.printlnError(String.format(" Bill %s Count : %s", key, value)));
                    continue;
                case 4:
                    int money = 0;
                    for (Map.Entry<Bill, Integer> entry : atm.getBills().entrySet()) {
                        money += entry.getKey().getMoney() * entry.getValue();
                    }
                    console.printlnError(money + "");
                    continue;
                case 5:
                    console.println(Server.generateCard().toString());
                    continue;
                case 6:
                    Map<Bill, Integer> bills = new EnumMap<>(Bill.class);
                    Arrays.stream(Bill.values())
                            .forEach(bill -> bills.put(bill, new Random().nextInt(1000)));
                    atm.updateBills(bills);
                    bills.forEach((key, value) -> console.println(String.format("%s count %s", key, value)));
                    continue;
                case 7:
                    return Screen.TRANSFER_OPERATION;
                case 8:
                    return Screen.CHANGE_PIN;
                case 9:
                    atm.setCurrentCard(null);
                    return Screen.CARD_WAIT;
                default:
                    console.println(BAD_OPERATION);
            }
        }
    }

    private void printAdminOperations() {
        console.println("*****ADMIN OPERATIONS*****");
        console.println("(3) - ATM bills   ");
        console.println("(4) - Count money in ATM ");
        console.println("(5) - Generate random card");
        console.println("(6) - Generate random ATM bills");
        console.println("**************************");
    }

}
