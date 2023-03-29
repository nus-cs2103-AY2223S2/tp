package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the end of the age in a given range
 */
public class EndAge implements End<Age> {

    private final Optional<Age> age;

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
     * @param p
     */
    public boolean isAfter(Person p) {
        if (!age.isPresent()) {
            return true;
        }
        int a1 = Integer.parseInt(age.get().value);
        int a2 = Integer.parseInt(p.getAge().value);
        return a1 >= a2;
    }
}
