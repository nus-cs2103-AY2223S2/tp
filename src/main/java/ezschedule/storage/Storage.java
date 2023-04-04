package ezschedule.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ezschedule.commons.exceptions.DataConversionException;
import ezschedule.model.ReadOnlyScheduler;
import ezschedule.model.ReadOnlyUserPrefs;
import ezschedule.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SchedulerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSchedulerFilePath();

    @Override
    Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException, IOException;

    @Override
    void saveScheduler(ReadOnlyScheduler scheduler) throws IOException;
}
