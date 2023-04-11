package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.storage.parents.ParentsStorage;
import seedu.address.storage.pcclass.PcClassStorage;

/**
 * API of the Storage component
 */
public interface Storage extends ParentsStorage, PcClassStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    Path getPcFilePath();

    Optional<ReadOnlyPcClass> readPC() throws DataConversionException, IOException;

    void savePC(ReadOnlyPcClass readOnlyPcClass) throws IOException;

}
