package com.chrismartyniuk.assignment_2;

/**
 * Created by cmart on 2017-12-12.
 */

public class Card {

    public int value;
    public int secondValue;
    public boolean isAce;
    public int idValue;
    public String face;
    public String suit;
    public boolean played;

    public Card(int passedValue, String passedSuit) {

        idValue = passedValue;
        suit = passedSuit;
        played = false;

        generateValues(idValue);

    }

    private void generateValues(int passedValue) {

        switch (passedValue) {
            case 0:
                face = "A";
                value = 11;
                isAce = true;
                secondValue = 11;
                break;
            case 1:
                face = "2";
                value = 2;
                break;
            case 2:
                face = "3";
                value = 3;
                break;
            case 3:
                face = "4";
                value = 4;
                break;
            case 4:
                face = "5";
                value = 5;
                break;
            case 5:
                face = "6";
                value = 6;
                break;
            case 6:
                face = "7";
                value = 7;
                break;
            case 7:
                face = "8";
                value = 8;
                break;
            case 8:
                face = "9";
                value = 9;
                break;
            case 9:
                face = "10";
                value = 10;
                break;
            case 10:
                face = "J";
                value = 10;
                break;
            case 11:
                face = "Q";
                value = 10;
                break;
            case 12:
                face = "K";
                value = 10;
                break;
        }

    }

}
