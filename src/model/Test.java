//package model;

import assets.*;
import java.util.*;

import javax.sound.midi.SysexMessage;


public class Test {
    public static void main (String args[]) {
        // initializing stuff
        Scanner scanner = new Scanner(System.in);
        int numPlayers;
        ArrayList<String> playersNames = new ArrayList<>();
        ArrayList<String> playersColors = new ArrayList<>();
        int maxTurns;
        int numDice = 2;
        
        // get players (number, names)
        System.out.print("Number of players: ");
        numPlayers = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numPlayers; i++) {
            int pos = i + 1;
            System.out.print("Name of "+pos+" player: ");
            playersNames.add(scanner.nextLine());
            System.out.print("Color of "+pos+" player: ");
            playersColors.add(scanner.nextLine());
        }
        maxTurns = 30 * numPlayers;
        ModelInterface model = new ModelInterface(numPlayers, playersNames, playersColors, maxTurns, numDice);

        // defining player order
        System.out.println("----------");
        System.out.println("Defining player order:");
        ArrayList<Integer>results = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            int result = model.totalDiceResult(model.rollDices());
            System.out.println("Player "+playersNames.get(i)+": "+result);
            results.add(result);
        }
        model.definePlayerOrder(results);
        ArrayList<Integer> playersInOrder = model.getPlayersOrder();
        System.out.print("Player order: ");
        for (int i = 0; i < numPlayers; i++) {
            Player player = model.getPlayerById(playersInOrder.get(i));
            System.out.print(player.getName());
            if (i < numPlayers - 1) {
                System.out.print(", ");
            }
            else {
                System.out.println("");
            }
        }


        // game loop
        System.out.println("----------");
        System.out.println("STARTING THE GAME");
        while (!model.isFinished()) {
            Player player;
            String input;
            Info info;
            // move player
            player = model.getNextPlayer();
            System.out.println(player.getName()+" is playing...");
            System.out.print("Press ENTER to throw dice");
            input = scanner.nextLine();
            ArrayList<Integer> diceResults = model.rollDices();
            System.out.print("Dices: ");
            for (int i = 0; i < diceResults.size(); i++) {
                System.out.print(diceResults.get(i)+" ");
            }
            System.out.println("");
            info = model.moveNextPlayer(diceResults);

            // show data and options
            System.out.println("++++++++++");
            System.out.println("Player moved "+info.numMoves+" spaces");
            System.out.println("Player fell in space "+info.spaceId);
            if (info.space instanceof CardSpace) {
                System.out.println("Space is a CardSpace! You drew a card");
                System.out.println("Card: "+info.card.getDescription());
                if (info.card.getAction().equals("receber")) {
                    System.out.println("Player received "+info.card.getAmount());
                }
                else {
                    System.out.println("Player lost "+info.card.getAmount());
                }
            }
            else if (info.space instanceof MoneySpace) {
                System.out.println("Space is a MoneySpace!");
                MoneySpace tempSpace = (MoneySpace) info.space;
                System.out.println("Description: "+tempSpace.getDescription());
                if (tempSpace.isLoss()) {
                    System.out.println("Player lost "+tempSpace.getMoney());
                }
                else {
                    System.out.println("Player received "+tempSpace.getMoney());
                }
            }
            else if (info.space instanceof PrisonSpace) {
                System.out.println("Space is a PrisonSpace!");
                if (info.gotLocked) {
                    System.out.println("Player got locked! HAHAHAHAHAHA (sorry)");
                }
                else {
                    System.out.println("Oh Player was already locked");
                }
            }
            else if (info.space instanceof PropertySpace) {
                System.out.println("Space is a PropertySpace!");
                PropertySpace tempSpace = (PropertySpace) info.space;
                Property property = tempSpace.getProperty();
                Aux.printProperty(property);
                if (property.hasOwner() && !property.isOwner(player.getId())) {
                    System.out.println("Player paid rent");
                }
            }
            else if (info.space instanceof VacationSpace) {
                System.out.print("oh its a vacation space... wait he have those?");
            }
            else {
                System.out.println("oh its just a normal space nevermind");
            }
            System.out.println("++++++++++");

            // process option
            if (info.possible_actions.size() > 0) {
                System.out.print("OPTIONS: ");
                for (int i = 0; i < info.possible_actions.size(); i++) {
                    System.out.print("("+i+") "+info.possible_actions.get(i));
                    System.out.print(", ");
                }
                System.out.println("("+info.possible_actions.size()+") nothing");
                System.out.print("Select an option: ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option != info.possible_actions.size()) {
                    String optionDescription = info.possible_actions.get(option);
                    if (optionDescription.equals("bail")) {
                        model.bailFromJail();
                    }
                    else if (optionDescription.equals("buy")) {
                        model.buyProperty();
                    }
                    else if (optionDescription.equals("buyHouse")) {
                        model.buyHouse();
                    }
                    else if (optionDescription.equals("sell")) {
                        model.sellProperty(null);
                    }
                }
            }
            else {
                System.out.println("No options, going to the next player");
            }

            System.out.println("Choose an option: (0) go to next player, (1) print players, (2) print properties");
            System.out.print("Choise: ");
            int choice = Integer.parseInt(scanner.nextLine());
            while (choice != 0) {
                if (choice == 1) {
                    Aux.printAllPlayers(model.getPlayers());
                }
                else if (choice == 2) {
                    Aux.printAllProperties(model.getProperties());
                }
                System.out.println("Choose an option: (0) go to next player, (1) print players, (2) print properties");
                System.out.print("Choise: ");
                choice = Integer.parseInt(scanner.nextLine());
            }

            // go to next player
            System.out.println("We still have "+model.getLeftTurns()+" turns!");
            model.goToNextPlayer();

        }

        // show results
        System.out.println("THE GAME IS FINISHED!");
        Player winner = model.getWinner();
        System.out.println("Winner: "+winner.getName());

        // end/save
        scanner.close();
    }
}

class Aux {
    static void printPlayer(Player player) {
        System.out.println("----------");
        System.out.println("PLAYER INFO");
        System.out.println("Player: "+player.getName());
        System.out.println("Money: "+player.getMoney());
        System.out.println("Color: "+player.getColor());
        System.out.println("Is locked: "+player.isLocked());
        ArrayList<Property> properties = player.getProperties();
        if (properties.size() == 0) {
            System.out.println("----------");
            return;
        }
        System.out.print("Properties: ");
        for (int i = 0; i < properties.size(); i++) {
            System.out.print(properties.get(i).getName());
            if (i < properties.size() - 1) {
                System.out.print(", ");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("----------");
    }

    static void printProperty(Property property) {
        System.out.println("----------");
        System.out.println("PROPERTY INFO");
        System.out.println("Name: "+property.getName());
        if (property.hasOwner()) {
            System.out.println("Owner: Player"+property.getOwnerId());
        }
        else {
            System.out.println("Owner: None");
        }
        if (property.isMortgaged()) {
            System.out.println("Price: "+property.getPrice()+" (mortgaged)");
        }
        else {
            System.out.println("Price: "+property.getPrice());
        }
        System.out.println("House price: "+property.getHousePrice());
        System.out.println("Base rent: "+property.getBaseRent());
        System.out.println("House value: "+property.getHouseValue());
        System.out.println("Number of Houses: "+property.getNumHouses());
        System.out.println("Actual rent: "+property.getRentValue());
        System.out.println("----------");
    }

    static void printAllPlayers(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Aux.printPlayer(players.get(i));
        }
    }

    static void printAllProperties(ArrayList<Property> properties) {
        for (int i = 0; i < properties.size(); i++) {
            Aux.printProperty(properties.get(i));
        }
    }
}
