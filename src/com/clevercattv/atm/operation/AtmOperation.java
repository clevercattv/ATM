package com.clevercattv.atm.operation;

import com.clevercattv.atm.exception.DefaultRealisationException;
import com.clevercattv.atm.model.OperationResponse;
import com.clevercattv.atm.model.enums.Bill;

import java.util.Map;

public interface AtmOperation {

    default OperationResponse operation(Integer money, Map<Bill, Integer> bills){
        throw new DefaultRealisationException();
    }

}
