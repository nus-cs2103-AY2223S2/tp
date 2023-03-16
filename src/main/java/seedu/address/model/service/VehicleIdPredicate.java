package seedu.address.model.service;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;


/**
 * Tests that a {@code Vehicle}'s id matches the id given.
 */
public class VehicleIdPredicate implements Predicate<Vehicle> {
    private final int id;

    public VehicleIdPredicate(Index id) {
        this.id = id.getZeroBased();
    }

    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VehicleIdPredicate // instanceof handles nulls
                && id == ((VehicleIdPredicate) other).id); // state check
    }

}

