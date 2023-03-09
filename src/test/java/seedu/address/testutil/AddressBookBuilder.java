package seedu.address.testutil;

import seedu.address.model.Deck;
import seedu.address.model.person.Card;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Deck ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Deck addressBook;

    public AddressBookBuilder() {
        addressBook = new Deck();
    }

    public AddressBookBuilder(Deck addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Card} to the {@code Deck} that we are building.
     */
    public AddressBookBuilder withPerson(Card card) {
        addressBook.addPerson(card);
        return this;
    }

    public Deck build() {
        return addressBook;
    }
}
