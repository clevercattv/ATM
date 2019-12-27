package com.clevercattv.atm.operation;

import com.clevercattv.atm.model.OperationResponse;
import com.clevercattv.atm.model.enums.Bill;

import java.util.Map;

public class AtmOperationPayment implements AtmOperation {

    @Override
    public OperationResponse<Map<Bill, Integer>> operation(Integer money, Map<Bill, Integer> bills) {
        for (Map.Entry<Bill, Integer> entry : bills.entrySet()) {
            Bill bill = entry.getKey();
            Integer countBill = entry.getValue();
            int resultBillsCount = countResultBills(0, money, bill);
            resultBillsCount = countBill < resultBillsCount ? countBill : resultBillsCount;
            money -= bill.getMoney() * resultBillsCount;
            bills.put(bill, countBill - resultBillsCount);
            if (money == 0) return new OperationResponse<>(bills, true,
                    "Payment of the money success complete.");
        }
        return new OperationResponse<>(null, false,
                "ATM didn't have enougth money..");
    }

    private Integer countResultBills(Integer count, Integer left, Bill bill) {
        return left >= bill.getMoney() ?
                countResultBills(++count, left - bill.getMoney(), bill) : count;
    }

}
