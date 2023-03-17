package seedu.address.model.service;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;


/**
 * Tests that a {@code Service}'s id matches the id given.
 */
public class ServiceIdPredicate implements Predicate<Service> {
    private final int id;

    public ServiceIdPredicate(Index id) {
        this.id = id.getZeroBased();
    }

    @Override
    public boolean test(Service service) {
        return service.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ServiceIdPredicate // instanceof handles nulls
                && id == ((ServiceIdPredicate) other).id); // state check
    }

}

