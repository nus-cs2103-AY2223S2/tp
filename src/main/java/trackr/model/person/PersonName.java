package trackr.model.person;

import trackr.model.commons.Name;

/**
 * Represents a Person's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class PersonName extends Name {

    /**
     * Constructs a {@code Name}.
     *
     * @param personName A valid person name.
     */
    public PersonName(String personName) {
        super(personName, "Person");
    }
}
