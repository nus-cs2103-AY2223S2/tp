package seedu.address.model.entity.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.Vehicle;
import seedu.address.model.tag.Tag;

/**
 * The Customer class represents a Customer.
 */
public class Customer extends Person {

    private static int incrementalId = 0;
    private int id;
    private final Set<Integer> vehicleIds;
    // Service History

    /**
     * {@inheritDoc}
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.vehicleIds = new HashSet<>();
        id = ++incrementalId;
    }

    /**
     * This method returns Customer id.
     *
     * @return customer id.
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns a list of vehicles which the Customer has.
     *
     * @return a list of vehicles this customer has.
     */
    public List<Integer> getVehicleIds() {
        return new ArrayList<>(this.vehicleIds);
    }

    /**
     * This method adds vehicles to the Customer.
     *
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle) {
        this.vehicleIds.add(vehicle.getId());
    }

    /**
     * This method removes vehicles from the Customer.
     *
     * @param vehicle The vehicle to add.
     */
    public void removeVehicle(Vehicle vehicle) {
        this.vehicleIds.remove(vehicle.getId());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Customer) {
            Customer otherCustomer = (Customer) other;
            return this.getId() == otherCustomer.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return temp + StringUtil.NEWLINE + String.format("Vehicles: %s", this.getVehicleIds());
    }

}
