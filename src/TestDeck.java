public class TestDeck{
    public static void main(String[] args){
        Deck chanceDeck = new Deck("Sorte");
        chanceDeck.loadCardsFromFile("../assets/deck/cards.txt");
        chanceDeck.shuffle();
        Card card = chanceDeck.drawCard();

        if (card != null) {
            System.out.println("Descrição carta de Sorte: " + card.getDescription() 
            + "\n Ação: " + card.getAction()
            + "\n Valor: " + card.getAmount()
            + "\n Tipo: " + card.getType() + "\n");
        }

        Deck communityChestDeck = new Deck("Revés");
        communityChestDeck.loadCardsFromFile("../assets/deck/cards.txt");
        communityChestDeck.shuffle();
        card = communityChestDeck.drawCard();

        if (card != null) {
            System.out.println("Descrição carta de Revés: " + card.getDescription() 
            + "\n Ação: " + card.getAction()
            + "\n Valor: " + card.getAmount()
            + "\n Tipo: " + card.getType());
        }
    }
}
