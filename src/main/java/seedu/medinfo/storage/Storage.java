package seedu.medinfo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.ReadOnlyUserPrefs;
import seedu.medinfo.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MedInfoStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMedInfoFilePath();

    @Override
    Optional<ReadOnlyMedInfo> readMedInfo() throws DataConversionException, IOException, CommandException;

    @Override
    void saveMedInfo(ReadOnlyMedInfo addressBook) throws IOException;

}
