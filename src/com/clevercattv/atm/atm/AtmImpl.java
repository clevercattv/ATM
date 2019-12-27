package com.clevercattv.atm.atm;

import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.console.AtmConsole;
import com.clevercattv.atm.exception.WrongBillsMapException;
import com.clevercattv.atm.model.enums.Bill;
import com.clevercattv.atm.screen.*;
import com.clevercattv.atm.server.Server;

import java.util.*;

public class AtmImpl implements Atm {

    public static final int COUNT_ATTEMPTS = 3;
    private static final int EATEN_CARD_SLOT_SIZE = 100;

    private final Map<Screen, AtmScreen> screens = new EnumMap<>(Screen.class);
    private final Map<Bill, Integer> bills = new EnumMap<>(Bill.class);

    private AtmScreen currentScreen;
    private AtmCard currentCard;
    private boolean active = true;
    private final AtmConsole console;
    private List<AtmCard> eatenCards = new ArrayList<>();

    public AtmImpl(AtmConsole console) {
        this.console = console;
        Arrays.stream(Bill.values()).forEach(bill -> bills.put(bill, 0));
    }

    @Override
    public void init() {
        fillAtmScreens();
        waitScreen();
        initScreen();
    }

    @Override
    public void initScreen() {
        try {
            while (active) {
                setCurrentScreen(screens.get(currentScreen.init()));
            }
        } catch (Exception e) {
            console.refreshScanner();
            console.printlnError(e.getMessage());
            initScreen();
        }
    }

    private void fillAtmScreens() {
        screens.clear();
        screens.put(Screen.DEPOSIT_OPERATION, new AtmScreenDeposit(console, this));
        screens.put(Screen.BILLS_STORAGE_EMPTY, new AtmScreenBillsStorageEmpty(console, this));
        screens.put(Screen.CHECK_BALANCE, new AtmScreenCheckBalance(console, this));
        screens.put(Screen.WITHDRAW_OPERATION, new AtmScreenWithdraw(console, this));
        screens.put(Screen.EAT_CARD, new AtmScreenCardEaten(console, this));
        screens.put(Screen.CARD_WAIT, new AtmScreenCardWait(console, this));
        screens.put(Screen.CARD_SLOT_FULL, new AtmScreenEatenCardSlotFull(console, this));
        screens.put(Screen.MAIN_OPERATIONS, new AtmScreenMainOperations(console, this));
    }

    private void waitScreen() {
        currentScreen = screens.get(Screen.CARD_WAIT);
    }

    @Override
    public void eatCard() {
        Server.removeCard(currentCard);
        eatenCards.add(currentCard);
        currentCard = null;
        if (eatenCards.size() == EATEN_CARD_SLOT_SIZE) {
            active = false;
        }
    }

    @Override
    public void updateBills(Map<Bill, Integer> bills) {
        if (bills.size() != Bill.values().length) {
            throw new WrongBillsMapException();
        }
        this.bills.putAll(bills);
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Map<Screen, AtmScreen> getScreens() {
        return screens;
    }

    @Override
    public void setCurrentScreen(AtmScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    @Override
    public AtmCard getCurrentCard() {
        return currentCard;
    }

    @Override
    public void setCurrentCard(AtmCard currentCard) {
        this.currentCard = currentCard;
    }

    @Override
    public Map<Bill, Integer> getBills() {
        return bills;
    }

}
