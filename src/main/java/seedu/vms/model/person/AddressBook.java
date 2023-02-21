package seedu.vms.model.person;

import seedu.vms.model.StorageModel;

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
