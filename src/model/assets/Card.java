package assets;
import java.io.*;

public class Card implements Serializable {

    private final String description;
    private final String action;
    private final Integer amount;
    private final String type;

    public Card (String description, String action, Integer amount, String type) {
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

