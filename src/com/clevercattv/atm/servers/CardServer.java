package com.clevercattv.atm.servers;

import com.clevercattv.atm.cards.AtmCard;
import com.clevercattv.atm.cards.AtmCardImpl;
import com.clevercattv.atm.cards.CardType;
import com.clevercattv.atm.exceptions.NoSuchCardException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CardServer {

    private static final Set<AtmCard> CARDS = new HashSet<>();
    public static final double WITHDRAW_INTEREST = 1.01;

    static {
        CARDS.add(new AtmCardImpl(1,1234, CardType.CREDIT,100,""));
    }

    private CardServer(){}

    public static Optional<AtmCard> getCard(int cardNumber) {
        return CARDS.stream().filter(e -> e.matchingCardNumber(cardNumber)).findFirst();
    }

    public static boolean removeCard(AtmCard card) {
        return CARDS.remove(card);
    }

    public static boolean canWithdraw(double withdraw, AtmCard card) {
        if (CARDS.contains(card)){
            if (card.getBalance() * WITHDRAW_INTEREST >= withdraw){

            }
        } else {
            throw new NoSuchCardException();
        }
        return false;
    }

}
