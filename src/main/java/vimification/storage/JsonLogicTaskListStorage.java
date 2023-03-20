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
import vimification.model.LogicTaskList;

/**
 * A class to access TaskPlanner data stored as a json file on the hard disk.
 */
public class JsonLogicTaskListStorage implements LogicTaskListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLogicTaskListStorage.class);

    private Path filePath;

    public JsonLogicTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public Optional<LogicTaskList> readLogicTaskList() throws DataConversionException {
        return readLogicTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<LogicTaskList> readLogicTaskList(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonAdaptedLogicTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonAdaptedLogicTaskList.class);
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
    public void saveTaskList(LogicTaskList taskList) throws IOException {
        saveTaskList(taskList, filePath);
    }

    /**
     * Similar to {@link #saveTaskList(LogicTaskList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskList(LogicTaskList taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonAdaptedLogicTaskList(taskList), filePath);
    }
}
