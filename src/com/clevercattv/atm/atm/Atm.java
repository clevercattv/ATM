package com.clevercattv.atm.atm;

import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.screen.AtmScreen;
import com.clevercattv.atm.screen.Screen;

import java.util.Map;

public interface Atm {

    void init();

    void initScreen();

    void eatCard();

    void updateBills(Map<Bill, Integer> bills);

    void setActive(boolean active);

    Map<Screen, AtmScreen> getScreens();

    void setCurrentScreen(AtmScreen currentScreen);

    AtmCard getCurrentCard();

    void setCurrentCard(AtmCard currentCard);

    Map<Bill, Integer> getBills();

}
