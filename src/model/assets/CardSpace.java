package assets;

public class CardSpace extends Space {
    public CardSpace() {
        this.description = "SORTE/REVÉS";
    }

    public Card drawCard(Deck deck) {
        return deck.drawCard();
    }
}