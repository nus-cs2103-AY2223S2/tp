package teambuilder.testutil;

import teambuilder.model.TeamBuilder;
import teambuilder.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TeamBuilderMaker {

    private TeamBuilder addressBook;

    public TeamBuilderMaker() {
        addressBook = new TeamBuilder();
    }

    public TeamBuilderMaker(TeamBuilder addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TeamBuilderMaker withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public TeamBuilder build() {
        return addressBook;
    }
}
