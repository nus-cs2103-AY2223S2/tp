package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.information.Region.Place;

/**
 * Tests that a {@code Person}'s {@code Place} exactly matches the given place.
 */
public class RegionIsEqualPredicate<T extends Person> implements Predicate<T> {
    private final Place place;

    /**
     * Constructs a {@code RegionIsIsEqualPredicate} with the given place
     *
     * @param place The matching place.
     */
    public RegionIsEqualPredicate(Place place) {
        requireNonNull(place);
        this.place = place;
    }

    @Override
    public boolean test(T object) {
        return object.getRegion().region.equals(place);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegionIsEqualPredicate // instanceof handles nulls
                && place.equals(((RegionIsEqualPredicate<?>) other).place)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(place);
    }
}

