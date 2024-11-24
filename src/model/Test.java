//package model;
import assets.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main (String args[]) {
        Deck deck = new Deck();
        deck.loadCardsFromFile("../../assets/deck/cards.txt");
        ArrayList<Integer> properties = new ArrayList<>(Arrays.asList(2, 3, 7));
        ArrayList<Integer> cards = new ArrayList<>(Arrays.asList(4, 5));
        ArrayList<Integer> moneys = new ArrayList<>(Arrays.asList(8, 9));
        int prisonId = 1;
        Board board = new Board(10, 3, prisonId, properties, cards, moneys);

        Space space = board.getSpace(9);
        board.movePlayer(1, 15);
        System.out.println(board.goesThroughStart(1, 2));
        System.out.println(board.getPlayerPos(1));
        if (space instanceof MoneySpace) {
            MoneySpace temp = (MoneySpace) space;
            System.out.println(temp.getDescription());
        }
    }
}