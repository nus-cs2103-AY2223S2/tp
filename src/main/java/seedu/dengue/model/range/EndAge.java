package seedu.dengue.model.range;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

public class EndAge extends Age implements End<Age> {
    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public EndAge(String age) {
        super(age);
    }

    public boolean isAfter(Person p) {
        String a1 = value;
        String a2 = p.getAge().value;
        return a1.compareTo(a2) >= 0;
    }
}
