package vimification.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.commons.exceptions.IllegalValueException;
import vimification.commons.util.JsonUtil;
import vimification.model.LogicTaskList;

/**
 * A class to access TaskPlanner data stored as a json file on the hard disk.
 */
public class JsonLogicTaskListStorage implements LogicTaskListStorage {

    private static final Logger LOGGER = LogsCenter.getLogger(JsonLogicTaskListStorage.class);

    private Path filePath;

    public JsonLogicTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getLogicTaskListFilePath() {
        return filePath;
    }

    @Override
    public LogicTaskList readLogicTaskList() throws DataConversionException, IOException {
        try {
            return JsonUtil
                    .readJsonFile(filePath, JsonAdaptedLogicTaskList.class)
                    .toModelType();
        } catch (IllegalValueException ive) {
            LOGGER.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLogicTaskList(LogicTaskList taskList) throws IOException {
        requireNonNull(taskList);
        JsonUtil.saveJsonFile(new JsonAdaptedLogicTaskList(taskList), filePath);
    }
}
