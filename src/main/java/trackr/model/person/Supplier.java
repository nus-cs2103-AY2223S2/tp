package trackr.model.person;

import java.util.Set;

import trackr.model.commons.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {

    /**
     * Every field must be present and not null.
     */
    public Supplier(PersonName personName, PersonPhone personPhone, PersonEmail personEmail,
                    PersonAddress personAddress, Set<Tag> tags) {
        super(personName, personPhone, personEmail, personAddress, tags);
    }

}
