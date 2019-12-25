package com.clevercattv.atm.screens;

import com.clevercattv.atm.atms.Atm;
import com.clevercattv.atm.atms.AtmImpl;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.models.enums.Bill;

import java.util.*;
import java.util.stream.Collectors;

public class AtmScreenWithdraw extends AtmScreenImpl {

    private static final int[] DEFAULT_WITHDRAW = new int[]{10, 20, 50, 100, 200, 500, 1000};
    private static List<Integer> operations = new ArrayList<>();

    public AtmScreenWithdraw(AtmConsole console, Atm atm) {
        super(console, atm);
    }

    @Override
    public Screen init() {
        console.println("Withdraw operations");
        console.println("(Max withdraw : " + AtmImpl.OPERATION_MAX_WITHDRAW + ")");
        fillDefaultWithdrawOperations(
                atm.getBills().entrySet()
                        .stream()
                        .filter(e -> e.getValue() > 0)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
        console.println("(8) - Back to main operations");
        console.println("(9) - End session");
        while (true) {
            int operation = console.readInt();
            if (operations.contains(operation) || operation > 6) {
                switch (operation) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        withdraw(DEFAULT_WITHDRAW[operation], atm.getBills());
                        return Screen.WITHDRAW_OPERATION;
                    case 7:
                        console.println("Enter required amount : ");
                        withdraw(console.readInt(), atm.getBills());
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

    private void fillDefaultWithdrawOperations(Map<Bill, Integer> bills) {
        operations.clear();
        for (int i = 0; i < DEFAULT_WITHDRAW.length; i++) {
            if (canWithdraw(DEFAULT_WITHDRAW[i], bills)) {
                operations.add(i);
                console.println(String.format("(%d) - %d ", i, DEFAULT_WITHDRAW[i]));
            }
        }
    }

    private void withdraw(Integer fullCount, Map<Bill, Integer> bills) {
        for (Map.Entry<Bill, Integer> bill : bills.entrySet()) {
            int resultBillsCount = countResultBills(0, fullCount, bill.getKey());
            if (bill.getValue() < resultBillsCount) {
                resultBillsCount = bill.getValue();
            }
            fullCount -= bill.getKey().getMoney() * resultBillsCount;
            bills.put(bill.getKey(), bill.getValue() - resultBillsCount);
            if (fullCount == 0) return;
        }
        throw new RuntimeException("Need " + fullCount + " more..");
    }

    private boolean canWithdraw(Integer fullCount, Map<Bill, Integer> bills) {
        for (Map.Entry<Bill, Integer> bill : bills.entrySet()) {
            int resultBillsCount = countResultBills(0, fullCount, bill.getKey());
            if (bill.getValue() < resultBillsCount) {
                resultBillsCount = bill.getValue();
            }
            fullCount -= bill.getKey().getMoney() * resultBillsCount;
            if (fullCount == 0) return true;
        }
        return false;
    }

    private Integer countResultBills(Integer count, Integer left, Bill bill) {
        return left >= bill.getMoney() ?
                countResultBills(++count, left - bill.getMoney(), bill) : count;
    }

}
