package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPCClass;
import seedu.address.storage.parents.ParentsStorage;
import seedu.address.storage.pcclass.PCClassStorage;

/**
 * API of the ClassStorage component
 */
public interface ClassStorage extends ParentsStorage, PCClassStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    Path getPCFilePath();

    Optional<ReadOnlyPCClass> readPC() throws DataConversionException, IOException;

    void savePC(ReadOnlyPCClass readOnlyPCClass) throws IOException;

}
