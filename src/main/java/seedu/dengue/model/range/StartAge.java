package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the start of the age in a given range
 */
public class StartAge implements Start<Age> {

    private final Optional<Age> age;

    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public StartAge(Optional<Age> age) {
        this.age = age;
    }


    /**
     * Checks for whether the end value of the age range is before the age of the person.
     *
     * @param p
     */
    public boolean isBefore(Person p) {
        if (!age.isPresent()) {
            return true;
        }
        int a1 = Integer.parseInt(age.get().value);
        int a2 = Integer.parseInt(p.getAge().value);
        return a1 <= a2;
    }
}
