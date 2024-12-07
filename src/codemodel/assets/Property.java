/*
* The Property class represents a property from the game
* It focuses in only storing data
*/

package codemodel.assets;
import java.io.*;

public class Property implements Serializable {
    // constants
    public final Integer MAX_NUM_HOUSES = 4;

    private String name;
    private Integer price;
    private Integer housePrice;  // how much to buy house
    private Integer numHouses;
    private Integer houseValue;  // how much each house adds to rent
    private Integer mortgagePrice;
    private boolean isMortgaged;
    private Integer baseRent;
    private Integer ownerId;
    
    public Property(String name, Integer price, Integer housePrice, Integer houseValue, Integer mortgagePrice, Integer baseRent) {
        this.name = name;
        this.price = price;
        this.housePrice = housePrice;
        this.numHouses = 0;
        this.houseValue = houseValue;
        this.mortgagePrice = mortgagePrice;
        this.isMortgaged = false;
        this.baseRent = baseRent;  // rent when there's no houses/hotels
        this.ownerId = -1;  // -1 means no owner;
    }

    // price of property
    // if mortgaged, mortgage price; otherwise, normal price
    public Integer getActualPrice() {
        if (this.isMortgaged) {
            return this.mortgagePrice;
        } else {
            return this.price;
        }
    }

    // price of rent
    public Integer getRentValue() {
        return this.baseRent + this.numHouses * this.houseValue;
    }

    // buy property
    public void buy(Integer ownerId) {
        this.isMortgaged = false;
        this.numHouses = 0;
        this.ownerId = ownerId;
    }

    // sell property
    public void sell() {
        this.isMortgaged = true;
        this.numHouses = 0;
        this.ownerId = -1;
    }

    // owner buys a house
    public void buyHouse() {
        if (this.hasAllHouses()) {
            throw new ArithmeticException("Property has max number of houses already.");
        }
        this.numHouses += 1;
    }

    public boolean hasAllHouses() {
        return this.numHouses == this.MAX_NUM_HOUSES;
    }

    public boolean isOwner(Integer playerId) {
        return this.ownerId == playerId;
    }

    public boolean hasOwner() {
        return this.ownerId != -1;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Integer getHousePrice() {
        return this.housePrice;
    }

    public Integer getNumHouses() {
        return this.numHouses;
    }

    public Integer getMaxNumHouses() {
        return this.MAX_NUM_HOUSES;
    }

    public Integer getHouseValue() {
        return this.houseValue;
    }

    public Integer getMortgagePrice() {
        return this.mortgagePrice;
    }

    public boolean isMortgaged() {
        return this.isMortgaged;
    }

    public Integer getBaseRent() {
        return this.baseRent;
    }

    public Integer getOwnerId() {
        return this.ownerId;
    }
}
