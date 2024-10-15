package model;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Property> properties;

    public Bank() {
        properties = new ArrayList<Property>();
    }

    public void mortgage(Property p) {
        this.properties.add(p);
    }

    public Property get_property(int propertyId) {
        for (int i = 0; i < this.properties.size(); i++) {
            if (this.properties.get(i).id == propertyId) {
                Property found = this.properties.get(i);
                this.properties.remove(i);
                return found;
            }
        }
        throw new NotFoundException("Property is not on bank");
    }

    public boolean contains_property(int propertyId) {
        for (int i = 0; i < this.properties.size(); i++) {
            if (this.properties.get(i).id == propertyId) {
                return true;
            }
        }
        return false;
    }
}
