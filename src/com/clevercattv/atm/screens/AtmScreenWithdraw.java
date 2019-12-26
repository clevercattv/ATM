package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.atms.AtmImpl;
import com.clevercattv.atm.cards.AtmCard;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.models.OperationResponse;
import com.clevercattv.atm.models.enums.Bill;
import com.clevercattv.atm.operations.AtmOperation;
import com.clevercattv.atm.operations.AtmOperationWithdraw;
import com.clevercattv.atm.servers.CardServer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clevercattv.atm.util.WithdrawUtil.canWithdraw;

public class AtmScreenWithdraw extends AtmScreenImpl {

    private static final int[] DEFAULT_WITHDRAW = new int[]{10, 20, 50, 100, 200, 500, 1000};
    private static List<Integer> operations = new ArrayList<>();
    private static final AtmOperation operation = new AtmOperationWithdraw();

    public AtmScreenWithdraw(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("Withdraw operations");
        console.println("(Max withdraw : " + AtmImpl.OPERATION_MAX_WITHDRAW + ")");
        fillDefaultWithdrawOperations(
                atm.getCurrentCard(),
                atm.getBills().entrySet()
                        .stream()
                        .filter(e -> e.getValue() > 0)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
        console.println("(8) - Back to main operations");
        console.println("(9) - End session");
        while (true) {
            int action = console.readInt();
            if (operations.contains(action) || action > 6) {
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
                        console.println("Enter required amount : ");
                        withdraw(console.readInt());
                        return Screen.WITHDRAW_OPERATION;
                    case 8:
                        return Screen.MAIN_OPERATIONS;
                    case 9:
                        return Screen.CARD_WAIT;
                    default:
                        return Screen.WITHDRAW_OPERATION;
                }
            }
            console.println("Unexpected operation!");
        }
    }

    @SuppressWarnings("unchecked")
    private void withdraw(Integer money) {
        OperationResponse<Map<Bill, Integer>> response = operation.operation(money, new EnumMap<>(atm.getBills()));
        console.println(response.getAdditionInformation());
        atm.updateBills(response.getItem());
    }

    private void fillDefaultWithdrawOperations(AtmCard card, Map<Bill, Integer> bills) {
        operations.clear();
        double balance = card.getBalance() * CardServer.WITHDRAW_INTEREST;
        for (int i = 0; i < DEFAULT_WITHDRAW.length; i++) {
            if (balance > DEFAULT_WITHDRAW[i] && canWithdraw(DEFAULT_WITHDRAW[i], bills)) {
                operations.add(i);
                console.println(String.format("(%d) - %d ", i, DEFAULT_WITHDRAW[i]));
            }
        }
    }

}
