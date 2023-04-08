package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.logic.comparators.AgeComparator;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the end of the age in a given range
 */
public class EndAge implements End<Age> {

    private static final AgeComparator AGE_COMPARATOR = new AgeComparator();
    public final Optional<Age> age;

    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public EndAge(Optional<Age> age) {
        this.age = age;
    }

    /**
     * Checks for whether the end value of the age range is after the age of the person.
     *
     * @param p A Person.
     */
    public boolean isAfter(Person p) {
        if (!age.isPresent()) {
            return true;
        }
        return AGE_COMPARATOR.compare(age.get(), p.getAge()) >= 0;
    }

    /**
     * Checks for whether the start value of the age range is before the given end age.
     *
     * @param start A StartAge.
     */
    public boolean isValidEnd(Start<Age> start) {
        if (!(start.get().isPresent() && age.isPresent())) {
            return true;
        }
        int a1 = Integer.parseInt(age.get().value);
        int a2 = Integer.parseInt(start.get().get().value);
        return a1 >= a2;
    }

    public Optional<Age> get() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndAge // instanceof handles nulls
                && age.equals(((EndAge) other).age));
    }
}
