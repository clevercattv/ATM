package com.clevercattv.atm.screen;

import com.clevercattv.atm.atm.Atm;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.model.OperationResponse;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.operation.AtmOperation;
import com.clevercattv.atm.operation.AtmOperationPayment;
import com.clevercattv.atm.server.Server;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clevercattv.atm.util.Constants.*;

public class AtmScreenDeposit extends AtmScreenImpl {

    private static final int[] DEFAULT_DEPOSIT = new int[]{10, 20, 50, 100, 200, 500, 1000};
    private static List<Integer> availableDeposit = new ArrayList<>();
    private static final AtmOperation operationDeposit = new AtmOperationPayment();

    public AtmScreenDeposit(AtmConsole console, Atm atm) {
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
        countAvailableDepositOperations(filteredBills);
        while (true) {
            console.printScreenName("Deposit operations");
            console.println("(Max deposit : $ " + Server.OPERATION_MAX_WITHDRAW + ")");
            availableDeposit.forEach(operation -> console.println(
                    String.format("(%d) - $ %d ", operation, DEFAULT_DEPOSIT[operation])));
            console.println("(7) - Custom amount");
            console.println(TO_MAIN_OPERATIONS);
            console.println(END_SESSION);
            int action = console.readInt(CHOOSE_OPERATION);
            if (availableDeposit.contains(action) || action > 6) {
                switch (action) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        withdraw(DEFAULT_DEPOSIT[action]);
                        return Screen.DEPOSIT_OPERATION;
                    case 7:
                        withdraw(console.readInt("Enter required amount : "));
                        return Screen.DEPOSIT_OPERATION;
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
                    operationDeposit.operation(money, new EnumMap<>(atm.getBills()));
            if (!response.isSuccess()) {
                console.println(response.getAdditionInformation());
            } else {
                if (Server.deposit(money, atm.getCurrentCard())) {
                    console.println(response.getAdditionInformation());
                    atm.updateBills(response.getItem());
                } else {
                    console.println("You haven't enough deposit money!");
                }
            }
        }
    }

    private void countAvailableDepositOperations(Map<Bill, Integer> bills) {
        availableDeposit.clear();
        for (int i = 0; i < DEFAULT_DEPOSIT.length; i++) {
            if (operationDeposit.operation(DEFAULT_DEPOSIT[i], bills).isSuccess()) {
                availableDeposit.add(i);
            }
        }
    }

}
