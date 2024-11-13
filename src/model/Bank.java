package model;

import assets.*;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Property> properties;
    Deck deck;

    public Bank(ArrayList<Property> properties, Deck deck) {
        this.properties = properties;
        this.deck = deck;
    }

    // player buys property
    // returns false if player cant buy it
    public boolean buyProperty(String propertyName, player p) {
        for (int i = 0; i < properties.size(); i++) {
            if (this.properties[i].getName().equals(propertyName)) {
                return this.properties[i].buy(p);
            }
        }
        // could not find property
        return false;
    }

    // owner sells property
    // return false if player cant sell it
    public boolean sellProperty(String propertyName) {
        for (int i = 0; i < properties.size(); i++) {
            if (this.properties[i].getName().equals(propertyName)) {
                return this.properties[i].sell();
            }
        }
        // could not find property
        return false;
    }

    // bank gives money to player
    public void pay(player p, int money) {
        p.earn(money);
    }

    // player pays rent to owner
    public void payRent(String propertyName, player p) {
        for (int i = 0; i < properties.size(); i++) {
            if (this.properties[i].getName().equals(propertyName)) {
                return this.properties[i].applyRent(p);
            }
        }
        // could not find property
        return false;
    }

    // draws a card from deck and applies effect to player
    // returns card 
    public Card drawCard(player p) {
        Card card = this.Deck.drawCard();
        card.applyEffect(p);
        return card;
    }


    // GETTERS
    public ArrayList<Property> getProperties() {
        return this.properties;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
