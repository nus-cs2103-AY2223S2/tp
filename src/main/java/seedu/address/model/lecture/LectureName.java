package seedu.address.model.lecture;

import seedu.address.model.Name;

/**
 * Represents a lecture's name in the tracker.<p>
 * Guarantees: immutable, is valid as declared in {@link #isValidName(String)}.
 */
public class LectureName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Lecture names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code LectureName}.
     *
     * @param name A valid name.
     */
    public LectureName(String name) {
        super(name, MESSAGE_CONSTRAINTS);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof LectureName;
    }

}
