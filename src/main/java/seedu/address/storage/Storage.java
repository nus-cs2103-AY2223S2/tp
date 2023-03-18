package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.elderly.ElderlyStorage;
import seedu.address.storage.pair.PairStorage;
import seedu.address.storage.volunteer.VolunteerStorage;

/**
 * API of the Storage component
 */
public interface Storage extends PairStorage, VolunteerStorage, ElderlyStorage, UserPrefsStorage {

    /**
     * Reads all elderly, volunteer and elderly details.
     */
    FriendlyLink read() throws DataConversionException, IOException;

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;
}
