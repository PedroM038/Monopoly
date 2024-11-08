package model;

public class ChanceSpace extends Space {
    
	public void action(Deck chance, Player player) {
        //pega uma carta de sorte ou reves
            Card card = new Card;
            card = chance.drawCard;
            System.out.println(card.getDescription);
            card.apllyEffect(player);
	}
}
