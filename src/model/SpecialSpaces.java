package model;

public class SpecialSpaces extends Space {

    //gets a chance card from the deck and executes it
    public void action(Bank bank, Player player) {
        Card card = bank.drawCard(player);
		System.out.println(card.getDescription());
    }

    //Start space: player earns 200
    public void action(Player player, Bank bank) {
        bank.pay(player, 200);
    }

    //Go to prision space
    public void action(Player player, Prison prison) {
        prison.lockPlayer(player.getId());
    }

    //vacation space
    public void action(Player player) {
        int player_id = player.getId();
        if (lazy[player_id] < 0)
            lazy[player_id] = 0;
    }
}
