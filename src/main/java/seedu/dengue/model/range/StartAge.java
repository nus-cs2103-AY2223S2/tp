package seedu.dengue.model.range;

import seedu.dengue.model.person.Age;

public class StartAge extends Age implements Start<Age> {
    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public StartAge(String age) {
        super(age);
    }
}