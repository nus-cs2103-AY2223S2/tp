package seedu.address.model.service;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Part}'s id matches the id given.
 */
public class PartIdPredicate implements Predicate<Part> {
    private final int id;

    public PartIdPredicate(Index id) {
        this.id = id.getZeroBased();
    }

    @Override
    public boolean test(Part part) {
        return part.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PartIdPredicate // instanceof handles nulls
                && id == ((PartIdPredicate) other).id); // state check
    }

}
