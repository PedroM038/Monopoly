package model;

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
	private Player owner;
	private int baseRent;
    
	public Property(String name, int price, int housePrice, int houseValue, int mortgagePrice, int baseRent) {
		this.name = name;
		this.price = price;
		this.housePrice = housePrice;
		this.numHouses = 0;
		this.houseValue = houseValue;
		this.mortgagePrice = mortgagePrice;
		this.isMortgaged = false;
		this.owner = null;
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
	public int totalRentValue() {
		return this.baseRent + this.numHouses * this.houseValue;
	}

	// Player p buys house
	// obs: returns false if cant buy house
    public boolean buy(Player p) {
		if (p.getMoney() < this.getActualPrice() || this.owner != null || this.owner == p) {
			return false;
		}
		this.owner = p;
		p.pay(this.getActualPrice());
		if (this.isMortgaged) {
			this.isMortgaged = false;
		}
		return true;
	}

	// sells property
	// obs: returns false if cant sell house
	public boolean sell() {
		if (this.owner == null) {
			return false;
		}
		this.isMortgaged = true;
		this.owner.earn(this.getActualPrice());
		this.owner = null;
		this.numHouses = 0;
		return true;
	}

	// player p pays rent
	public void applyRent(Player p) {
		if (this.owner == p) {
			return;
		}
		p.pay(this.totalRentValue());
	}

	// owner buys a house
	// returns false if cant buy houses
	public boolean buyHouse() {
		if (this.owner == null || this.owner.getMoney() < this.housePrice || this.numHouses == this.MAX_NUM_HOUSES) {
			return false;
		}
		this.numHouses += 1;
		this.owner.pay(this.housePrice);
		return true;
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

    public int getHouseValue() {
        return this.houseValue;
    }

    public int getMortgagePrice() {
        return this.mortgagePrice;
    }

    public boolean isMortgaged() {
        return this.isMortgaged;
    }

    public Player getOwner() {
        return this.owner;
    }

    public int getBaseRent() {
        return this.baseRent;
    }
}
