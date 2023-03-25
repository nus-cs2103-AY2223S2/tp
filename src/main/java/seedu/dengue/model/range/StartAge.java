package seedu.dengue.model.range;

import java.time.LocalDate;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

public class StartAge extends Age implements Start<Age> {
    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public StartAge(String age) {
        super(age);
    }

    public boolean isBefore(Person p) {
        String a1 = value;
        String a2 = p.getAge().value;
        return a1.compareTo(a2) <= 0;
    }
}