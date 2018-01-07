package com.chrismartyniuk.assignment_2;

import java.util.ArrayList;

/**
 * Created by cmart on 2017-12-12.
 */

public class Player {

    private ArrayList<Card> hand;
    public int handValue;

    public Player() {
        hand = new ArrayList<Card>();
    }

    public void dealCard(Deck deck) {
        hand.add(deck.dealCard());
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> passedHand) {
        hand = passedHand;
    }

}
