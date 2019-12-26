package com.clevercattv.atm.operations;

import com.clevercattv.atm.models.OperationResponse;
import com.clevercattv.atm.models.enums.Bill;

import java.util.Map;

import static com.clevercattv.atm.util.WithdrawUtil.countResultBills;

public class AtmOperationWithdraw implements AtmOperation {

    @Override
    public OperationResponse<Map<Bill, Integer>> operation(Integer money, Map<Bill, Integer> bills) {
        for (Map.Entry<Bill, Integer> bill : bills.entrySet()) {
            int resultBillsCount = countResultBills(0, money, bill.getKey());
            if (bill.getValue() < resultBillsCount) {
                resultBillsCount = bill.getValue();
            }
            money -= bill.getKey().getMoney() * resultBillsCount;
            bills.put(bill.getKey(), bill.getValue() - resultBillsCount);
            if (money == 0) return new OperationResponse<>(bills,true,"Withdraw complete.");
        }
        return new OperationResponse<>(null,false,"Some think fail.");
    }

}
