package assets;

public class Card {

    private final String description;
    private final String action;
    private final int amount;
    private final String type;

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
}

