package de.joshuarosenberger.suffkischte.framework;

/**
 * A Player has a name and some statistics of him during a single game session.
 */

public class Player {
    private final String name;
    private int recievedDrinks;
    private int handedOutDrinks;

    public Player(String name){
        if (name.length() < 1)
            throw new IllegalArgumentException("Names should have at least 1 character.");
        this.name = name;
        this.recievedDrinks = 0;
        this.handedOutDrinks = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getRecievedDrinks() {
        return this.recievedDrinks;
    }

    public int getHandedOutDrinks() {
        return this.handedOutDrinks;
    }

    /**
     * Adds an amount of drinks to the players count of recieved drinks.
     * A negative number of drinks means the drinks are subtracted from the total count.
     * @param drinks
     */
    public void addRecievedDrinks(int drinks) {
        this.recievedDrinks += drinks;
    }

    /**
     * Adds an amount of drinks to the players count of handed out drinks.
     * A negative number of drinks means the drinks are subtracted from the total count.
     * @param drinks
     */
    public void addHandedOutDrinks(int drinks) {
        this.handedOutDrinks += drinks;
    }
}
