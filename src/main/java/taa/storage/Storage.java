package taa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taa.commons.exceptions.DataConversionException;
import taa.model.ReadOnlyUserPrefs;
import taa.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaaStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaaDataFilePath();

    @Override
    Optional<TaaData> readTaaData() throws DataConversionException, IOException;

    @Override
    void saveTaaData(TaaData taaData) throws IOException;

}
