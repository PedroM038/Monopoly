/*
 * The PlayerSpaceHandler class handles all necessary actions when a player falls in a space
 * It does not store data, only does the actions necessary
 * Acts like a static class
 */

package codemodel;
import codemodel.assets.*;

public class PlayerSpaceHandler {
    public static Info solve(Player player, Space space, int spaceId, ModelInterface model) {
        Info info = new Info(player);
        info.space = space;
        info.spaceId = spaceId;
        if (space instanceof CardSpace) {
            PlayerSpaceHandler.solveCardSpace(player, (CardSpace) space, info, model);
        }
        else if (space instanceof MoneySpace) {
            PlayerSpaceHandler.solveMoneySpace(player, (MoneySpace) space, info, model);
        }
        else if (space instanceof PrisonSpace) {
            PlayerSpaceHandler.solvePrisonSpace(player, (PrisonSpace) space, info, model);
        }
        else if (space instanceof PropertySpace) {
            PlayerSpaceHandler.solvePropertySpace(player, (PropertySpace) space, info, model);
        }
        else if (space instanceof VacationSpace) {
            PlayerSpaceHandler.solveVacationSpace(player, (VacationSpace) space, info, model);
        }

        if (player.getProperties().size() > 0) {
            info.possible_actions.add("sell");
        }
        return info; 
    }

    private static void solveCardSpace(Player player, CardSpace space, Info info, ModelInterface model) {
        Deck deck = model.getDeck();
        Card card = deck.drawCard();
        Bank.applyCardToPlayer(player, card);
        info.card = card;
    }

    private static void solveMoneySpace(Player player, MoneySpace space, Info info, ModelInterface model) {
        int money = space.getMoney();
        if (space.isLoss()) {
            player.pay(-money);
        }
        else {
            player.remunerate(money);
        }
    }

    private static void solvePrisonSpace(Player player, PrisonSpace space, Info info, ModelInterface model) {
        JailGuard guard = model.getGuard();
        guard.lockPlayer(player);
        info.gotLocked = true;
    }

    private static void solvePropertySpace(Player player, PropertySpace space, Info info, ModelInterface model) {
        Property property = space.getProperty();
        int ownerId = property.getOwnerId();
        if (ownerId == -1) {
            info.possible_actions.add("buy");
        }
        else if (ownerId == player.getId() && !property.hasAllHouses()) {
            info.possible_actions.add("buyHouse");
        }
        else {
            Player owner = model.getPlayers().get(ownerId);
            Bank.chargeRent(owner, player, property);
        }
    }

    private static void solveVacationSpace(Player player, VacationSpace space, Info info, ModelInterface model) {
        return;
    }
}