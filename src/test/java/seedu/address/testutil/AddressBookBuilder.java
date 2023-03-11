package seedu.address.testutil;

import seedu.address.model.MasterDeck;
import seedu.address.model.card.Card;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Deck ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private MasterDeck addressBook;

    public AddressBookBuilder() {
        addressBook = new MasterDeck();
    }

    public AddressBookBuilder(MasterDeck addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Card} to the {@code Deck} that we are building.
     */
    public AddressBookBuilder withPerson(Card card) {
        addressBook.addCard(card);
        return this;
    }

    public MasterDeck build() {
        return addressBook;
    }
}
