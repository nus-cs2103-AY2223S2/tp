package seedu.dengue.model.range;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the end of the age in a given range
 */
public class EndAge extends Age implements End<Age> {

    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public EndAge(String age) {
        super(age);
    }

    /**
     * Checks for whether the end value of the age range is after the age of the person.
     *
     * @param p
     */
    public boolean isAfter(Person p) {
        String a1 = value;
        String a2 = p.getAge().value;
        return a1.compareTo(a2) >= 0;
    }
}
