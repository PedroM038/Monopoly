/*
 * The Bank class represents the banker from the game
 * It's purpose is to do all financial transactions from the game
 * It does not store data, and acts as a static class 
 */

package codemodel;
import codemodel.assets.*;
import java.io.*;

public class Bank implements Serializable {
    public static boolean canBuyProperty(Player player, Property property) {
        return player.getMoney() >= property.getActualPrice();
    }

    public static void buyProperty(Player player, Property property) {
        player.pay(property.getActualPrice());
        player.buyProperty(property);
    }

    public static void buyHouse(Player player, Property property) {
        player.pay(property.getHousePrice());
        property.buyHouse();
    }

    public static void sellProperty(Player player, Property property) {
        player.sellProperty(property);
        player.remunerate(property.getActualPrice());
    }

    public static void payPlayer(Player player, int money) {
        player.remunerate(money);
    }

    public static void chargePlayer(Player player, int money) {
        player.pay(money);
    }

    public static void chargeRent(Player owner, Player tenant, Property property) {
        int rent = property.getRentValue();
        Bank.chargePlayer(tenant, rent);
        Bank.payPlayer(owner, rent);
    }

    // returns true if player gains money, false otherwise
    public static boolean applyCardToPlayer(Player player, Card card) {
        if (card.getType().equals("Sorte")) {
            player.remunerate(card.getAmount());
            return true;
        }
        else {
            player.pay(card.getAmount());
            return false;
        }
    }
}