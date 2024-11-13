package assets;

import java.util.Random;

public class Dice {
    private int sides; // Number of sides on the dice
    private Random random; // Random object to generate random numbers

    // Constructor that initializes the dice with n sides
    public Dice(int sides) {
        if (sides <= 0) {
            throw new IllegalArgumentException("The number of sides must be greater than 0.");
        }
        this.sides = sides;
        this.random = new Random();
    }

    // Method to roll the dice and return a random side
    public int roll() {
        return random.nextInt(this.sides) + 1;
    }

    // Method to get the number of sides of the dice
    public int getSides() {
        return this.sides;
    }
}
