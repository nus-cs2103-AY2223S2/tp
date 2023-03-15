package seedu.address.model.entity.shop.garage;

import java.util.Objects;

import seedu.address.model.service.Vehicle;

/**
 * This class represents a lot within the Garage.
 * A garage can have >=0 lots.
 */
public class Lot {
    private Vehicle vehicle = null;

    /**
     * This method returns the current vehicle parked at this lot.
     *
     * @return the current vehicle parked at this lot.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * This method sets the current vehicle parked at this lot.
     *
     * @param hasVehicle
     */
    public void setVehicle(Vehicle hasVehicle) {
        this.vehicle = hasVehicle;
    }

    /**
     * This method returns true if a vehicle is in this lot.
     *
     * @return whether a vehicle is in this lot.
     */
    public boolean hasVehicle() {
        return Objects.isNull(vehicle);
    }

    /**
     * This method sets the vehicle field to null.
     */
    public void removeVehicle() {
        vehicle = null;
    }

}
