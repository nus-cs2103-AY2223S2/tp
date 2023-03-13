package codoc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import codoc.commons.exceptions.DataConversionException;
import codoc.model.ReadOnlyCodoc;
import codoc.model.ReadOnlyUserPrefs;
import codoc.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CodocStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getCodocFilePath();

    @Override
    Optional<ReadOnlyCodoc> readCodoc() throws DataConversionException, IOException;

    @Override
    void saveCodoc(ReadOnlyCodoc codoc) throws IOException;

}
