package seedu.patientist.testutil;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Patientist ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Patientist patientist;

    public AddressBookBuilder() {
        patientist = new Patientist();
    }

    public AddressBookBuilder(Patientist patientist) {
        this.patientist = patientist;
    }

    /**
     * Adds a new {@code Person} to the {@code Patientist} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        //patientist.addPerson(person); todo
        return this;
    }

    public Patientist build() {
        return patientist;
    }
}
