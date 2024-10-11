import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Card> cards;
    private String type;

    public Deck(String type){
        this.cards = new ArrayList<>();
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void loadCardsFromFile(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String description = parts[0];
                String action = parts[1];
                int amount = Integer.parseInt(parts[2]);
                String cardType = parts[3];

                if(this.type.equals(cardType)) {
                    cards.add(new Card(description, action, amount, cardType));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        if(cards.isEmpty()){
            resetDeck();
        }
        return cards.remove(0);
    }

    public void resetDeck(){
        System.out.println("Reembaralhando o baralho...");
        shuffle();
    }
}
