package teambuilder.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import teambuilder.commons.exceptions.DataConversionException;
import teambuilder.model.ReadOnlyTeamBuilder;
import teambuilder.model.ReadOnlyUserPrefs;
import teambuilder.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TeamBuilderStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyTeamBuilder> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTeamBuilder addressBook) throws IOException;

}
