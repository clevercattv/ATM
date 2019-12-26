package com.clevercattv.atm.util;

import com.clevercattv.atm.models.enums.Bill;

import java.util.Map;

public class WithdrawUtil {

    public static boolean canWithdraw(Integer fullCount, Map<Bill, Integer> bills) {
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

    public static Integer countResultBills(Integer count, Integer left, Bill bill) {
        return left >= bill.getMoney() ?
                countResultBills(++count, left - bill.getMoney(), bill) : count;
    }
    
}
