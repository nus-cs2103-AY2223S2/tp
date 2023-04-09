package trackr.model.person;

import trackr.model.commons.Name;

/**
 * Represents a Person's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class PersonName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Person (Customer / Supplier) names should only contain "
                    + "alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code PersonName}.
     *
     * @param personName A valid person name.
     */
    public PersonName(String personName) {
        super(personName, "Person");
    }
}
