package assets;

public class CardSpace extends Space {
    public CardSpace() {
        this.description = "SORTE/REVÉS";
        this.type = SpaceConstants.CARD_SPACE_TYPE;
    }

    public Card drawCard(Deck deck) {
        return deck.drawCard();
    }
}