package com.clevercattv.atm.atms;

import com.clevercattv.atm.cards.AtmCard;
import com.clevercattv.atm.consoles.AtmConsole;
import com.clevercattv.atm.screens.*;
import com.clevercattv.atm.servers.CardServer;

import java.util.*;

public class AtmImpl implements Atm {

    public static final int COUNT_ATTEMPTS = 3;
    public static final int EATEN_CARD_SLOT_SIZE = 100;
    public static final int MONEY_SLOT_SIZE = 200;

    private final Map<Screen, AtmScreen> screens = new EnumMap<>(Screen.class);

    private AtmScreen currentScreen;
    private AtmCard currentCard;
    private boolean active = true;
    private final AtmConsole console;
    private List<AtmCard> eatenCards = new ArrayList<>();

    public AtmImpl(AtmConsole console) {
        this.console = console;
    }

    @Override
    public void init() {
        initScreens();
        waitScreen();
        update();
    }

    @Override
    public void update() {
        try {
            while (active) {
                currentScreen = screens.get(currentScreen.init());
            }
            screens.get(Screen.CARD_WAIT).init();
        } catch (Exception e) {
            console.println(e.getMessage());
            update();
        }
    }

    private void initScreens() {
        screens.clear();
        screens.put(Screen.CARD_WAIT, new AtmScreenCardWaitScreen(console, this));
        screens.put(Screen.CARD_SLOT_FULL, new AtmScreenCardSlotFull(console, this));
        screens.put(Screen.MAIN_OPERATIONS, new AtmScreenMainOperations(console, this));
    }

    private void waitScreen() {
        currentScreen = screens.get(Screen.CARD_WAIT);
    }

    public void eatCard() {
        CardServer.removeCard(currentCard);
        eatenCards.add(currentCard);
        currentCard = null;
        if (eatenCards.size() == EATEN_CARD_SLOT_SIZE) {
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<Screen, AtmScreen> getScreens() {
        return screens;
    }

    public AtmScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(AtmScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public AtmCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(AtmCard currentCard) {
        this.currentCard = currentCard;
    }

    public AtmConsole getConsole() {
        return console;
    }

    public List<AtmCard> getEatenCards() {
        return eatenCards;
    }

    public void setEatenCards(List<AtmCard> eatenCards) {
        this.eatenCards = eatenCards;
    }

}
