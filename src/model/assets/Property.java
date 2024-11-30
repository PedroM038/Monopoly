package assets;

public class Property {
	// constants
	public final int MAX_NUM_HOUSES = 4;

	private String name;
	private int price;
	private int housePrice;  // how much to buy house
	private int numHouses;
	private int houseValue;  // how much each house adds to rent
	private int mortgagePrice;
	private boolean isMortgaged;
	private int baseRent;
	private int ownerId;
    
	public Property(String name, int price, int housePrice, int houseValue, int mortgagePrice, int baseRent) {
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
	public int getActualPrice() {
		if (this.isMortgaged) {
			return this.mortgagePrice;
		}
		else {
			return this.price;
		}
	}

	// price of rent
	public int getRentValue() {
		return this.baseRent + this.numHouses * this.houseValue;
	}

	// buy property
	public void buy(int ownerId) {
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

	public boolean isOwner(int playerId) {
        return this.ownerId == playerId;
    }

    public boolean hasOwner() {
        return this.ownerId != -1;
    }


	// GETTERS
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getHousePrice() {
        return this.housePrice;
    }

    public int getNumHouses() {
        return this.numHouses;
    }

	public int getMaxNumHouses() {
		return this.MAX_NUM_HOUSES;
	}

    public int getHouseValue() {
        return this.houseValue;
    }

    public int getMortgagePrice() {
        return this.mortgagePrice;
    }

    public boolean isMortgaged() {
        return this.isMortgaged;
    }

    public int getBaseRent() {
        return this.baseRent;
    }

	public int getOwnerId() {
		return this.ownerId;
	}
}
