package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the start of the age in a given range
 */
public class StartAge implements Start<Age> {

    public final Optional<Age> age;

    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public StartAge(Optional<Age> age) {
        this.age = age;
    }


    /**
     * Checks for whether the start value of the age range is before the age of the person.
     *
     * @param p A Person.
     */
    public boolean isBefore(Person p) {
        if (!age.isPresent()) {
            return true;
        }
        int a1 = Integer.parseInt(age.get().value);
        int a2 = Integer.parseInt(p.getAge().value);
        return a1 <= a2;
    }

    /**
     * Checks for whether the start value of the age range is before the given end age.
     *
     * @param end An EndAge.
     */
    public boolean isValidStart(End<Age> end) {
        if (!(end.get().isPresent() && age.isPresent())) {
            return true;
        }
        int a1 = Integer.parseInt(age.get().value);
        int a2 = Integer.parseInt(end.get().get().value);
        return a1 <= a2;
    }

    public Optional<Age> get() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartAge // instanceof handles nulls
                && age.equals(((StartAge) other).age));
    }
}
