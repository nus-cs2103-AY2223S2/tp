package vimification.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.commons.exceptions.IllegalValueException;
import vimification.commons.util.FileUtil;
import vimification.commons.util.JsonUtil;
import vimification.model.ReadOnlyTaskPlanner;

/**
 * A class to access TaskPlanner data stored as a json file on the hard disk.
 */
public class JsonTaskPlannerStorage implements TaskPlannerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskPlannerStorage.class);

    private Path filePath;

    public JsonTaskPlannerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskPlanner> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskPlanner> readTaskList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (!jsonTaskList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskList(ReadOnlyTaskPlanner taskList) throws IOException {
        saveTaskList(taskList, filePath);
    }

    /**
     * Similar to {@link #saveTaskList(ReadOnlyTaskPlanner)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskList(ReadOnlyTaskPlanner taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(taskList), filePath);
    }

}
