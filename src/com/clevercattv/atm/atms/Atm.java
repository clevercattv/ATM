package com.clevercattv.atm.atms;

import com.clevercattv.atm.cards.AtmCard;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.models.enums.Bill;
import com.clevercattv.atm.screens.AtmScreen;
import com.clevercattv.atm.screens.Screen;

import java.util.List;
import java.util.Map;

public interface Atm {

    void init();

    void update();

    void eatCard();

    boolean isActive() ;

    void setActive(boolean active);

    Map<Screen, AtmScreen> getScreens();

    AtmScreen getCurrentScreen();

    void setCurrentScreen(AtmScreen currentScreen);

    AtmCard getCurrentCard();

    void setCurrentCard(AtmCard currentCard);

    AtmConsole getConsole();

    List<AtmCard> getEatenCards();

    void setEatenCards(List<AtmCard> eatenCards);

    Map<Bill, Integer> getBills();

}
