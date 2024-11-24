//package model;

import assets.*;

public class PlayerSpaceHandler {
    public static Info solve(Player player, Space space, Info info) {
       if (space instanceof CardSpace) {
        return PlayerSpaceHandler.solveCardSpace(player, (CardSpace) space);
       }
       else if (space instanceof MoneySpace) {
        return PlayerSpaceHandler.solveMoneySpace(player, (MoneySpace) space);
       }
       else if (space instanceof PrisonSpace) {
        return PlayerSpaceHandler.solvePrisonSpace(player, (PrisonSpace) space);
       }
       else if (space instanceof PropertySpace) {
        return PlayerSpaceHandler.solvePropertySpace(player, (PropertySpace) space);
       }
       else if (space instanceof VacationSpace) {
        return PlayerSpaceHandler.solveVacationSpace(player, (VacationSpace) space);
       }
       return null; 
    }

    private static Info solveCardSpace(Player player, CardSpace space) {
        return null;
    }

    private static Info solveMoneySpace(Player player, MoneySpace space) {
        return null;
    }

    private static Info solvePrisonSpace(Player player, PrisonSpace space) {
        return null;
    }

    private static Info solvePropertySpace(Player player, PropertySpace space) {
        return null;
    }

    private static Info solveVacationSpace(Player player, VacationSpace space) {
        return null;
    }
}
