package seedu.address.model.entity.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.Vehicle;
import seedu.address.model.tag.Tag;

/**
 * The Customer class represents a Customer.
 */
public class Customer extends Person {

    private static int incrementalId = 0;
    private int id;
    private ArrayList<Vehicle> vehicles;
    // Service History

    /**
     * {@inheritDoc}
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        id = ++incrementalId;
    }

    /**
     * This method returns Customer id.
     * @return customer id.
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns a list of vehicles which the Customer has.
     * @return a list of vehicles this customer has.
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * This method adds vehicles to the Customer.
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    /**
     * This method removes vehicles from the Customer.
     * @param vehicle The vehicle to add.
     */
    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

}
