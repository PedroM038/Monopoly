package assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Card> cards;
    private int numDrawnCards; 

    public Deck(){
        this.cards = new ArrayList<>();
    }

    public void loadCardsFromFile(String filename){
        this.numDrawnCards = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String description = parts[0];
                String action = parts[1];
                int amount = Integer.parseInt(parts[2]);
                String cardType = parts[3];

                this.cards.add(new Card(description, action, amount, cardType));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
        this.shuffle();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        if(this.numDrawnCards == this.cards.size()){
            this.shuffle();
        }
        Card card = this.cards.remove(0);
        this.cards.add(card);
        this.numDrawnCards += 1;
        return card;
    }
}
