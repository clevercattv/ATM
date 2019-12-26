package com.clevercattv.atm.operations;

import com.clevercattv.atm.exceptions.DefaultRealisationException;
import com.clevercattv.atm.models.OperationResponse;
import com.clevercattv.atm.models.enums.Bill;
import com.clevercattv.atm.screens.AtmScreen;

import java.math.BigDecimal;
import java.util.Map;

public interface AtmOperation {

//    Пополнить телефон
//    Баланс карты
//    Выдача наличных
//    Центр денежных переводов
//    Мои настройки

    default OperationResponse operation(Integer money, Map<Bill, Integer> bills){
        throw new DefaultRealisationException();
    }

}
