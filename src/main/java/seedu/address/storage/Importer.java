package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.exceptions.JsonNotFoundException;

/**
 * A class to perform importing of AddressBook data.
 */
public interface Importer {
    ReadOnlyAddressBook readData() throws DataConversionException, JsonNotFoundException;
}
