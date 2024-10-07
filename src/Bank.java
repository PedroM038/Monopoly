public class Bank {
    private Integer maxDebt;  // max debt a player can have
    private Integer playersDebt[];  // each player's debt
    private double taxRate;  // tax rate of debt

    public Bank (Integer maxDebt, double taxRate, int numPlayers) {
        this.maxDebt = maxDebt;
        this.taxRate = taxRate;
        this.playersDebt = new Integer[numPlayers];
        for (int i = 0; i < numPlayers; i++) this.playerDebt[i] = 0;
    }

    // if player can loan money
    public boolean canGiveLoan (Integer playerId, Integer quantity) {
        return quantity + this.playersDebt[playerId] <= this.maxDebt;
    }

    // gives player a loan
    // if cannot give, throws error
    // else, returns the loan quantity
    public Integer giveLoan (Integer playerId, Integer quantity) {
        if (quantity + this.playersDebt[playerId] > this.maxDebt)
            throw new IllegalArgumentException("Quantity cannot exceed player debt");
        playersDebt[playerId] += quantity;
        return quantity;
    }

    // pays player's debt
    // if exceeds debt value, returns remainder quantity
    public Integer payDept (Integer playerId, Integer quantity) {
        Integer remainder = Math.max(0, quantity - this.playersDebt[playerId]);
        this.playersDebt[playerId] = Math.max(0, this.playersDebt[playerId] - quantity);
        return remainder;
    }

    // applies taxes to player's debt
    public void applyTax (Integer playerId) {
        playersDebt[playerId] = (1 + this.taxRate) * playersDebt[playerId];
    }

    // applies taxes to all player's debt
    public void applyTaxAll () {
        for (int i = 0; i < this.playersDebt.length; i++)
            this.applyTax(i);
    }

    // returns if the player has exceeded his debt
    public boolean hasExceededDebt (Integer playerId) {
        return this.playersDebt[playerId] > this.maxDebt;
    }
}
