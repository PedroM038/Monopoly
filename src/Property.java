/**
 * Property
 */
public class Property {
	private String name;
	private int[] values;
	private int houses;
    
	public Property(String name, int values[], int houses) {
		this.name = name;
		this.values = values;
		this.houses = houses;
	}

        public int getValue(int level) {
                return this.value[level];
        }

        public String getName() {
                return this.name;
        }

        public int getHouses() {
                return this.houses;
        }

        public void setHouses(int houses) {
                this.houses = houses;
        }
}
