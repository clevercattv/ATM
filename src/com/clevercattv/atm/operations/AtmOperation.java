package com.clevercattv.atm.operations;

import com.clevercattv.atm.models.OperationResponse;
import com.clevercattv.atm.screens.AtmScreen;

import java.math.BigDecimal;

public interface AtmOperation {

//    Пополнить телефон
//    Баланс карты
//    Выдача наличных
//    Центр денежных переводов
//    Мои настройки

    OperationResponse operation(BigDecimal money);

}
