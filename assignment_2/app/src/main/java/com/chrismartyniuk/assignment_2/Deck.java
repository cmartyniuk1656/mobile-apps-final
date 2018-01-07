package com.chrismartyniuk.assignment_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cmart on 2017-12-12.
 */

public class Deck {

    private ArrayList<Card> currentDeck;

    public Deck() {
        currentDeck = generateDeck();
    }

    private ArrayList<Card> generateDeck() {

        ArrayList<Card> generatedDeck = new ArrayList<Card>();

        //Incrementing card idValue
        for (int i = 0; i < 13; i++) {

            //Incrementing card suit value
            for (int x = 0; x < 4; x++) {

                String suit;

                switch (x) {
                    case 0:
                        suit = "clubs";
                        break;
                    case 1:
                        suit = "spades";
                        break;
                    case 2:
                        suit = "hearts";
                        break;
                    case 3:
                        suit = "diamonds";
                        break;
                    default:
                        suit = "clubs";
                }

                //Genenerate a new card and addd it to the deck
                generatedDeck.add(new Card(i, suit));
            }

        }

        //Shuffle the deck
        Collections.shuffle(generatedDeck);
        return generatedDeck;
    }

    public Card dealCard() {

        Card cardToDeal = currentDeck.get(0);
        currentDeck.remove(0);
        return cardToDeal;

    }

    public void newDeck() {
        currentDeck.clear();
        currentDeck = generateDeck();
    }



}
