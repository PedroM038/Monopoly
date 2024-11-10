package model;

public class Vacation extends SpecialSpaces {
    int[] lazy = new int[8];

    public VacationSpace() {
    }

    public VacationSpace() {
        for (int i = 0; i < 8; i++)
	    this.lazy[i] = -1;
    }

    //returns player back to the game
    public void backToWork(Player player) {
        lazy[player.getId()] = -1;
        System.out.println("Player's vacation is over");
    }

    //counts the number of rounds a player spent in vacation
    public void vacationCount(Player player) {
        lazy[player.getId()]++;

	//if the number of rounds id ge than 3, then player can return to the game
	if (lazy[player.getId()] >= 3)
		backToWork(player);
    }
}
