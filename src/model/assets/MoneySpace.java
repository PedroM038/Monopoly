package assets;


public class MoneySpace extends Space {
    private Integer money;  // if negative, is a loss
    public MoneySpace(String description, Integer money) {
        this.description = description;
        this.money = money;
    }

    public boolean isLoss() {
        return this.money < 0;
    }

    public String getDescription() {
        return this.description;
    }

    public int getMoney() {
        return this.money;
    }
}