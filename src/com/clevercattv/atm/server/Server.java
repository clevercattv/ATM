package com.clevercattv.atm.server;

import com.clevercattv.atm.card.AtmCard;
import com.clevercattv.atm.card.AtmCardImpl;
import com.clevercattv.atm.card.CardType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Server {

    public static final double WITHDRAW_INTEREST = 1.01;
    public static final int OPERATION_MAX_WITHDRAW = 5000;

    private static int counter = 1;

    private static final Set<AtmCard> CARDS = new HashSet<>();

    static {
        CARDS.add(new AtmCardImpl(counter++, 1234, CardType.CREDIT, 20000, "", true, 10000));
    }

    private Server() {
    }

    public static AtmCard generateCard() {
        AtmCard atmCard = new AtmCardImpl(counter++, 1111,
                CardType.CREDIT, new Random().nextInt(100000), "", false, 10000);
        CARDS.add(atmCard);
        return atmCard;
    }

    public static Optional<AtmCard> getCard(int cardNumber) {
        return CARDS.stream().filter(e -> e.matchingCardNumber(cardNumber)).findFirst();
    }

    /* In real world that's set card enable to false, not delete */
    public static void removeCard(AtmCard card) {
        CARDS.remove(card);
    }

    public static boolean withdraw(Integer withdraw, AtmCard card) {
        if (CARDS.contains(card)) {
            double withdrawWithIntegers = withdraw * WITHDRAW_INTEREST;
            if (card.getBalance() >= withdrawWithIntegers) {
                card.setBalance(card.getBalance() - withdrawWithIntegers);
                return true;
            }
        }
        return false;
    }

    /* Deposit without start percents WOW! */
    public static boolean deposit(Integer deposit, AtmCard card) {
        if (CARDS.contains(card) && card.getDepositBalance() > deposit) {
            card.setDepositBalance(card.getDepositBalance() - deposit);
            return true;
        }
        return false;
    }

    public static void callMaster() {
        /*
         * Imagine that this method have code
         */
    }

}
