/**
 * Card
 */
public class Card {

    private String description;
    private String action;
    private int amount;
    private String type;

    public Card (String description, String action, int amount, String type) {
        this.description = description;
        this.action = action;
        this.amount = amount;
        this.type = type;
    }

    public String getDescription(){
        return description;
    }

    public String getAction() {
        return action;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void applyEffect(Player player){
        switch (action) {
            case "pagar":
                /*método para subtrair dinheiro do player (Player Class)*/
                /*player.decreaseBalance(amoount);*/
                System.out.println( /*player.getName() + */" pagou R$" + amount);
                break;
            case "receber":
                /*método para somar dinheiro do player (Player Class)*/
                /*player.increaseBalance(amoount);*/
                System.out.println( /*player.getName() + */" recebeu R$" + amount);
                break;
            case "mover":
                /*método para mover o player*/
                /*player.moveTo(amoount);*/
                System.out.println( /*player.getName() + */" foi movido para" + amount);
                break;
        }
    }
}

