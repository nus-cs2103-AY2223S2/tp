package seedu.connectus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.connectus.commons.exceptions.DataConversionException;
import seedu.connectus.model.ReadOnlyConnectUs;
import seedu.connectus.model.ReadOnlyUserPrefs;
import seedu.connectus.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ConnectUsStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getConnectUsFilePath();

    @Override
    Optional<ReadOnlyConnectUs> readConnectUs() throws DataConversionException, IOException;

    @Override
    void saveConnectUs(ReadOnlyConnectUs addressBook) throws IOException;

}
