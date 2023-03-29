package seedu.address.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.exceptions.EmptyAddressBookException;

/**
 * A class to perform importing of AddressBook data.
 */
public interface Importer {
    Optional<ReadOnlyAddressBook> readData() throws DataConversionException, EmptyAddressBookException;
}
