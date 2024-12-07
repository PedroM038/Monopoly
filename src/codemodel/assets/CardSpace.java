/*
* The CardSpace class represents a class that gives a card to the players that falls into this space
*/
package codemodel.assets;

public class CardSpace extends Space {
    public CardSpace() {
        this.description = "SORTE/REVÉS";
    }

    public Card drawCard(Deck deck) {
        return deck.drawCard();
    }
}
