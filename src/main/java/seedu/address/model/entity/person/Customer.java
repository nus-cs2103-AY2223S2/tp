package seedu.address.model.entity.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.service.Vehicle;
import seedu.address.model.tag.Tag;

/**
 * The Customer class represents a Customer.
 */
public class Customer extends Person {

    private final int id;
    private final Set<Integer> vehicleIds = new HashSet<>();
    // TODO: Service History list, ensure its HashSet of service ids*

    /**
     * {@inheritDoc}
     */
    public Customer(int id, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public Customer(int id, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                    Set<Integer> vehicleIds) {
        super(name, phone, email, address, tags);
        this.id = id;
        this.vehicleIds.addAll(vehicleIds);
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

    /**
     * Returns true if both customers have the same id.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && otherCustomer.getId() == getId();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Customer) {
            Customer otherCustomer = (Customer) other;
            return this.getId() == otherCustomer.getId() || super.equals(other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, super.getName(), super.getPhone(), super.getEmail(), super.getAddress());
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return temp + StringUtil.NEWLINE + String.format("Vehicles: %s", this.getVehicleIds());
    }

}
