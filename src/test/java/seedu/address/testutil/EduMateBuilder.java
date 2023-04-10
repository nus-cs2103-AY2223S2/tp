package seedu.address.testutil;

import seedu.address.model.EduMate;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code EduMate ab = new EduMateBuilder().withPerson("John", "Doe").build();}
 */
public class EduMateBuilder {

    private EduMate eduMate;

    public EduMateBuilder() {
        eduMate = new EduMate();
    }

    public EduMateBuilder(EduMate eduMate) {
        this.eduMate = eduMate;
    }

    /**
     * Adds a new {@code Person} to the {@code EduMate} that we are building.
     */
    public EduMateBuilder withPerson(Person person) {
        eduMate.addPerson(person);
        return this;
    }

    public EduMate build() {
        return eduMate;
    }
}
