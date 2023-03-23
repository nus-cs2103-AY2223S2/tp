package seedu.address.model.module;

import seedu.address.model.Name;

/**
 * Represents a module's name in the tracker.<p>
 * Guarantees: immutable, is valid as declared in {@link #isValidName(String)}.
 */
public class ModuleName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Module names should only contain alphanumeric characters and spaces";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]*";

    /**
     * Constructs a {@code ModuleName}.
     *
     * @param name A valid name.
     */
    public ModuleName(String name) {
        super(name, VALIDATION_REGEX, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if {@code test} is a valid name.
     *
     * @param test The string to check if it is a valid name.
     * @return True if {@code test} is a valid name. Otherwise, false.
     */
    public static boolean isValidName(String test) {
        return Name.isValidName(test, VALIDATION_REGEX);
    }

    /**
     * Returns true if the name is empty.
     *
     * @return True if the name is empty. Otherwise, false.
     */
    public boolean isEmpty() {
        return name.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ModuleName;
    }

}
