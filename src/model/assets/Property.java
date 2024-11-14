package assets;

public class Property {
	// constants
	public static final int MAX_NUM_HOUSES = 4;

	private String name;
	private int price;
	private int housePrice;  // how much to buy house
	private int numHouses;
	private int houseValue;  // how much each house adds to rent
	private int mortgagePrice;
	private boolean isMortgaged;
	private int baseRent;
    
	public Property(String name, int price, int housePrice, int houseValue, int mortgagePrice, int baseRent) {
		this.name = name;
		this.price = price;
		this.housePrice = housePrice;
		this.numHouses = 0;
		this.houseValue = houseValue;
		this.mortgagePrice = mortgagePrice;
		this.isMortgaged = false;
		this.baseRent = baseRent;  // rent when there's no houses/hotels
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
	public void buy() {
		this.isMortgaged = false;
	}

	// sell property
	public void sell() {
		this.isMortgaged = true;
		this.numHouses = 0;
	}

	// owner buys a house
	public void buyHouse() {
		if (this.numHouses == this.MAX_NUM_HOUSES) {
			throw new ArithmeticException("Property has max number of houses already.");
		}
		this.numHouses += 1;
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
}
