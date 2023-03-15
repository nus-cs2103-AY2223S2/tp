package codoc.testutil;

import codoc.model.Codoc;
import codoc.model.person.Person;

/**
 * A utility class to help with building Codoc objects. Example usage: <br>
 * {@code Codoc ab = new CodocBuilder().withPerson("John", "Doe").build();}
 */
public class CodocBuilder {

    private Codoc codoc;

    public CodocBuilder() {
        codoc = new Codoc();
    }

    public CodocBuilder(Codoc codoc) {
        this.codoc = codoc;
    }

    /**
     * Adds a new {@code Person} to the {@code Codoc} that we are building.
     */
    public CodocBuilder withPerson(Person person) {
        codoc.addPerson(person);
        return this;
    }

    public Codoc build() {
        return codoc;
    }
}
