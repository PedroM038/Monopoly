package assets;

public class MoneySpace extends Space {
    private int money;  // if negative, is a loss
    public MoneySpace(String description, int money) {
        this.description = description;
        this.money = money;
    }

    public boolean isLoss() {
        return this.money < 0;
    }

    public int getMoney() {
        return this.money;
    }
}