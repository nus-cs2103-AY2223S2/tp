package tfifteenfour.clipboard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.ReadOnlyUserPrefs;
import tfifteenfour.clipboard.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends RosterStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getRosterFilePath();

    @Override
    Optional<ReadOnlyRoster> readRoster() throws DataConversionException, IOException;

    @Override
    void saveRoster(ReadOnlyRoster roster) throws IOException;

}
