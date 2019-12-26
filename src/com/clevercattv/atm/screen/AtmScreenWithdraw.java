package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.model.OperationResponse;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.operation.AtmOperation;
import com.clevercattv.atm.operation.AtmOperationWithdraw;
import com.clevercattv.atm.server.Server;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clevercattv.atm.util.Constants.*;

public class AtmScreenWithdraw extends AtmScreenImpl {

    private static final int[] DEFAULT_WITHDRAW = new int[]{10, 20, 50, 100, 200, 500, 1000};
    private static List<Integer> availableWithdraw = new ArrayList<>();
    private static final AtmOperation operationWithdraw = new AtmOperationWithdraw();

    public AtmScreenWithdraw(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        Map<Bill, Integer> filteredBills = atm.getBills().entrySet()
                .stream()
                .filter(e -> e.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (filteredBills.isEmpty()) {
            return Screen.BILLS_STORAGE_EMPTY;
        }
        countAvailableWithdrawOperations(atm.getCurrentCard(), filteredBills);
        while (true) {
            console.printScreenName("Withdraw operations");
            console.println("(Max withdraw : $ " + Server.OPERATION_MAX_WITHDRAW + ")");
            availableWithdraw.forEach(operation -> console.println(
                    String.format("(%d) - $ %d ", operation, DEFAULT_WITHDRAW[operation])));
            console.println("(7) - Custom amount");
            console.println(TO_MAIN_OPERATIONS);
            console.println(END_SESSION);
            int action = console.readInt(CHOOSE_OPERATION);
            if (availableWithdraw.contains(action) || action > 6) {
                switch (action) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        withdraw(DEFAULT_WITHDRAW[action]);
                        return Screen.WITHDRAW_OPERATION;
                    case 7:
                        withdraw(console.readInt("Enter required amount : "));
                        return Screen.WITHDRAW_OPERATION;
                    case 8:
                        return Screen.MAIN_OPERATIONS;
                    case 9:
                        return Screen.CARD_WAIT;
                    default:
                        console.println(BAD_OPERATION);
                        continue;
                }
            }
            console.println(BAD_OPERATION);
        }
    }

    @SuppressWarnings("unchecked")
    private void withdraw(Integer money) {
        if (money > Server.OPERATION_MAX_WITHDRAW) {
            console.println(String.format(
                    "You can't withdraw more than %d per one operation.", Server.OPERATION_MAX_WITHDRAW));
        } else {
            OperationResponse<Map<Bill, Integer>> response =
                    operationWithdraw.operation(money, new EnumMap<>(atm.getBills()));
            if (!response.isSuccess()) {
                console.println(response.getAdditionInformation());
            } else {
                if (Server.withdraw(money, atm.getCurrentCard())) {
                    console.println(response.getAdditionInformation());
                    atm.updateBills(response.getItem());
                } else {
                    console.println("You haven't enough money!");
                }
            }
        }
    }

    private void countAvailableWithdrawOperations(AtmCard card, Map<Bill, Integer> bills) {
        availableWithdraw.clear();
        double balance = card.getBalance() * Server.WITHDRAW_INTEREST;
        for (int i = 0; i < DEFAULT_WITHDRAW.length; i++) {
            if (balance > DEFAULT_WITHDRAW[i] &&
                    operationWithdraw.operation(DEFAULT_WITHDRAW[i], bills).isSuccess()) {
                availableWithdraw.add(i);
            }
        }
    }

}
