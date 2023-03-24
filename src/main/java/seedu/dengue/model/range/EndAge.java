package seedu.dengue.model.range;

import seedu.dengue.model.person.Age;

public class EndAge extends Age implements End<Age> {
    /**
     * Constructs an {@code Age}.
     *
     * @param age A valid age.
     */
    public EndAge(String age) {
        super(age);
    }
}
