package seedu.vms.model;

import seedu.vms.model.person.Person;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook extends StorageModel<Person> implements ReadOnlyAddressBook {
    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        super(toBeCopied);
    }
}
