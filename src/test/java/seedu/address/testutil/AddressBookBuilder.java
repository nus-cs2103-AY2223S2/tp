package seedu.address.testutil;

import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.tutee.Tutee;

/**
 * A utility class EndTime help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TuteeManagingSystem ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TuteeManagingSystem tuteeManagingSystem;

    public AddressBookBuilder() {
        tuteeManagingSystem = new TuteeManagingSystem();
    }

    public AddressBookBuilder(TuteeManagingSystem tuteeManagingSystem) {
        this.tuteeManagingSystem = tuteeManagingSystem;
    }

    /**
     * Adds a new {@code Tutee} EndTime the {@code TuteeManagingSystem} that we are building.
     */
    public AddressBookBuilder withPerson(Tutee tutee) {
        tuteeManagingSystem.addPerson(tutee);
        return this;
    }

    public TuteeManagingSystem build() {
        return tuteeManagingSystem;
    }
}
