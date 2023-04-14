package seedu.powercards.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.powercards.commons.exceptions.DataConversionException;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.ReadOnlyUserPrefs;
import seedu.powercards.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MasterDeckStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMasterDeckFilePath();

    @Override
    Optional<ReadOnlyMasterDeck> readMasterDeck() throws DataConversionException, IOException;

    @Override
    void saveMasterDeck(ReadOnlyMasterDeck masterDeck) throws IOException;

}
