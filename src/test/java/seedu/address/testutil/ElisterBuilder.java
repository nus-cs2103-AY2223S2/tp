package seedu.address.testutil;

import seedu.address.model.Elister;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Elister objects.
 * Example usage: <br>
 *     {@code Elister ab = new ElisterBuilder().withPerson("John", "Doe").build();}
 */
public class ElisterBuilder {

    private Elister elister;

    public ElisterBuilder() {
        elister = new Elister();
    }

    public ElisterBuilder(Elister elister) {
        this.elister = elister;
    }

    /**
     * Adds a new {@code Person} to the {@code Elister} that we are building.
     */
    public ElisterBuilder withPerson(Person person) {
        elister.addPerson(person);
        return this;
    }

    public Elister build() {
        return elister;
    }
}
