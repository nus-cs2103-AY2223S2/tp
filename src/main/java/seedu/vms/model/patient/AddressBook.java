package seedu.vms.model.patient;

import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class AddressBook extends StorageModel<Patient> implements ReadOnlyAddressBook {
    public AddressBook() {}

    /**
     * Creates an AddressBook using the Patients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        super(toBeCopied);
    }
}
