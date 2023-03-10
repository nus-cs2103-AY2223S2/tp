package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyReminders;
import seedu.address.model.Reminders;

import static java.util.Objects.requireNonNull;

/**
 * A class to access Reminders data stored as a json file on the hard disk.
 */
public class JsonRemindersStorage implements RemindersStorage{

    private static final Logger logger = LogsCenter.getLogger(JsonRemindersStorage.class);

    private Path filePath;

    public JsonRemindersStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRemindersFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyReminders> readReminders() throws DataConversionException {
        return readReminders(filePath);
    }

    /**
     * Similar to {@link #readReminders()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyReminders> readReminders(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        return JsonUtil.readJsonFile(filePath, ReadOnlyReminders.class);
    }

    @Override
    public void saveReminders(ReadOnlyReminders reminders) throws IOException {
        JsonUtil.saveJsonFile(reminders, filePath);
    }

    /**
     * Similar to {@link #saveReminders(ReadOnlyReminders)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveReminders(ReadOnlyReminders reminders, Path filePath) throws IOException {
        requireNonNull(reminders);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(reminders, filePath);
    }
}
