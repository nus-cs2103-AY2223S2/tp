package seedu.address.testutil;

import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} and {@code Event} objects to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {};

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

}
