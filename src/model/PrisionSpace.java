public class PrisionSpace extends Space {
    int[] prisioners = new int[8];

	public PrisionSpace() {
	}

	//sets the vector that records whos in prision
	public PrisionSpace() {
        for (int i; i < 8; i++)
			this.prisioners[i] = -1;
	}

    //release the player from prision
    public void freePrisioner(Player player) {
        prisioners[player.getId()] = -1;
	}

	//verify if the player can be released from prision
	public void cop(Player player, Dice dice) {
		//if the player gets 6 in the dice, release it
        if (dice.roll() == 6)
		    freePrisioner(player);
		else
			//else increments number of rounds spent in the prision
			prisioners[player.getId()]++;

		//if the number of rounds the player spent in the prision is ge than 3, release it
		if (prisioners[player.getId()] >= 3)
			freePrisioner(player);
	}

	//records the entry of the player into prision
	public void action(player Player) {
        prisioners[player.getId()] = 0;
	}
}
