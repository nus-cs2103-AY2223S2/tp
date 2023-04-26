package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.mapping.AssignTask;


/**
 * A class to access AssignTask data stored as a json file on the hard disk.
 */
public class JsonAssignTaskStorage implements RepositoryStorage<AssignTask> {

    private static final Logger logger = LogsCenter.getLogger(JsonAssignTaskStorage.class);
    private final Path filePath;
    public JsonAssignTaskStorage(Path filePath) {
        this.filePath = filePath;
    }


    @Override
    public Optional<ReadOnlyRepository<AssignTask>> readRepository() throws DataConversionException {
        return readRepository(filePath);
    }

    /**
     * Similar to {@link #readRepository()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyRepository<AssignTask>> readRepository(Path filePath) throws
            DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAssignTasks> jsonAssignTasks = JsonUtil.readJsonFile(
            filePath, JsonSerializableAssignTasks.class);
        if (jsonAssignTasks.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonAssignTasks.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRepository(ReadOnlyRepository<AssignTask> repository) throws IOException {
        saveRepository(repository, filePath);

    }
    /**
     * Similar to {@link #saveRepository(ReadOnlyRepository)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveRepository(ReadOnlyRepository<AssignTask> repository, Path filePath) throws IOException {
        requireNonNull(repository);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAssignTasks(repository), filePath);
    }
}
