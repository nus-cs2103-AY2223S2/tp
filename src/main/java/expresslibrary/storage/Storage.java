package expresslibrary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.ReadOnlyUserPrefs;
import expresslibrary.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ExpressLibraryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getExpressLibraryFilePath();

    @Override
    Optional<ReadOnlyExpressLibrary> readExpressLibrary() throws DataConversionException, IOException;

    @Override
    void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary) throws IOException;

}
